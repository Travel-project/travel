package com.example.azzaahmed.travelaholicc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class ResultsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




//        if (savedInstanceState == null) {
//            ResultsFragment results = new ResultsFragment();
//            results.setArguments(getIntent().getExtras());
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.ResultsContainer, results)
//                    .commit();
//        }


    }

}
