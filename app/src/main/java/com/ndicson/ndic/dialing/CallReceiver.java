package com.ndicson.ndic.dialing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import static com.ndicson.ndic.dialing.MainActivityDial.mypreference;

public class CallReceiver extends BroadcastReceiver {
    public String incomingNumber;
    SharedPreferences specialNumber;

    public CallReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        //get the outgoing number, and put the prefix "+86" before it
        //String number= getResultData(); //to get the outgoing phone number
       // number= "+86"+number;
       // setResultData(number);
        String a=TelephonyManager.EXTRA_STATE_RINGING;
//         Toast.makeText(context, "Phone is "+a, Toast.LENGTH_LONG).show();
//         Toast.makeText(context, "Phone is "+b, Toast.LENGTH_LONG).show();

        incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
//            Toast.makeText(context, "Phone is ringing", Toast.LENGTH_LONG).show();

        specialNumber = context.getSharedPreferences(mypreference,Context.MODE_PRIVATE);
        String num=specialNumber.getString("specialNumber",null);
        Toast.makeText(context,"stored in SharePreference: "+num+" and call: "+incomingNumber,Toast.LENGTH_LONG).show();
        if (specialNumber.contains(incomingNumber)) {
            Toast.makeText(context,"This number "+incomingNumber+" is stored in SharePreference",Toast.LENGTH_LONG).show();

            //make sure not to change the call anymore
            abortBroadcast();
        }
    }
}
