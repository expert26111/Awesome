package readfiles2;

/**
 *
 * @author yoyo && thatOneDroid
 */

public class City {

    private Double lat;
    private Double longt;
    private String name;

    @Override
    public String toString() {
        return "City{" + "lat=" + lat + ", longt=" + longt + ", name=" + name + '}';
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLongt() {
        return longt;
    }

    public void setLongt(Double longt) {
        this.longt = longt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        City rhs = (City) obj;
        return this.getName() == rhs.getName() && this.getLat() == rhs.getLat();

    }

}
