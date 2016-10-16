package com.example.azzaahmed.travelaholicc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by azza ahmed on 10/16/2016.
 */
public class HotelAdapter extends ArrayAdapter<Hotel>

    {
        private Context context;

        HotelAdapter(Context context,  ArrayList hotels ) {
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
            String HotelPrice=Double.toString(getItem(position).getPrice());

            TextView price = (TextView) convertView.findViewById(R.id.HotelPrice);
            TextView Name = (TextView) convertView.findViewById(R.id.HotelName);

            //ImageView image = (ImageView) customView.findViewById(R.id.ssImage);

            price.setText(HotelPrice);
            Name.setText(HotelName);
            //image.setImageResource(R.drawable.chunky);

        Picasso.with(context)
                .load(getItem(position).getImage_path()).resize(300, 450)
                .into((ImageView) convertView.findViewById(R.id.HotelImage));
            return convertView;
        }
    }

