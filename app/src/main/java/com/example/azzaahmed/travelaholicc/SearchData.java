package com.example.azzaahmed.travelaholicc;

import java.io.Serializable;

/**
 * Created by azza ahmed on 10/16/2016.
 */
public class SearchData implements Serializable {
    private String countryFrom;
    private String countryTo;
    private String dateFrom;
    private String dateTo;
    private String cityFrom;
    private String cityTo;

    private int adultNumber;
    private int childrenNumber;
//    private int classType;

    private String roomCapacity;
    private double starRate;
    private double budget;
    private int roomNumber;
    private int nights;

    boolean bussiness;
    boolean first_class;
    boolean economy;
    boolean flightsOnly;
    boolean hotelsOnly;

    public SearchData(String countryFrom, String countryTo, String dateFrom, String dateTo, String cityFrom, String cityTo, int adultNumber, int childrenNumber, int classType, double starRate, int roomNumber, boolean flightsOnly, boolean hotelsOnly, boolean economy,boolean bussiness, boolean first_class,int nights, double budget) {
        this.countryFrom = countryFrom;
        this.countryTo = countryTo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.adultNumber = adultNumber;
        this.childrenNumber = childrenNumber;
//        this.classType = classType;
        //this.roomCapacity = roomCapacity;
        this.starRate = starRate;
        this.roomNumber = roomNumber;
        this.flightsOnly=flightsOnly;
        this.hotelsOnly=hotelsOnly;

        this.bussiness=bussiness;
        this.first_class=first_class;
        this.economy=economy;

        this.nights=nights;

        this.budget = budget;
    }

    public String getCountryFrom() {
        return countryFrom;
    }

    public String getCountryTo() {
        return countryTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getCityFrom() {
        return cityFrom;
    }

    public String getCityTo() {
        return cityTo;
    }

    public int getAdultNumber() {
        return adultNumber;
    }

    public int getChildrenNumber() {
        return childrenNumber;
    }

//    public int getClassType() {
//        return classType;
//    }

    public String getRoomCapacity() {
        return roomCapacity;
    }

    public double getStarRate() {
        return starRate;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getNights() { return nights; }

    public double getBudget() { return budget; }
}
