package com.acadgildbatch5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Divyanshu on 20-11-2016.
 */

public class SecondActivity extends Activity {

    TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondlayout);


        message = (TextView)findViewById(R.id.message);
        Intent intentOBJ = getIntent();
        String value = intentOBJ.getStringExtra("USERINPUT");
        int salaryValue = intentOBJ.getIntExtra("SALaRy",2000);
        boolean isValidateUSer = intentOBJ.getBooleanExtra("isValid",false);


        String userBundle = intentOBJ.getExtras().getString("USERINPUT");
        int age = intentOBJ.getExtras().getInt("Ageu");


        message.setText(value+" "+salaryValue+ " "+isValidateUSer+ "  "+userBundle);


       // Toast.makeText(SecondActivity.this,value+"   "+salaryValue+"  "+isValidateUSer,Toast.LENGTH_SHORT).show();
    }
}
