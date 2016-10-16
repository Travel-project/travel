package com.example.azzaahmed.travelaholicc;

import java.io.Serializable;

/**
 * Created by Dell on 13/10/16.
 */
public class Flight  implements Serializable, Comparable <Flight>
{

    private String origin;
    private String destination;
    private String depart_date;
    private String return_date;
    private String currency;
    private double price;



    private String airline;
    private String flight_number;
    private String image_path;
    private int trip_class;

//    trip_class — the flight class:
//    0 — The economy class (the default value);
//    1 — The business class;
//    2 — The first class.
    Flight(String origin, String destination, String depart_date, String return_date, String currency, Double price, int trip_class){
        this.origin=origin;
        this.destination=destination;
        this.depart_date=depart_date;
        this.return_date=return_date;
        this.currency=currency;
        this.price=price;
        this.trip_class=trip_class;
    }

    public double getPrice() {
        return price;
    }
    public String getName() {

        return origin+"  "+destination;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepart_date() {
        return depart_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAirline() {
        return airline;
    }

    public String getFlight_number() {
        return flight_number;
    }

    public String getImage_path() {
        return image_path;
    }

    public int getTrip_class() {
        return trip_class;
    }

    @Override
    public int compareTo(Flight another) {
        if(price==another.price) return 0;
        else if(price>another.price) return 1;
        else return -1;
    }
}
