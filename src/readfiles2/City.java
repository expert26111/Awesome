/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readfiles2;

import java.math.BigDecimal;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author yoyo
 */
public class City 
{
    private Double lat;
    private Double longt;
    private String name;

//    public City(String name,double lat, double longt) {
//        this.lat = lat;
//        this.longt = longt;
//        this.name = name;
//    }

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
    public int hashCode()
    {
         int result=17;
            result=31*result;
            result=31*result+(name!=null ? name.hashCode():0);
            return result;
       // return new HashCodeBuilder().append(name).append(lat).append(longt).toHashCode();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        //return false;
        if(obj == null)
        {
            return false;
        }
        
        if(obj == this)
        {
            return true;
        }
        
        if(obj.getClass() != this.getClass())
        {
            return false;
        }
        
        City rhs = (City)obj;
        return this.getName() == rhs.getName() && this.getLat()==rhs.getLat();
      //  System.out.println(new EqualsBuilder().append(name, rhs.getName()).append(lat,rhs.getLat()).append(longt,rhs.getLongt()).isEquals());
       // return new EqualsBuilder().append(name, rhs.getName()).append(lat,rhs.getLat()).append(longt,rhs.getLongt()).isEquals();
        
    }
    
}
