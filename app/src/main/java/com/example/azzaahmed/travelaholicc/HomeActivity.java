package com.example.azzaahmed.travelaholicc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
public class HomeActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {

            R.drawable.ic_tab_search,
            R.drawable.ic_tab_plan
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Search(), "Search");
        adapter.addFragment(new Plans(), "Plans");

        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.action_settings) {
            //startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        else  if (id == R.id.action_signout) {
//            //startActivity(new Intent(this, SettingsActivity.class));
            Intent intent = new Intent(this, MainActivity.class).putExtra("SignOut_flag","true");
           startActivity(intent);
//            MyApplication   myApp = (MyApplication)getApplicationContext();
//
//          //  (MainActivity() getActivity()).signOut();
//          //  (MyApplication)getApplication().ma
//   //   GoogleApiClient mGoogleApiClient= MainActivity.mGoogleApiClient;
//            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                    .requestEmail()
//                    .build();
//            myApp.mGoogleApiClient  = new GoogleApiClient.Builder(this)
//                    .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
//                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                    .build();
//            Auth.GoogleSignInApi.signOut( myApp.mGoogleApiClient).setResultCallback(
//                    new ResultCallback<Status>() {
//                        @Override
//                        public void onResult(Status status) {
//                            // [START_EXCLUDE]
//                         //   updateUI(false);
//                            // [END_EXCLUDE]
//                        }
//
//                    });
          //  startActivity(new Intent(this, MainActivity.class));
//            TextView mname=(TextView) findViewById(R.id.plan_fragment_text);;
//            Person currentUser = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
//            String emailaddress =Plus.AccountApi.getAccountName(mGoogleApiClient);
//            mname.setText(String.format("name as %s", currentUser.getDisplayName()+ " "+emailaddress));
//            mid.setText(String.format("id as %s", currentUser.getId()));
//
//            mStatus.setText(String.format("Signed In to G+ as %s", emailaddress));

            return true;

        }

        return super.onOptionsItemSelected(item);
    }


}
