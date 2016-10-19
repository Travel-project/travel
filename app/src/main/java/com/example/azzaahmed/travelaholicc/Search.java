package com.example.azzaahmed.travelaholicc;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.mukesh.countrypicker.fragments.CountryPicker;
import com.mukesh.countrypicker.interfaces.CountryPickerListener;
import com.mukesh.countrypicker.models.Country;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.Calendar;

//import com.example.azzaahmed.travelaholicc.R;


public class Search extends Fragment implements
        DatePickerDialog.OnDateSetListener{

    SearchData searchData;
    //private TextView dateTextView;
    private EditText date_EditText_from;
    private EditText date_EditText_to;
    private EditText country_EditText_from;
    private EditText country_EditText_to;
    private EditText city_EditText_from;
    private EditText city_EditText_to;
    private EditText budget;

    private String date_from_value;
    private String date_to_value;

    private CheckBox economy;
    private CheckBox business;
    private CheckBox first_class;

    MaterialBetterSpinner materialBetterSpinner_adults ;
    MaterialBetterSpinner materialBetterSpinner_children ;
    String[] SPINNER_DATA_ADULTS = {"1","2","3","4","5","6"};
    String[] SPINNER_DATA_CHILDREN = {"0","1","2","3","4","5","6"};

    //private TextView mCountryNameTextView, mCountryIsoCodeTextView, mCountryDialCodeTextView;
    private ImageView mCountryFlagImageView_from;
    private ImageView mCountryFlagImageView_to;
    View view;
    //private Button mPickCountryButton;

    private Button mSearch;

    private CountryPicker mCountryPicker;

    private View mFlight;
    private View mHotels;

    boolean date_from=false;
    boolean date_to=false;
    boolean country_from=false;
    boolean country_to=false;
    boolean flight=true; // flight will enters the search;
    boolean hotel=true; // hotel will enters the search;

    int mShortAnimationDuration;


    public Search() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_search, container, false);
        //dateTextView = (TextView)view.findViewById(R.id.date_textview);
        //Button dateButton = (Button)view.findViewById(R.id.date_button);
        date_EditText_from = (EditText)view.findViewById(R.id.pick_date_from);
        date_EditText_to = (EditText)view.findViewById(R.id.pick_date_to);
        country_EditText_from = (EditText)view.findViewById(R.id.pick_country_from);
        country_EditText_to = (EditText)view.findViewById(R.id.pick_country_to);
        city_EditText_from = (EditText)view.findViewById(R.id.pick_city_from);
        city_EditText_to = (EditText)view.findViewById(R.id.pick_city_to);
        budget = (EditText)view.findViewById(R.id.budget);

        economy = (CheckBox)view.findViewById(R.id.economy);
        business = (CheckBox)view.findViewById(R.id.business);
        first_class = (CheckBox)view.findViewById(R.id.first_class);

        RadioGroup search_options = (RadioGroup)view.findViewById(R.id.options_group);

        date_EditText_from.setText("From: "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+Calendar.getInstance().get(Calendar.MONTH)+"/"+Calendar.getInstance().get(Calendar.YEAR));
        date_EditText_to.setText("To: "+Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+Calendar.getInstance().get(Calendar.MONTH)+"/"+Calendar.getInstance().get(Calendar.YEAR));

        date_from_value = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"-"+Calendar.getInstance().get(Calendar.MONTH)+"-"+Calendar.getInstance().get(Calendar.YEAR);
        date_to_value=Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"-"+Calendar.getInstance().get(Calendar.MONTH)+"-"+Calendar.getInstance().get(Calendar.YEAR);

        mCountryFlagImageView_from = (ImageView) view.findViewById(R.id.row_icon_from);
        mCountryFlagImageView_to = (ImageView) view.findViewById(R.id.row_icon_to);

        mSearch = (Button) view.findViewById(R.id.search);

