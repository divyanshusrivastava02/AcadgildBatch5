package com.acadgildbatch5.ListviewClasses;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.acadgildbatch5.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Divyanshu on 27-11-2016.
 */

public class Listview2 extends Activity {

    List<String> li;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview2);

        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rl);
        final RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams
                ((int) RelativeLayout.LayoutParams.WRAP_CONTENT,(int) RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin=10;
        params.topMargin=10;

        Button show = (Button) findViewById(R.id.button1);
         final ListView  list = new ListView(this);

        li = new ArrayList<String>();
        li.add("Batch1");
        li.add("Batch2");
        li.add("Batch3");
        li.add("Batch4");
        li.add("Batch5");
        li.add("Batch6");
        li.add("Batch7");
        li.add("Batch8");
        li.add("Batch1");



        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adp = new ArrayAdapter<String>(getBaseContext(),
                        android.R.layout.simple_expandable_list_item_1,li);

                adp.setDropDownViewResource(android.R.layout.simple_expandable_list_item_1);

                list.setAdapter(adp);
                list.setLayoutParams(params);

                rl.addView(list);

            }
        });

    }
}
