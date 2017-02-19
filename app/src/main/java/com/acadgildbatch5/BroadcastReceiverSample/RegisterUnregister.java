package com.acadgildbatch5.BroadcastReceiverSample;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 08-01-2017.
 */

public class RegisterUnregister extends Activity {

    UserDefinedBroadcastReceiver broadcastReceiver = new UserDefinedBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_unregister);
    }

    public void registerBrodcastReceiver(View view){

        this.registerReceiver(broadcastReceiver,new IntentFilter("com.acadgild.batch5"));
        Toast.makeText(this,"Enabled Broasdcast receiver",Toast.LENGTH_SHORT).show();
    }

    public void unregisterBrodcastReceiver(View view){
        this.unregisterReceiver(broadcastReceiver);
        Toast.makeText(this,"Disable Broasdcast receiver",Toast.LENGTH_SHORT).show();
    }
}
