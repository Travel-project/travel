package com.example.azzaahmed.travelaholicc;

import java.io.Serializable;

/**
 * Created by Dell on 13/10/16.
 */
public class Hotel implements Serializable, Comparable <Hotel>
{
   private String country;

    public String getCity() {
        return city;
    }



    public String getCountry() {
        return country;
    }

    private String city;
    String startdate;
    String enddate;
    String night;
    String room;
   private String currency;
   private Double price;
    String regional;



    private String image_path;

    public double getRate() {
        return rate;
    }

    public String getCurrency() {
        return currency;
    }

    private double rate;
    int adult;
    int child;
    private String name;

    public String getName() {
        return name;
    }
    public String getImage_path() {
        return image_path;
    }
    public Double getPrice() {
        return price;
    }

    Hotel(String name,String city,String currency,Double price,String image_path,String country,double rate){
        this.city=city;
        this.currency=currency;
        this.price=price;
        this.image_path=image_path;
        this.rate=rate;
        this.country=country;

        this.name=name;
    }

    @Override
    public int compareTo(Hotel another) {

        if(price==another.price) return 0;
        else if(price>another.price) return 1;
        else return -1;
    }

//    public void setRest(String startdate,String enddate){
//
//    }

}
