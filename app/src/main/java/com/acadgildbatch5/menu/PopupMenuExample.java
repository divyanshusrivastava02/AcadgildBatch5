package com.acadgildbatch5.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.Toolbar;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 10-12-2016.
 */

public class PopupMenuExample extends Activity {
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popupmenu);


        /**
         * Attached toolbar
         */

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("ToolTitle");
        setActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.greencheck);




        button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(PopupMenuExample.this,button1);
                popupMenu.getMenuInflater().inflate(R.menu.menu_options,popupMenu.getMenu());


                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(PopupMenuExample.this,"You Clicked : "+item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
}
