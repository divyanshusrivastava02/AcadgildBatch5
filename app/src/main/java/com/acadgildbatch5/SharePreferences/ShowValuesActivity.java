package com.acadgildbatch5.SharePreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.TextView;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 24-12-2016.
 */

public class ShowValuesActivity extends Activity {

    EditText username_value, password_values;
    TextView item1,item2,item3,item4,item5,item6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_savedvalues);
        username_value = (EditText)findViewById(R.id.username_value);
        password_values = (EditText)findViewById(R.id.password_value);


        SharedPreferences prefs = getSharedPreferences("Acadgild",MODE_PRIVATE);
        String userName = prefs.getString("username","Sumit");
        String password = prefs.getString("password","Ritesh");

        username_value.setText(userName);
        password_values.setText(password);


        item1 = (TextView)findViewById(R.id.item1);
        item2 = (TextView)findViewById(R.id.item2);
        item3 = (TextView)findViewById(R.id.item3);
        item4 = (TextView)findViewById(R.id.item4);
        item5 = (TextView)findViewById(R.id.item5);
        item6 = (TextView)findViewById(R.id.item6);


        //FETCHING VALUES

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        boolean cb1 = sp.getBoolean("cbp_preferences1",false);
        boolean cb2 = sp.getBoolean("cbp_preferences2",false);
        String edit1 = sp.getString("etp_preferences1","NO TITLE GIVEN");
        String list = sp.getString("lp_pref1","NOT SELECTED");
        String ringtone = sp.getString("rtp_pref1","NOT SELECTED");
        String eit2 = sp.getString("etp_preferences3","NOT SELECTED");


        item1.setText(Boolean.toString(cb1));
        item2.setText(Boolean.toString(cb2));
        item3.setText(edit1);
        item4.setText(list);
        item5.setText(ringtone);
        item6.setText(eit2);
    }
}
