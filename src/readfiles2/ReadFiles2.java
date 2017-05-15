package readfiles2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadFiles2 {

    Map<City, Integer> integerCitites = new HashMap<>();//where they should go 
    Map<Integer,Book> books = new HashMap<>();
    Map<String, City> stringCities = new HashMap<>();//all cities
    String filesLocation = "C:\\Users\\yoyo\\Desktop\\BooksDB";
    String[] matches = new String[] {"author", "editor","release","posting","au[thorn]or"};
    int count = 0;
    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        ReadFiles2 rd = new ReadFiles2();
        rd.CityScanner();
        rd.readFiles();
        rd.writeToFile();
    }
    
    
       public String createString(String wordLong/*, String keyWord, String keyword2*/) {
        String[] arraynext2 = wordLong.split(" ");     
        String newNextWord2 = "";
       // if (arraynext2[arraynext2.length - 1].toLowerCase().contains(keyWord) || arraynext2[arraynext2.length - 1].toLowerCase().contains(keyword2)) {

            for (int j = 0; j < arraynext2.length - 1; j++) {
                newNextWord2 += arraynext2[j] + " ";
            }
       // }
        return newNextWord2;

    }
       
       
           public void writeToFile() {
        FileWriter output;
        FileWriter output2;
     
        boolean flag = true;
        try {
            output = new FileWriter(new File("books"));
            output2 = new FileWriter(new File("cities"));
       
            if(flag)
            {
                  output.write("Number"+","+"Title"+","+"Author"+"\n");
                  output2.write("Number"+","+"City"+","+"Lat"+","+"Longt"+"\n");                              
                  flag = false;
            }

          //  System.out.println(books.size());
          for (Map.Entry<Integer, Book> entry : books.entrySet()) 
          {
                output.write(entry.getKey()+","+"\""+entry.getValue().title+"\""+","+entry.getValue().author+"\n");
                //////////////////////////////////
//                            String info = words.get(i).getTitle()+","+words.get(i).getAuthor()+","; 
//                            info = info + "\"";
//                            boolean flag4 = true;
//                            String firstCity = "";
//              
//                   for (Map.Entry<String, City> entry : words.get(i).getCities().entrySet())
//                      {
// 
//                              if(flag4)
//                                 {   
//
//                                        info += entry.getKey();
//                                        flag4 = false;
//                                 }else
//                                 {
//                                     info+= ","+entry.getValue().getName();
//                                 }
//                      }
//
//                     info+="\"";
//                     output2.write(info+"\n");
//                     /////////////////////////////////////////////////////
//                              for (Map.Entry<String, City> entry : words.get(i).getCities().entrySet())
//                      {
//                           String info2 = words.get(i).getTitle()+","+words.get(i).getAuthor()+","; 
//                           info2 += entry.getValue().getName();
//                           info2+="\n";
//                           output3.write(info2);
//                          
//                      }
              ////////////////////////////////////////////////////////////////////////////////////
   
          }
                                     
//                 for (Map.Entry<City,Integer> entry : integerCitites.entrySet()) 
//          {
////                City city = entry.getValue();
//              System.out.println(entry.getValue()+","+entry.getKey().getName()+","+entry.getKey().getLat()+","+entry.getKey().getLongt());
//                output2.write(entry.getValue()+","+entry.getKey().getName()+","+entry.getKey().getLat()+","+entry.getKey().getLongt()+"\n");
//          }
                 
                for (Map.Entry<City,Integer> entry : integerCitites.entrySet()) 
            {
//                City city = entry.getValue();
                System.out.println(entry.getValue() + "," + entry.getKey().getName() + "," + entry.getKey().getLat() + "," + entry.getKey().getLongt());
                output2.write(entry.getValue() + "," + entry.getKey().getName() + "," + entry.getKey().getLat() + "," + entry.getKey().getLongt() + "\n");
            }
                 
                 
   
            output.close();
            output2.close();
         
        } catch (Exception ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        //output.close();

    }
    
    
  public void readFiles() throws IOException {
        File folder = new File(filesLocation);
        File[] listOfFiles = folder.listFiles();
        //System.out.println(listOfFiles.length);
        Scanner input = null;
                    boolean flagDoneIntro =true ;
                    String title = "";
                    String author = "";
                    int countWords = 0;
                     int countWordsForAuthor = 1;
                    int countWordsForTitle = 1;
                    //Map<String,City> citiesforBook = null;
                    //        Book book = null;// we met the end of book introduction we can create a book object
                    
        int COUNTERBOOKS = 0;
        for (int i = 0; i < listOfFiles.length; i++) {
            File fileName = listOfFiles[i];
            if (fileName.isFile() && fileName.getName().endsWith(".txt")) {
                    // System.out.println("the name of the file  " + fileName.getName());
                try {
                   // System.out.println("I ma here");
                    File file = new File(filesLocation + "\\" + fileName.getName());
//                    BufferedReader br = null;
//                    FileReader fr = null;
//                    fr = new FileReader("C:/Users/yoyo/Desktop/BooksDB/40121.txt");
//                    br = new BufferedReader(fr);
//                    String sCurrentLine ="";
//                     if ((sCurrentLine = br.readLine()) != null) {
//                         System.out.println(sCurrentLine);
//                       }

                    input = new Scanner(new File(filesLocation + "\\" + fileName.getName()));   
                   // input = new Scanner(file);
                   // System.out.println( fileName.getName());
                    
                    flagDoneIntro = true;
                    title="";
                    author = "";
                    countWordsForTitle=0;
                    countWords = 200;
                    countWordsForAuthor = 0;
                   // integerCitites = new HashMap<>();
                    Book book = new Book();
                    COUNTERBOOKS++;
                   // book.setCities(citiesforBook);
                    books.put(COUNTERBOOKS,book); 
                  //  System.out.println(books.size());
                   // System.out.println("THE SIZE OF WORDS IS "+words.size());
                  //  book = new Book();
                    //reading       
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ReadFiles2.class.getName()).log(Level.SEVERE, null, ex);
                }
                
               // System.out.println(input.hasNext());
                while (input.hasNext() ) {

                    String word = input.next();
                        //   System.out.println(word);
                        
                      if(stringCities.get(word) != null)
                      {
                       //   City city = new City();
                          City city = stringCities.get(word);
                        //  city.setName(cityBuilder.getName());
                         // city.setLat(cityBuilder.getLat());
                          //city.setLongt(cityBuilder.getLongt());
                         // System.out.println(word);
                         // System.out.println(city.getName());
                        ///  citiesforBook.put(word, city);
                          // words.get(words.size() - 1).getCities().put(word, city);
                          
                          integerCitites.put(city,COUNTERBOOKS);
                         //System.out.println(integerCitites.size());
                          
                      }
                    //if it is a city do this 
                    
                    //try in another method catch title and author
                    
                   // System.out.println(word);
                   
                    if (flagDoneIntro)
                    {
                         countWords--; // maybe move only for reading title and author
                        if (word.toLowerCase().contains("***") && countWords<=0) 
                        {
                          // Book book = new Book();
                        //    Sysem.out.println("Size of countWords is "+countWords);
                           // System.out.println(" I am in");
                            if (title.isEmpty()) 
                            {
                               //System.out.println("A book without a title " + count);
                                title = "No Existing Title " + count;
                                count++;
                                //book.setTitle(nextWord);
                               Book b =   books.get(COUNTERBOOKS);
                               b.setTitle(title);
                               
                            }else
                            {
                                 // if(!nextWord.isEmpty())
                               // {
                                  // System.out.println("Title is "+nextWord);
                                   String booktitle = createString(title);
                               //    System.out.println("title "+booktitle);
                                 //  book.setTitle(title);
                                 //Integer idbook = books.size() - 1;
                               Book b =  books.get(COUNTERBOOKS);
                               b.setTitle(booktitle);
                              
                                //}
                            }
                            
                            if (author.isEmpty()) 
                            {
                               // System.out.println("A book without an author ");
                                author = "No Existing Author ";
                                books.get(COUNTERBOOKS).setAuthor(author);
                                //book.setAuthor(nextWord2);
                            }else
                            {
                              //  System.out.println("Author is "+nextWord2);
                                String bookAuthor = createString(author);
                                  //System.out.println("author "+bookAuthor);
                                books.get(COUNTERBOOKS).setAuthor(bookAuthor);
                               // book.setAuthor(author);
                            }
                           // words.add(book);
                           // words.get(words.size() - 1).setCities(citiesBook);
                            flagDoneIntro = false;
                           // System.out.print(flagDoneIntro+ " "+countWords);
                            
                        } else {
                            if (word.toLowerCase().contains("title")) {
 
                                
                  
                                 while ((!title.toLowerCase().contains(matches[0]) && (!title.toLowerCase().contains(matches[1]) && !title.toLowerCase().contains(matches[4]))) && countWordsForTitle<=20)
                                 {
                                   //  System.out.println("IN WHILE");
                                    if (input.hasNext() )//exclusive or 
                                    {
                                         // System.out.println("IN IF");
                                        title += input.next() + " ";
                                        countWordsForTitle++;
                                       // countWordsForTitle++;
                                      // System.out.println(" title| "+title);

                                    }
//
                                }
                               
                                while ((!author.toLowerCase().contains(matches[2]) && !author.toLowerCase().contains(matches[3])) && countWordsForAuthor<=20) 
                                {

                                    if (input.hasNext()) 
                                    {
                                        author += input.next() + " ";
                                        countWordsForAuthor++;
                                       //  System.out.println(" author| "+nextWord2); 
                                    }

                                }
                            }

                        }

                    }
                }//while
             //   System.out.println("SIze of hashmap while "+integerCitites.size());
                //System.out.println(integerCitites.size());
//                       for (Map.Entry<City,Integer> entry : integerCitites.entrySet()) 
//          {
////                City city = entry.getValue();
//              System.out.print(entry.getValue()+","+entry.getKey().getName()+","+entry.getKey().getLat()+","+entry.getKey().getLongt()+"\n");
//          }
            }//if
   //   System.out.println("SIze of hashmap if "+integerCitites.size());
        }//for
//                            for (Map.Entry<City,Integer> entry : integerCitites.entrySet()) 
//          {
////                City city = entry.getValue();
//              System.out.print(entry.getValue()+","+entry.getKey().getName()+","+entry.getKey().getLat()+","+entry.getKey().getLongt()+"\n");
//          }
//System.out.println("SIze of hashmap for "+integerCitites.size());
  }
    public void CityScanner() throws FileNotFoundException, IOException, ParseException {
        BufferedReader br = null;
        FileReader fr = null;
        fr = new FileReader("C:/Users/yoyo/Desktop/CitiesDB/cities5000.txt");
        br = new BufferedReader(fr);
        String sCurrentLine;
        if ((sCurrentLine = br.readLine()) != null) {

        }

        while ((sCurrentLine = br.readLine()) != null) {
            String[] line = sCurrentLine.split("\t");
            City city = new City();
            city.setName(line[2]);   
            String number = line[4];
            String number2= line[5];
            double value = Double.parseDouble( number.replace(".",".") );
            double value2 = Double.parseDouble( number2.replace(".",".") );
            city.setLat(value);
            city.setLongt(value2);
            stringCities.put(city.getName(), city);
        }
//                    for(int p = 0; p<stringCities.size(); p++)
//                   {
//                   
//
//                        for (Map.Entry<String, City> entry : stringCities.entrySet())
//                      {
//                           System.out.print(entry.getKey() + "," + entry.getValue().getLat()+","+entry.getValue().getLongt());
//                           System.out.println("");
//                      }
//                       
//                   }
    }
}
