package com.acadgildbatch5.menu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 10-12-2016.
 */

public class OptionMenuExample extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optionmenu);

        fetchData();
        updateUI();
    }

    public void updateUI(){
        // Refresh your view
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_options,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(OptionMenuExample.this,"Item 1  clicked",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.item3:
                Toast.makeText(OptionMenuExample.this,"Refresh  clicked",Toast.LENGTH_SHORT).show();
                fetchData();
                updateUI();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }


    public void fetchData(){
        // There is the url called which fetch the data from the server
    }
}
