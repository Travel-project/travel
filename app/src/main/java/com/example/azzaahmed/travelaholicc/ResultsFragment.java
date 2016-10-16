package com.example.azzaahmed.travelaholicc;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
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

//    private ArrayAdapter<String> ResultAdapter;
//    ListView listView;
//    ArrayList<String> videoArray = new ArrayList<String>();
    ArrayList<Flight> flightArrayList = new ArrayList<>();
    ArrayList<Hotel> hotelArrayList = new ArrayList<>();
    ArrayList<HotelFlight> HotelFlightList=new ArrayList<>();
  //  ArrayAdapter<String> videoAdapter;
    RequestQueue requestQueue;
//    String TOKEN;
    Context context;
boolean FetchHotelFlag=false;
    boolean FetchFlightFlag=false;
    boolean FetchedData=false;
    View rootView;
    double budget;
    boolean checkFetchWhatArray[]=new boolean[2];
    private Bundle extras;
    public ResultsFragment() {
    }
    // start to change ********************************
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //View rootView =inflater.inflate(R.layout.fragment_results, container, false);
        rootView = inflater.inflate(R.layout.fragment_results, container, false);
//        context=getActivity();
//        listView = (ListView) rootView.findViewById(R.id.listView);
//        videoAdapter= new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,videoArray);
////        listView.setAdapter(videoAdapter);

        requestQueue = Volley.newRequestQueue(getActivity());


         checkFetchWhatArray[0]=false;
        checkFetchWhatArray[1]=true;
  //      checkFetchWhatArray= getArguments().getBooleanArray("checkFetchWhat");
if(checkFetchWhatArray[0]&&!checkFetchWhatArray[1])
        requestFlight();
        else if(!checkFetchWhatArray[0]&&checkFetchWhatArray[1])
        requestHotel();
        else {requestFlight();
    requestHotel();}
         budget = 4000000;
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



//        ResultAdapter =
//                new ArrayAdapter<Hotel>(
//                        getActivity(), // The current context (this activity)
//                        R.layout.result_list, // The name of the layout ID.
//                        R.id., // The ID of the textview to populate.
//                        new ArrayList<String>());
//        // weekForecast);
//        ListView list = (ListView) rootView.findViewById(R.id.listview_forecast);
//        list.setAdapter(ForecastAdapter);
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                String forecast =ForecastAdapter.getItem(position);
//                Toast.makeText(getActivity(), forecast, Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(),DetailActivity.class).putExtra(Intent.EXTRA_TEXT,forecast);
//                startActivity(intent);
//            }
//        });
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
        String url = Utility.setUrlTiketHotelsReq("Indonesia", "2012-11-11", "2012-11-12", 1, 2, 2, 0); // *** these data will be obtained from the search ***
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
            }
        });
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
        String url = Utility.setUrlTravelpayoutsFlightReqCheap("MOW","HKT", "2012-11-11", "2012-11-12","USD",0); // *** these data will be obtained from the search ***
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
            }
        });
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjectRequest);
    }

    //http://api.travelpayouts.com/v1/prices/cheap?origin=MOW&destination=HKT&depart_date=2016-11-11&return_date=2016-12-11&currency=USD&token=52fcf0bf3f56f373ea453dc15c88b159

    private Flight createFlight(JSONArray data,int index) throws JSONException {
         String origin;
         String destination;
         String depart_date;
         String return_date;
         String currency;
         double price;
         String trip_class;

        origin = data.getJSONObject(index).getString("origin");
        destination = data.getJSONObject(index).getString("destination");
        depart_date = data.getJSONObject(index).getString("depart_date");
        return_date = data.getJSONObject(index).getString("return_date");
//        currency = data.getJSONObject(index).getString("currency");
        currency = "USD";
        price = data.getJSONObject(index).getDouble("value");
        trip_class = data.getJSONObject(index).getString("trip_class");

        return new Flight(origin,destination,depart_date,return_date,currency,price,Integer.parseInt(trip_class));

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
        double rate;
//        int adult;
//        int child;

        currency = "USD";
        city = results.getJSONObject(index).getString("province_name");
        rate = Double.parseDouble(results.getJSONObject(index).getString("star_rating"));
        price = results.getJSONObject(index).getDouble("total_price");
        regional = results.getJSONObject(index).getString("regional");
        image_path = results.getJSONObject(index).getString("photo_primary");
        name=results.getJSONObject(index).getString("name");

        return new Hotel(name,city,currency,price,image_path,regional,rate);

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




    private void ViewHotels( ){

        Collections.sort(hotelArrayList);

        Log.d("array check fetch", "in create" + hotelArrayList.size());
//        for (int i = 0; i < hotelArrayList.size(); i++) {
//
//                     hotelArrayList.add(new Hotel(hotelArrayList.get(i).getName(),hotelArrayList.get(i).getCity(),hotelArrayList.get(i).getCurrency(),hotelArrayList.get(i).getPrice(),hotelArrayList.get(i).getImage_path(),hotelArrayList.get(i).getCountry(),hotelArrayList.get(i).getRate()));
//                    Log.d("array check fetch", "hotel in loop add");
//
//            }


        ListAdapter HotelAdapter = new HotelAdapter(getContext(),hotelArrayList);
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


    private void ViewFlights( ){

        Collections.sort(flightArrayList);

        Log.d("array check fetch", "in create" + flightArrayList.size());

//        for (int i = 0; i < flightArrayList.size(); i++) {
//            flightArrayList.add(new Flight(flightArrayList.get(i).getOrigin(),flightArrayList.get(i).getDestination(),flightArrayList.get(i).getDepart_date(),flightArrayList.get(i).getReturn_date(),flightArrayList.get(i).getCurrency(),flightArrayList.get(i).getPrice(),flightArrayList.get(i).getTrip_class()));
//            Log.d("array check fetch ", " flight add= " + i);
//
//        }


        ListAdapter HotelAdapter = new FlightAdapter(getContext(),flightArrayList);
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
   // }}


