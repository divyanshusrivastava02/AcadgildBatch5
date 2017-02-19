package com.acadgildbatch5.services.UnBoundServicesEXMP;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 15-01-2017.
 */

public class UnBoundServiceExample extends Activity implements View.OnClickListener{
    Button startServiceButton, stopServiceBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_bound_unboundservices);


        startServiceButton = (Button)findViewById(R.id.startService);
        stopServiceBTN = (Button)findViewById(R.id.stopService);

        startServiceButton.setOnClickListener(this);
        stopServiceBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.startService:
                Intent  startServiceIntent = new Intent(UnBoundServiceExample.this, MyUnBoundService.class);
                startService(startServiceIntent);

                break;

            case R.id.stopService:
                Intent  stopServiceIntent = new Intent(UnBoundServiceExample.this, MyUnBoundService.class);
                stopService(stopServiceIntent);
                break;
        }

    }
}