// ************************************** Spinner ******************************************************************//
        materialBetterSpinner_adults = (MaterialBetterSpinner)
                view.findViewById(R.id.android_material_design_spinner_adults);
        materialBetterSpinner_children = (MaterialBetterSpinner)
                view.findViewById(R.id.android_material_design_spinner_children);
        ArrayAdapter<String> adapter_adults = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, SPINNER_DATA_ADULTS);
        ArrayAdapter<String> adapter_children = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, SPINNER_DATA_CHILDREN);
        materialBetterSpinner_adults.setAdapter(adapter_adults);
        materialBetterSpinner_children.setAdapter(adapter_children);

// ************************************* End Of Spinner ************************************************************//

        mFlight = view.findViewById(R.id.flights);
        mHotels = view.findViewById(R.id.hotels);
//        mBothFlightAndHotel = view.findViewById(R.id.both);
//        mFlight.setVisibility(View.GONE);//default both
//        mHotels.setVisibility(View.GONE);//default both
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mCountryPicker = CountryPicker.newInstance("Select Country");
        setListener();

        date_EditText_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Search.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                date_from = true;
                date_to = false;
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });

        date_EditText_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        Search.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                date_to=true;
                date_from=false;
                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
            }
        });

        search_options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                Toast.makeText(getActivity(), "checked id = " + checkedId, Toast.LENGTH_SHORT).show();

//                boolean checked = ((RadioButton) view).isChecked();
                // This check which radio button was clicked
                switch (checkedId) {
                    case R.id.flights_only:
//                        if (checked)
                        //Do something when radio button is clicked
                        flight=true; hotel=false;
//                        Toast.makeText(getActivity(), "Flights only", Toast.LENGTH_LONG).show();
                        showView();
                        break;

                    case R.id.hotels_only:
                        //Do something when radio button is clicked
                        flight=false; hotel=true;
//                        Toast.makeText(getActivity(), "Hotels only", Toast.LENGTH_LONG).show();
                        showView();
                        break;

                    case R.id.flights_and_hotels:
                        //Do something when radio button is clicked
                        flight=true; hotel=true;
//                        Toast.makeText(getActivity(), "Flights and Hotels", Toast.LENGTH_LONG).show();
                        showView();
                        break;
                }

            }
        });

