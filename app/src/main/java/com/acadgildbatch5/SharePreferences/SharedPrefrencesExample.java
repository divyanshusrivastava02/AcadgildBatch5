package com.acadgildbatch5.SharePreferences;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 24-12-2016.
 */

public class SharedPrefrencesExample extends Activity {

    Button btn_settings,btn_show, btn_saveValues;
    EditText usernameET, passwordET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedpreferences);


        btn_settings = (Button)findViewById(R.id.btn_settings);
        btn_show = (Button)findViewById(R.id.btn_show);
        btn_saveValues = (Button)findViewById(R.id.btn_saveValues);

        btn_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SharedPrefrencesExample.this,SettingActivity.class);
                startActivity(intent);
            }
        });


        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SharedPrefrencesExample.this,ShowValuesActivity.class);
                startActivity(intent);
            }
        });

        usernameET = (EditText)findViewById(R.id.username);
        passwordET = (EditText)findViewById(R.id.password);

        btn_saveValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("Acadgild",MODE_PRIVATE);
                SharedPreferences.Editor editor  = pref.edit();

                editor.putString("username",usernameET.getText().toString());
                editor.putString("password",passwordET.getText().toString());

                editor.commit();
            }
        });
    }
}
