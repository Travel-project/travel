package com.example.azzaahmed.travelaholicc;

import java.io.Serializable;

/**
 * Created by azza ahmed on 10/12/2016.
 */
public class HotelFlight implements Serializable , Comparable <HotelFlight>

    {
        private Flight flight;
        private Hotel hotel;
        private double totalPrice;
//        private int Hotel_id;
//        private String image_path;
//        private String Name;
//        private double rate;
//        private double price;
//        private int available_rooms;
//        private int room_capacity;
//        String FlightName;
//        Double FlightPrice;

//        HotelFlight(int Hotel_id, String image_path, String Name, double rate, double price, int room_capacity, String FlightName, Double FlightPrice){
//        this.Hotel_id=Hotel_id;
//        this.image_path=image_path;
//        this.rate=rate;
//        this.Name=Name;
//        this.price=price;
//        this.room_capacity=room_capacity;
//        this.FlightName=FlightName;
//        this.FlightPrice=FlightPrice;
//    }
        HotelFlight(Flight flight,Hotel hotel){
            this.flight=flight;
            this.hotel=hotel;
            totalPrice= flight.getPrice()+hotel.getPrice();
        }

//        public int getHotel_id() {
//            return Hotel_id;
//        }
//
//        public String getImage_path() {
//            return image_path;
//        }
//
//        public double getRate() {
//            return rate;
//        }
//
//        public int getAvailable_rooms() {
//            return available_rooms;
//        }
//
//        public int getRoom_capacity() {
//            return room_capacity;
//        }

        public double getHotelPrice() {
           // return price;
            return hotel.getPrice();
        }

        public String getName() {
            return hotel.getName();
         // return   Name;
        }
        public String getFlightName() {

            //return FlightName;
            return  flight.getName();
        }

        public Double getFlightPrice() {

            //return FlightPrice;
            return flight.getPrice();
        }
        public String getImagePath() {

            //return FlightPrice;
            return hotel.getImage_path();
        }
        public String getStarRate() {
            return hotel.getRate()+"";

        }
        public String getRating() {
            return hotel.getRating();

        }
        @Override
        public int compareTo(HotelFlight another) {

            if(totalPrice==another.totalPrice) return 0;
            else if(totalPrice<another.totalPrice) return 1;
            else return -1;
        }
    }
