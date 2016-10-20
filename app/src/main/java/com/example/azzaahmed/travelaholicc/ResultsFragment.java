package com.example.azzaahmed.travelaholicc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A placeholder fragment containing a simple view.
 */
public class ResultsFragment extends Fragment {

    private static final String TAG = "ResultsActivity";
    SearchData searchData;
    //    private ArrayAdapter<String> ResultAdapter;
//    ListView listView;
//    ArrayList<String> videoArray = new ArrayList<String>();
    ArrayList<Flight> flightArrayList = new ArrayList<>();
    ArrayList<Hotel> hotelArrayList = new ArrayList<>();
    ArrayList<HotelFlight> HotelFlightList=new ArrayList<>();
  //  ArrayAdapter<String> videoAdapter;
    RequestQueue requestQueue;
  TextView notFound;
    Context context;
    boolean FetchHotelFlag=false;
    boolean FetchFlightFlag=false;
    boolean FetchedData=false;
    View rootView;
    double budget;
    boolean checkFetchWhatArray[]=new boolean[2];
    private Bundle extras;
    ProgressDialog progress;
    public ResultsFragment() {
    }
    // start to change ********************************
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View rootView =inflater.inflate(R.layout.fragment_results, container, false);
        rootView = inflater.inflate(R.layout.fragment_results, container, false);
   notFound= (TextView) rootView.findViewById(R.id.notFound);
         progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.show();
        progress.setCancelable(false);

        requestQueue = Volley.newRequestQueue(getActivity());

        Intent intent=getActivity().getIntent();
        Bundle b=intent.getExtras();
        searchData= (SearchData) b.get("search_data");

        if(intent!=null && searchData!=null){

            Log.d(TAG, "data reveiverd : "+ receivedDataFromIntent() );

            checkFetchWhatArray[0]=searchData.flightsOnly;
            checkFetchWhatArray[1]=searchData.hotelsOnly;
      //      checkFetchWhatArray= getArguments().getBooleanArray("checkFetchWhat");
            if(checkFetchWhatArray[0]&&!checkFetchWhatArray[1])
                requestFlight();
            else if(!checkFetchWhatArray[0]&&checkFetchWhatArray[1])
                requestHotel();
            else {
                requestFlight();
                requestHotel();
            }
             budget =searchData.getBudget();
        }
//        requestFlight();
// requestHotel();

//        HotelFlight obj1=new HotelFlight(1202133,"path","movenpick",3.5,12530,2,"Flydubai F212",(double)3000);
//        HotelFlight obj2=new HotelFlight(1202133,"path","hilton",4,500,1,"Misr Airlines 999",(double)5000);
//        HotelFlight obj3=new HotelFlight(8902133,"path","fourseason",4,200,2,"Emirates E1231",4500.0);
//        HotelFlight obj4=new HotelFlight(127772143,"path","catarakt",3,600,3,"Flydubai F949",2600.0);
//        HotelFlight[] hotels = {obj1,obj2,obj3,obj4};
//        ListAdapter HotelAdapter = new ResultsAdapter(getContext(), hotels);
//        ListView HotelsListView = (ListView)rootView.findViewById(R.id.listview_results);
//        HotelsListView.setAdapter(HotelAdapter);
//
//        HotelsListView.setOnItemClickListener(
//                new AdapterView.OnItemClickListener(){
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        String food = String.valueOf(parent.getItemAtPosition(position));
//                        //Toast.makeText(MainActivity.this, food, Toast.LENGTH_LONG).show();
//                    }
//                }
//        );


