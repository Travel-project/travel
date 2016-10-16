package com.example.azzaahmed.travelaholicc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by azza ahmed on 10/12/2016.
 */
public class ResultsAdapter extends ArrayAdapter<HotelFlight> {
    private Context context;

    ResultsAdapter(Context context,  ArrayList hotels ) {
        super(context, R.layout.result_list ,hotels);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      //  View customView = LayoutInflater.from(context).inflate(R.layout.result_list, parent, false);
        if (null == convertView) {
            convertView = LayoutInflater.from(context).inflate(R.layout.result_list, parent, false);
        }
        String HotelName = getItem(position).getName();
        String HotelPrice=Double.toString(getItem(position).getHotelPrice());

        String FlightName = getItem(position).getFlightName();
        String FlightPrice=Double.toString(getItem(position).getFlightPrice());
        TextView price = (TextView) convertView.findViewById(R.id.HotelPrice);
        TextView Name = (TextView) convertView.findViewById(R.id.HotelName);
        TextView Flightprice = (TextView) convertView.findViewById(R.id.FlightPrice);
        TextView Flightname = (TextView) convertView.findViewById(R.id.FlightName);
        //ImageView image = (ImageView) customView.findViewById(R.id.ssImage);
        Flightname.setText(FlightName);
        Flightprice.setText(FlightPrice);
        price.setText(HotelPrice);
        Name.setText(HotelName);
        //image.setImageResource(R.drawable.chunky);

//        Picasso.with(context)
//                .load(getItem(position).getImage_path()).resize(300, 450)
//                .into((ImageView) convertView.findViewById(R.id.HotelImage));
        return convertView;
    }
}
