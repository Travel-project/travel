package com.example.azzaahmed.travelaholicc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by azza ahmed on 10/16/2016.
 */
public class FlightAdapter extends ArrayAdapter<Flight> {
    private Context context;

    FlightAdapter(Context context,  ArrayList Flights ) {
        super(context, R.layout.result_list ,Flights);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //  View customView = LayoutInflater.from(context).inflate(R.layout.result_list, parent, false);
        if (null == convertView) {
          //  convertView = LayoutInflater.from(context).inflate(R.layout.flight_result_list, parent, false);
           convertView = LayoutInflater.from(context).inflate(R.layout.result_list, parent, false);
        }
        convertView.findViewById(R.id.hotelResultLayout).setVisibility(View.GONE);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,250
        );
        convertView.findViewById(R.id.ResultLayout).setLayoutParams(lp);
        String FlightName = getItem(position).getName();
        String FlightPrice="$"+Double.toString(getItem(position).getPrice());
       TextView Flightprice = (TextView) convertView.findViewById(R.id.FlightPrice);
        TextView Flightname = (TextView) convertView.findViewById(R.id.FlightName);
        //ImageView image = (ImageView) customView.findViewById(R.id.ssImage);
        Flightname.setText(FlightName);
        Flightprice.setText(FlightPrice);


        //image.setImageResource(R.drawable.chunky);

//        Picasso.with(context)
//                .load(getItem(position).getImagePath()).resize(300, 450)
//                .into((ImageView) convertView.findViewById(R.id.HotelImage));
        return convertView;
    }
}