        return rootView;


    }

    public void requestHotel(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Utility.URL_TIKET_TOKEN_REQ, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Utility.TIKET_TOKEN_HOTELS=response.getString("token");
                    continueRequestRoom();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                videoAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                progress.dismiss();
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    public void continueRequestRoom()
    {
//        String url = Utility.setUrlTiketHotelsReq("Indonesia", "2012-11-11", "2012-11-12", 1, 2, 2, 0,"usd"); // *** these data will be obtained from the search ***
        String url = Utility.setUrlTiketHotelsReq(searchData.getCountryTo(),searchData.getDateFrom(),searchData.getDateTo(),searchData.getNights(),searchData.getRoomNumber(), searchData.getAdultNumber(), searchData.getChildrenNumber(),"usd"); // *** these data will be obtained from the search ***
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject results = response.getJSONObject("results");
                    JSONArray result = results.getJSONArray("result");
                    Hotel hotel;
                    //JSONArray result = JSONObject.get("result");
                    for(int i=0 ; i<result.length();i++){
                        hotel=createHotel(result,i);
                        hotelArrayList.add(hotel);
//                        videoArray.add(result.getJSONObject(i).getString("province_name"));
                    }
                    FetchHotelFlag=true;
               //     if(FetchHotelFlag&&FetchFlightFlag) ViewUpdate();
                 if(checkFetchWhatArray[1]&&!checkFetchWhatArray[0]) ViewHotels();

                 else if(checkFetchWhatArray[1]&&checkFetchWhatArray[0]) {

                     if(FetchHotelFlag&&FetchFlightFlag) ViewUpdate();
                 }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
           //     videoAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                progress.dismiss();
            }
        });
        ///////// toast btala3 null exception////////////////////////////////////////////
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    public void requestFlight()
    {
        // MOW : Moscow
        // KHT : Khost
        String url;
       if ((searchData.bussiness&&searchData.first_class) || (searchData.bussiness&&searchData.economy) || (searchData.first_class&&searchData.economy))
          url = Utility.setUrlTravelpayoutsFlightReqCheap_NoClassType(searchData.getCityFrom(), searchData.getCityTo(), searchData.getDateFrom(), searchData.getDateTo(), "USD"); // *** these data will be obtained from the search ***
        else
            url = Utility.setUrlTravelpayoutsFlightReqCheap(searchData.getCityFrom(),searchData.getCityTo(),searchData.getDateFrom(),searchData.getDateTo(),"USD",getTripClass()); // *** these data will be obtained from the search ***
//http://api.travelpayouts.com/v1/prices/calendar?depart_date=2016-11&origin=MOW&destination=BCN&calendar_type=departure_date&currency=usd&token=52fcf0bf3f56f373ea453dc15c88b159
     //   url = Utility.setUrlTravelpayoutsFlightReqCheap_Month(searchData.getCityFrom(),searchData.getCityTo(),searchData.getDateFrom(),searchData.getDateTo(),"USD"); // *** these data will be obtained from the search ***

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Flight flight;
                    JSONArray data = response.getJSONArray("data");
                    //JSONObject flights = data.getJSONObject("HKT"); // where KHT is the destnation city
                    for(int i=0 ; i<data.length();i++){
                        flight = createFlight(data,i);
                        flightArrayList.add(flight);
//                    videoArray.add(flight.getPrice()+"");
//                    videoArray.add(flights.getJSONObject("1").getString("airline"));
//                    videoArray.add(flights.getJSONObject("2").getString("airline"));
                    }
                    FetchFlightFlag=true;
          //      if(FetchHotelFlag&&FetchFlightFlag) ViewUpdate();
                    if(checkFetchWhatArray[0]&&!checkFetchWhatArray[1]) ViewFlights();

                    else if(checkFetchWhatArray[1]&&checkFetchWhatArray[0]) {

                        if(FetchHotelFlag&&FetchFlightFlag) ViewUpdate();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
           //     videoAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
                progress.dismiss();
                //////toastyyyy//////
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }
//http://api.travelpayouts.com/v1/prices/calendar?depart_date=2016-11&origin=MOW&destination=BCN&calendar_type=departure_date&currency=usd&token=52fcf0bf3f56f373ea453dc15c88b159
    //http://api.travelpayouts.com/v1/prices/cheap?origin=MOW&destination=HKT&depart_date=2016-11-11&return_date=2016-12-11&currency=USD&token=52fcf0bf3f56f373ea453dc15c88b159

    private Flight createFlight(JSONArray data,int index) throws JSONException {
         String origin;
         String destination;
         String depart_date;
         String return_date;
         String currency;
         double price;
         String trip_class;
            String Airline;
        String FlightNumber;
        origin = data.getJSONObject(index).getString("origin");
        destination = data.getJSONObject(index).getString("destination");
        depart_date = data.getJSONObject(index).getString("depart_date");
        return_date = data.getJSONObject(index).getString("return_date");
//        depart_date = data.getJSONObject(index).getString("departure_at");
//        return_date = data.getJSONObject(index).getString("return_at");
//        currency = data.getJSONObject(index).getString("currency");
        currency = "USD";
        price = data.getJSONObject(index).getDouble("value");
      //  price = data.getJSONObject(index).getDouble("price");
       trip_class = data.getJSONObject(index).getString("trip_class");
          //  trip_class="0";
//            Airline= data.getJSONObject(index).getString("airline");
//             FlightNumber=data.getJSONObject(index).getString("flight_number");
//
       // return new Flight(origin,destination,depart_date,return_date,currency,price,Integer.parseInt(trip_class),Airline,FlightNumber);
        return new Flight(origin,destination,depart_date,return_date,currency,price,Integer.parseInt(trip_class),"","");

    }

    private Hotel createHotel(JSONArray results,int index) throws JSONException {
        String city;
//        String startdate;
//        String enddate;
//        String night;
//        String room;
       String name;
        String currency;
        double price;
        String image_path;
        String regional;
        String rate;
        String rating;
//        int adult;
//        int child;

        currency = "USD";
        city = results.getJSONObject(index).getString("province_name");
        rate = results.getJSONObject(index).getString("star_rating");
        price = results.getJSONObject(index).getDouble("total_price");
        regional = results.getJSONObject(index).getString("regional");
        image_path = results.getJSONObject(index).getString("photo_primary");
        name=results.getJSONObject(index).getString("name");
        rating=results.getJSONObject(index).getString("rating");
        return new Hotel(name,city,currency,price,image_path,regional,rate,rating);

    }
    //private void ViewUpdate(String FetchWhat ){
private void ViewUpdate( ){
   //     while(!FetchedData){
            //if(FetchHotelFlag&&FetchFlightFlag){
               // FetchedData=true;
    //if(FetchWhat.equals("HotelsAndFlights")) {
        Collections.sort(hotelArrayList);
        Collections.sort(flightArrayList);
        Log.d("array check fetch", "in create" + hotelArrayList.size() + "   " + flightArrayList.size());
        for (int i = 0; i < hotelArrayList.size(); i++) {

            for (int j = 0; j < flightArrayList.size(); j++) {
                if (hotelArrayList.get(i).getPrice() + flightArrayList.get(j).getPrice() <= budget) {
                    //  HotelFlightList.add(new HotelFlight(HotelList.get(i).getHotel_id(),HotelList.get(i).getImage_path(),HotelList.get(i).getName(),HotelList.get(i).getRate(),HotelList.get(i).getPrice(),HotelList.get(i).getRoom_capacity(),FlightList.get(j).getFlightName(),FlightList.get(j).getFlightPrice()));
                    HotelFlightList.add(new HotelFlight(flightArrayList.get(j), hotelArrayList.get(i)));
                    Log.d("array check fetch", "in loop add");
                } else {
                    j = flightArrayList.size();
                }
            }
        }
    progress.dismiss();

    Collections.sort(HotelFlightList);
    if(HotelFlightList.size()==0) notFound.setText("no data found");
    else {
        ListAdapter HotelAdapter = new ResultsAdapter(getContext(), HotelFlightList);
        ListView HotelsListView = (ListView) rootView.findViewById(R.id.listview_results);
        HotelsListView.setAdapter(HotelAdapter);


        HotelsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        //Toast.makeText(MainActivity.this, food, Toast.LENGTH_LONG).show();
                    }
                }
        );

            }
}

    private void ViewHotels( ) {
        ArrayList<Hotel> hotelNewList = new ArrayList<>();
        Collections.sort(hotelArrayList);

        Log.d("array check fetch", "in create" + hotelArrayList.size());

        progress.dismiss();
        for (int i = 0; i < hotelArrayList.size(); i++) {
            if (hotelArrayList.get(i).getPrice() <= budget)
                hotelNewList.add(hotelArrayList.get(i));
            else i = hotelArrayList.size();
        }

        if (hotelNewList.size() == 0) notFound.setText("no data found");
            // ListAdapter HotelAdapter = new HotelAdapter(getContext(),hotelArrayList);
        else {
            ListAdapter HotelAdapter = new HotelAdapter(getContext(), hotelNewList);
            ListView HotelsListView = (ListView) rootView.findViewById(R.id.listview_results);
            HotelsListView.setAdapter(HotelAdapter);

            HotelsListView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String food = String.valueOf(parent.getItemAtPosition(position));
                            //Toast.makeText(MainActivity.this, food, Toast.LENGTH_LONG).show();
                        }
                    }
            );

        }

    }
    private void ViewFlights() {
        ArrayList<Flight> flightNewList = new ArrayList<>();
        Collections.sort(flightArrayList);
        progress.dismiss();
        Log.d("array check fetch", "in create" + flightArrayList.size());

        for (int i = 0; i < flightArrayList.size(); i++) {
            if (flightArrayList.get(i).getPrice() <= budget)
                flightNewList.add(flightArrayList.get(i));
            else i = flightArrayList.size();
        }
        if (flightNewList.size() == 0) notFound.setText("no data found");
            //   ListAdapter HotelAdapter = new FlightAdapter(getContext(),flightArrayList);
        else {
            ListAdapter HotelAdapter = new FlightAdapter(getContext(), flightNewList);
            ListView HotelsListView = (ListView) rootView.findViewById(R.id.listview_results);
            HotelsListView.setAdapter(HotelAdapter);

            HotelsListView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String food = String.valueOf(parent.getItemAtPosition(position));
                            //Toast.makeText(MainActivity.this, food, Toast.LENGTH_LONG).show();
                        }
                    }
            );

        }
    }
    private int getTripClass(){
//    trip_class — the flight class:
//    0 — The economy class (the default value);
//    1 — The business class;
//    2 — The first class.
        if(searchData.bussiness)
            return 1;
        else if(searchData.economy)
            return 0;
        else if(searchData.first_class)
            return 2;
        else return 0;
    }

    private String receivedDataFromIntent(){
        return searchData.getCountryFrom()+","+searchData.getCountryTo()+"\n"+searchData.getDateFrom()+","+searchData.getDateTo()+"\n"
                +searchData.getCityFrom()+","+searchData.getCityTo()+"\n"+searchData.getBudget()+"\n"+searchData.flightsOnly+","+searchData.hotelsOnly+
                "\n"+searchData.first_class+","+searchData.economy+","+searchData.bussiness;
    }
        }
   // }}


