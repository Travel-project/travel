package com.example.azzaahmed.travelaholicc;

/**
 * Created by Dell on 13/10/16.
 */
public class Utility {

    public static String TRAVELPAYOUTS_TOKEN_FLIGHT = "52fcf0bf3f56f373ea453dc15c88b159";
    public static String URL_TRAVELPAYOUTS_FLIGHT_REQ_CHEAP = "http://api.travelpayouts.com/v2/prices/latest?"; //"http://api.travelpayouts.com/v1/prices/cheap?"

    public static String TIKET_TOKEN_HOTELS;
    public static String URL_TIKET_TOKEN_REQ = "http://api-sandbox.tiket.com/apiv1/payexpress?method=getToken&secretkey=a47d61aeeb25ef31c45502478967802f&output=json";
    public static String URL_TIKET_HOTELS_REQ = "http://api-sandbox.tiket.com/search/hotel?q=";

    public static String setUrlTravelpayoutsFlightReqCheap(String origin,String destination,String depart_date,String return_date,String currency, int trip_class) {
        URL_TRAVELPAYOUTS_FLIGHT_REQ_CHEAP = URL_TRAVELPAYOUTS_FLIGHT_REQ_CHEAP+"origin="+origin+"&destination="+destination+"&depart_date="+depart_date+"&return_date="+return_date+"&currency="+currency+"&trip_class="+trip_class+"&page=1&limit=30&sorting=price&token="+TRAVELPAYOUTS_TOKEN_FLIGHT;
        return URL_TRAVELPAYOUTS_FLIGHT_REQ_CHEAP;
    }
    public static String setUrlTiketHotelsReq(String country,String startdate,String enddate,int night,int rooms,int adult,int child) {
        URL_TIKET_HOTELS_REQ = URL_TIKET_HOTELS_REQ+country+"&startdate="+startdate+"&night="+night+"&enddate="+enddate+"&room="+rooms+"&adult="+adult+"&child="+child+"&token="+TIKET_TOKEN_HOTELS+"&output=json";
        return  URL_TIKET_HOTELS_REQ;
    }
}
