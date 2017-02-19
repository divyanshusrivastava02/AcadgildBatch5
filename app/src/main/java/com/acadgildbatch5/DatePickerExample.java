package com.acadgildbatch5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by Divyanshu on 27-11-2016.
 */

public class DatePickerExample extends Activity {

    DatePicker datePick;
    Button pickBTN;
    TimePicker time;
    TextView timeIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datepicker);

        timeIS = (TextView)findViewById(R.id.timeIS);
        time = (TimePicker)findViewById(R.id.timePicker);
        datePick = (DatePicker)findViewById(R.id.datepicker1);
        pickBTN = (Button)findViewById(R.id.dataclick);
        pickBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getBaseContext(),"Time is:-"+time.getCurrentHour()+" : "+ time.getCurrentMinute(),Toast.LENGTH_SHORT).show();
                timeIS.setText("Time is:-"+time.getCurrentHour()+" : "+ time.getCurrentMinute());
//                Toast.makeText(getBaseContext(),"Date is:-"+datePick.getDayOfMonth() +" - "+(datePick.getMonth()+1)+" - "+datePick.getYear(),Toast.LENGTH_SHORT).show();
            }
        });


        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(getBaseContext(),"Current Time is:-"+hourOfDay+" : "+minute,Toast.LENGTH_SHORT).show();
                timeIS.setText("Current Time is:-"+hourOfDay+" : "+minute);

            }
        });
    }
}
