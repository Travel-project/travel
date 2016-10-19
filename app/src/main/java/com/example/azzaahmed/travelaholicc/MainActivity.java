package com.example.azzaahmed.travelaholicc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Activity to demonstrate basic retrieval of the Google user's ID, email address, and basic
 * profile.
 */
public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener {

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private  GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;
private Bundle extras;
  //  MyApplication myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         Intent myIntent = getIntent(); // gets the previously created intent
             // String SignOut_flag = myIntent.getStringExtra("SignOut_flag");
        extras = getIntent().getExtras();

        Log.d(TAG, "in create");
            // Views
          //  mStatusTextView = (TextView) findViewById(R.id.status);

            // Button listeners
            findViewById(R.id.sign_in_button).setOnClickListener(this);
           // findViewById(R.id.sign_out_button).setOnClickListener(this);
           // findViewById(R.id.disconnect_button).setOnClickListener(this);

            // [START configure_signin]
            // Configure sign-in to request the user's ID, email address, and basic
            // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();
            // [END configure_signin]

            // [START build_client]
            // Build a GoogleApiClient with access to the Google Sign-In API and the
            // options specified by gso.
//        myApp = (MyApplication)getApplicationContext();
//        myApp.mGoogleApiClient  = new GoogleApiClient.Builder(this);
//
       mGoogleApiClient  = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
               .addConnectionCallbacks(this)
               .addOnConnectionFailedListener(this)
                    .build();
            // [END build_client]

            // [START customize_button]
            // Customize sign-in button. The sign-in button can be displayed in
            // multiple sizes and color schemes. It can also be contextually
            // rendered based on the requested scopes. For example. a red button may
            // be displayed when Google+ scopes are requested, but a white button
            // may be displayed when only basic profile is requested. Try adding the
            // Scopes.PLUS_LOGIN scope to the GoogleSignInOptions to see the
            // difference.
            SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
            signInButton.setSize(SignInButton.SIZE_WIDE);
            signInButton.setScopes(gso.getScopeArray());
            // [END customize_button]

    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "in start and " + mGoogleApiClient.isConnected());

        if (extras != null) {
            Log.d(TAG, "in extra and " + mGoogleApiClient.isConnected());

            String SignOut_flag = extras.getString("SignOut_flag");
        if(SignOut_flag.equals("true"))
        mGoogleApiClient.connect();
        }
       // mGoogleApiClient= myApp.mGoogleApiClient;

       else {
            Log.d(TAG, "out  extra and " + mGoogleApiClient.isConnected());
            OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);

            if (opr.isDone()) {
                // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
                // and the GoogleSignInResult will be available instantly.
                Log.d(TAG, "Got cached sign-in  and" + mGoogleApiClient.isConnected());

                GoogleSignInResult result = opr.get();
                handleSignInResult(result);
                //    mGoogleApiClient.connect();
            } else {
                Log.d(TAG, "NOT  cached sign-in  and" + mGoogleApiClient.isConnected());
                // If the user has not previously signed in on this device or the sign-in has expired,
                // this asynchronous branch will attempt to sign in the user silently.  Cross-device
                // single sign-on will occur in this branch.
                showProgressDialog();
                opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                    @Override
                    public void onResult(GoogleSignInResult googleSignInResult) {
                        hideProgressDialog();
                        handleSignInResult(googleSignInResult);
                    }
                });
            }

        }
    }


    // [START onActivityResult]
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Log.d(TAG, "in RC sugn in and" + mGoogleApiClient.isConnected());
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    // [END onActivityResult]

    // [START handleSignInResult]
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());


        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
//         GoogleSignInAccount acct = result.getSignInAccount();
//         mStatusTextView.setText( acct.getDisplayName()+"   "+acct.getEmail()+"    "+ acct.getId());
//         updateUI(true);

         //   Intent intent = new Intent(this, HomeActivity.class).putExtra("client",(Parcelable)mGoogleApiClient);
          // startActivity(intent);

//            if (extras != null) {
//                String SignOut_flag = extras.getString("SignOut_flag");
//                Log.d(TAG, "in the condition true and" + mGoogleApiClient.isConnected());
//
//                if (SignOut_flag.equals("true")) {
//                    Log.d(TAG, "in the condition true");
//
//                    Log.d(TAG, "after stop a connection " + mGoogleApiClient.isConnected());
//                 //   findViewById(R.id.sign_out_button).performClick();
//                  //  mStatusTextView.setText( acct.getDisplayName());
//                    signOut();
//                }
//                else  Log.d(TAG, "in the condition not null bs msh true");
//
//            }
      //      else {
                Log.d(TAG, "sent to home");
         startActivity(new Intent(this, HomeActivity.class));
    //   }
        } else {
            // Signed out, show unauthenticated UI.
            Log.d(TAG, "signed out un auth UI and" + mGoogleApiClient.isConnected());
            updateUI(false);
        }
    }
    // [END handleSignInResult]

    // [START signIn]
    private void signIn() {
        Log.d(TAG, "in sign in and" + mGoogleApiClient.isConnected());
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    // [END signIn]

    // [START signOut]
    private  void signOut() {

        Log.d(TAG, "in sign out and" + mGoogleApiClient.isConnected());
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
//        mGoogleApiClient.disconnect();
//        mGoogleApiClient.connect();
        Log.d(TAG, "in sign out2 and" + mGoogleApiClient.isConnected());
    }
    // [END signOut]

    // [START revokeAccess]
    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        updateUI(false);
                        // [END_EXCLUDE]
                    }
                });
//        Log.d(TAG, "sent to home");
//        startActivity(new Intent(this, HomeActivity.class));
    }
    // [END revokeAccess]

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
      //  mGoogleApiClient.connect();
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            //   mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
         //   findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
        //    mStatusTextView.setText("Signedout");

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
          //  findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
//            case R.id.sign_out_button:
//                signOut();
//                break;
//            case R.id.disconnect_button:
//                revokeAccess();
//                break;
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "onConnect " );

        if (extras != null) {        String SignOut_flag = extras.getString("SignOut_flag");
        if(SignOut_flag.equals("true")){
            Log.d(TAG, "onConnect and if flag true  " );
        signOut();
    }
    }
    }

    @Override
    public void onConnectionSuspended(int i) {
mGoogleApiClient.connect();
    }
}