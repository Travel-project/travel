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

    public Double getPrice() {
        return price;
    }

    public String getCountry() {
        return country;
    }

    private String city;
    String startdate;
    String enddate;
    String night;
    String room;
    String currency;
   private Double price;
    String regional;
    String image_path;
    double rate;
    int adult;
    int child;

    Hotel(String city,String currency,Double price,String image_path,String regional,double rate){
        this.city=city;
        this.currency=currency;
        this.price=price;
        this.image_path=image_path;
        this.rate=rate;
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
