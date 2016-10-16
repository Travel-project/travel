package com.example.azzaahmed.travelaholicc;

/**
 * Created by azza ahmed on 10/16/2016.
 */
public class SearchData {
    private String countryFrom;
    private String countryTo;
    private String dateFrom;
    private String dateTo;
    private String cityFrom;
    private String cityTo;
    private String adultNumber;
    private String childrenNumber;
    private String classType;

   private String roomCapacity;
    private String starRate;
    private String roomNumber;

    public SearchData(String countryFrom, String countryTo, String dateFrom, String dateTo, String cityFrom, String cityTo, String adultNumber, String childrenNumber, String classType, String roomCapacity, String starRate, String roomNumber) {
        this.countryFrom = countryFrom;
        this.countryTo = countryTo;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.adultNumber = adultNumber;
        this.childrenNumber = childrenNumber;
        this.classType = classType;
        this.roomCapacity = roomCapacity;
        this.starRate = starRate;
        this.roomNumber = roomNumber;
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

    public String getAdultNumber() {
        return adultNumber;
    }

    public String getChildrenNumber() {
        return childrenNumber;
    }

    public String getClassType() {
        return classType;
    }

    public String getRoomCapacity() {
        return roomCapacity;
    }

    public String getStarRate() {
        return starRate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}
