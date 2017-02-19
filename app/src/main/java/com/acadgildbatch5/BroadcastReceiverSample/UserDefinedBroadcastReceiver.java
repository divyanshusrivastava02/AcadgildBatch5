package com.acadgildbatch5.BroadcastReceiverSample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Divyanshu on 08-01-2017.
 */

public class UserDefinedBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equalsIgnoreCase("com.acadgild.batch5")){
            StringBuilder msgstring = new StringBuilder(" Current Time : ");
            Format format = new SimpleDateFormat("hh:mm:ss a");
            msgstring.append(format.format(new Date()));
            Toast.makeText(context,msgstring,Toast.LENGTH_SHORT).show();
        }
    }
}
