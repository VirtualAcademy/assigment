package com.ndicson.ndic.dialing;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivityDial extends AppCompatActivity {
    private CallReceiver callReceiver;
    private IntentFilter intentFilter;
    public SharedPreferences sharedpreferences;
    public static final String mypreference = "specialnumber";
    public static final String SpecialNumber = "6505551212";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dial);

        //      Shared preference
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("specialNumber",SpecialNumber);
        editor.commit();

        //dynamic registration of CallReceiver
        callReceiver=new CallReceiver();
        intentFilter =new IntentFilter();
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        registerReceiver(callReceiver,intentFilter);


    }


    @Override
    protected void onDestroy() {

        //dynamic unregisteration
        // unregisterReceiver(callReceiver);

        super.onDestroy();
    }
}