//        dateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar now = Calendar.getInstance();
//                DatePickerDialog dpd = DatePickerDialog.newInstance(
//                        Search.this,
//                        now.get(Calendar.YEAR),
//                        now.get(Calendar.MONTH),
//                        now.get(Calendar.DAY_OF_MONTH)
//                );
//                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");
//            }
//        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                public SearchData(String countryFrom, String countryTo, String dateFrom, String dateTo, String cityFrom, String cityTo, int adultNumber, int childrenNumber, int classType, double starRate, int roomNumber, boolean flightsOnly, boolean hotelsOnly, boolean economy,boolean bussiness, boolean first_class,int nights) {

                //materialBetterSpinner_adults
                //materialBetterSpinner_children
                // *String roomCapacity*, String starRate, String roomNumber

                searchData = new SearchData(country_EditText_from.getText().toString(),country_EditText_to.getText().toString(),date_from_value,
                        date_to_value, city_EditText_from.getText().toString(),city_EditText_to.getText().toString(),1,0,0,4.0,1,flight,hotel,
                        economy.isChecked(),business.isChecked(),first_class.isChecked(),1,Double.parseDouble(budget.getText().toString()));

                // start result activity here
                Intent myIntent = new Intent(getActivity(), ResultsActivity.class);
                // myIntent.putExtra("key", value); //Optional parameters
                Bundle b = new Bundle();
                b.putSerializable("search_data", searchData);
                myIntent.putExtras(b);
                getActivity().startActivity(myIntent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        TimePickerDialog tpd = (TimePickerDialog) getActivity().getFragmentManager().findFragmentByTag("Timepickerdialog");
        DatePickerDialog dpd = (DatePickerDialog) getActivity().getFragmentManager().findFragmentByTag("Datepickerdialog");

        //if(tpd != null) tpd.setOnTimeSetListener(this);
        if(dpd != null) dpd.setOnDateSetListener(this);
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date;
        if(date_from) {
            date = "From: " +dayOfMonth  + "/" + (++monthOfYear) + "/" + year;
            //dateTextView.setText(date);
            date_from_value =  dayOfMonth + "-" + (++monthOfYear) + "-" +year ;
            date_EditText_from.setText(date);
        }
        if(date_to){
            date = "To: " + dayOfMonth + "/" + (++monthOfYear) + "/" + year;
            //dateTextView.setText(date);
            date_to_value = dayOfMonth + "-" + (++monthOfYear) + "-" +year ;
            date_EditText_to.setText(date);
        }
    }

    private void setListener() {
        mCountryPicker.setListener(new CountryPickerListener() {
            @Override public void onSelectCountry(String name, String code, String dialCode,
                                                  int flagDrawableResID) {
                if(country_from) {
                    country_EditText_from.setText(name);
                    mCountryFlagImageView_from.setImageResource(flagDrawableResID);

                }
                if(country_to) {
                    country_EditText_to.setText(name);
                    mCountryFlagImageView_to.setImageResource(flagDrawableResID);
                }
//                mCountryIsoCodeTextView.setText(code);
//                mCountryDialCodeTextView.setText(dialCode);
                mCountryPicker.dismiss();
            }
        });
        country_EditText_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country_from=true;
                country_to=false;
                mCountryPicker.show(getActivity().getSupportFragmentManager(), "COUNTRY_PICKER");
            }
        });
        country_EditText_to.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                country_from=false;
                country_to=true;
                mCountryPicker.show(getActivity().getSupportFragmentManager(), "COUNTRY_PICKER");
            }
        });
        getUserCountryInfo();
    }

    private void getUserCountryInfo() {
        Country country = mCountryPicker.getUserCountryInfo(getActivity());
        mCountryFlagImageView_from.setImageResource(country.getFlag());
        mCountryFlagImageView_to.setImageResource(country.getFlag());
//        mCountryDialCodeTextView.setText(country.getDialCode());
//        mCountryIsoCodeTextView.setText(country.getCode());
        country_EditText_from.setText(country.getName());
        country_EditText_to.setText(country.getName());
    }

    private void showView(){

        if(flight && !hotel){
            final View showView = mFlight;
            final View hideView = mHotels;
            continueShowView(showView,hideView);
            view.findViewById(R.id.country_layout).setVisibility(View.GONE);
            view.findViewById(R.id.city_layout).setVisibility(View.VISIBLE);

        }else if(hotel && !flight){
            final View showView = mHotels;
            final View hideView = mFlight;
            continueShowView(showView,hideView);
            view.findViewById(R.id.city_layout).setVisibility(View.GONE);
            view.findViewById(R.id.country_layout).setVisibility(View.VISIBLE);
        }else{
            view.findViewById(R.id.city_layout).setVisibility(View.VISIBLE);
            view.findViewById(R.id.country_layout).setVisibility(View.VISIBLE);
            final View showView1 = mFlight;

            showView1.setAlpha(0f);
            showView1.setVisibility(View.VISIBLE);

            showView1.animate()
                    .alpha(1f)
                    .setDuration(mShortAnimationDuration)
                    .setListener(null);

            final View showView2 = mHotels;

            showView2.setAlpha(0f);
            showView2.setVisibility(View.VISIBLE);

            showView2.animate()
                    .alpha(1f)
                    .setDuration(mShortAnimationDuration)
                    .setListener(null);

        }
    }

    private void continueShowView(View showView, final View hideView){
        showView.setAlpha(0f);
        showView.setVisibility(View.VISIBLE);

        showView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration)
                .setListener(null);

        hideView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        hideView.setVisibility(View.GONE);
                    }
                });
    }


}
