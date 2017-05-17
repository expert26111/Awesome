package readfiles2;

/**
 *
 * @author yoyo && thatOneDroid
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFiles2 {

    Map<City, Integer> integerCitites = new HashMap<>();
    Map<Integer, Book> books = new HashMap<>();
    Map<String, City> stringCities = new HashMap<>();
    String filesLocation = "C:\\Users\\thatOneDroid\\Documents\\NetBeansProjects\\GutenbergProject\\BooksAll";
    String[] matches = new String[]{"author:", "editor", "release", "posting", "au[thorn]or"};
    int count = 0;

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        ReadFiles2 rd = new ReadFiles2();
        rd.CityScanner();
        rd.readFiles();
        rd.writeToFile();
    }

    public String createString(String wordLong) {
        String[] arraynext2 = wordLong.split(" ");
        String newNextWord2 = "";

        for (int j = 0; j < arraynext2.length - 1; j++) {
            newNextWord2 += arraynext2[j] + " ";
        }

        return newNextWord2;

    }

    public void writeToFile() {
        FileWriter output;
        FileWriter output2;
        int writeBooks = 0;
        int writeCities = 0;

        boolean flag = true;
        try {
            output = new FileWriter(new File("books.csv"));
            output2 = new FileWriter(new File("cities.csv"));

            if (flag) {
                output.write("Number" + "," + "Title" + "," + "Author" + "," + "Book Number" + "\n");
                output2.write("Number" + "," + "City" + "," + "Lat" + "," + "Longt" + "\n");
                flag = false;
            }

            for (Map.Entry<Integer, Book> entry : books.entrySet()) {
                System.out.println("Write books  " + writeBooks++);
                output.write(entry.getKey() + "," + "\"" + entry.getValue().title + "\"" + "," + "\"" + entry.getValue().author + "\"" + "\n");

            }

            for (Map.Entry<City, Integer> entry : integerCitites.entrySet()) {
                System.out.println("Write city    " + writeCities++);

                System.out.println(entry.getValue() + "," + entry.getKey().getName() + "," + entry.getKey().getLat() + "," + entry.getKey().getLongt());
                output2.write(entry.getValue() + "," + entry.getKey().getName() + "," + entry.getKey().getLat() + "," + entry.getKey().getLongt() + "\n");
            }

            output.close();
            output2.close();

        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

    }

    public void readFiles() throws IOException {
        File folder = new File(filesLocation);
        File[] listOfFiles = folder.listFiles();
        Scanner input = null;
        boolean flagDoneIntro = true;
        String title = "";
        String author = "";
        int countWords = 0;
        int countWordsForAuthor = 1;
        int countWordsForTitle = 1;

        int readFilesCount = 0;
        int COUNTERBOOKS = 0;
        for (File fileName : listOfFiles) {
            if (fileName.isFile() && fileName.getName().endsWith(".txt")) {
                try {
                    File file = new File(filesLocation + "\\" + fileName.getName());

                    input = new Scanner(new File(filesLocation + "\\" + fileName.getName()));
                    System.out.println("Reading    " + fileName.getName() + " " + readFilesCount++);

                    flagDoneIntro = true;
                    title = "";
                    author = "";
                    countWordsForTitle = 0;
                    countWords = 200;
                    countWordsForAuthor = 0;
                    Book book = new Book();
                    COUNTERBOOKS++;
                    books.put(COUNTERBOOKS, book);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ReadFiles2.class.getName()).log(Level.SEVERE, null, ex);
                }
                while (input.hasNext()) {

                    String word = input.next();

                    if (stringCities.get(word) != null) {
                        City city = stringCities.get(word);
                        integerCitites.put(city, COUNTERBOOKS);

                    }
                    if (flagDoneIntro) {
                        countWords--;
                        if (word.toLowerCase().contains("***") && countWords <= 0) {
                            if (title.isEmpty()) {
                                System.out.println("Book" + fileName.getName() + " has no title");
                                title = "No Existing Title " + count;
                                count++;
                                Book b = books.get(COUNTERBOOKS);
                                b.setTitle(title);

                            } else {
                                String booktitle = createString(title);
                                Book b = books.get(COUNTERBOOKS);
                                b.setTitle(booktitle);
                                System.out.println("Book" + fileName.getName() + " has a title!!");

                            }

                            if (author.isEmpty()) {
                                author = "No Existing Author ";
                                books.get(COUNTERBOOKS).setAuthor(author);
                                System.out.println("Book" + fileName.getName() + " has no author");
                            } else {
                                String bookAuthor = createString(author);
                                books.get(COUNTERBOOKS).setAuthor(bookAuthor);
                                System.out.println("Book" + fileName.getName() + " has a author!!");
                            }
                            flagDoneIntro = false;

                        } else {
                            if (word.toLowerCase().contains("title:")) {

                                while ((!title.toLowerCase().contains(matches[0]) && (!title.toLowerCase().contains(matches[1]) && !title.toLowerCase().contains(matches[4]))) && countWordsForTitle <= 30) {
                                    if (input.hasNext()) {
                                        title += input.next() + " ";
                                        countWordsForTitle++;
                                    }

                                }

                                while ((!author.toLowerCase().contains(matches[2]) && !author.toLowerCase().contains(matches[3])) && countWordsForAuthor <= 30) {
                                    if (input.hasNext()) {
                                        author += input.next() + " ";
                                        countWordsForAuthor++;
                                    }

                                }
                            }

                        }

                    }
                }
            }
        }
    }

    public void CityScanner() throws FileNotFoundException, IOException, ParseException {
        BufferedReader br = null;
        FileReader fr = null;
        fr = new FileReader("C:\\Users\\thatOneDroid\\Documents\\NetBeansProjects\\Awesome\\cities15000.txt");
        br = new BufferedReader(fr);
        String sCurrentLine;
        int cityScanCount = 0;
        if ((sCurrentLine = br.readLine()) != null) {

        }

        while ((sCurrentLine = br.readLine()) != null) {
            String[] line = sCurrentLine.split("\t");
            City city = new City();
            city.setName(line[2]);
            String number = line[4];
            String number2 = line[5];
            double value = Double.parseDouble(number.replace(".", "."));
            double value2 = Double.parseDouble(number2.replace(".", "."));
            city.setLat(value);
            city.setLongt(value2);
            stringCities.put(city.getName(), city);
        }
    }
}
