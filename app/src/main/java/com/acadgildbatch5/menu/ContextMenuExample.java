package com.acadgildbatch5.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 10-12-2016.
 */

public class ContextMenuExample extends Activity{

    ListView listView1;
    String itemsList[]={"item A","item B","item C"};
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contextmenu);
        listView1 = (ListView)findViewById(R.id.listview1);
         adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,itemsList);
        listView1.setAdapter(adapter);
        registerForContextMenu(listView1);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("WELCOME TO ACADGILD OPTIONS");
        menu.add(0,v.getId(),0,"Call");
        menu.add(0,v.getId(),0,"Message");



    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;

        System.out.println("POSITION::::"+index+" "+adapter.getItem(index));

        if(item.getTitle()=="Call"){
            Toast.makeText(getApplicationContext(),"Call Clicked",Toast.LENGTH_SHORT).show();
        }else if(item.getTitle()=="Message"){
            Toast.makeText(getApplicationContext(),"Message Clicked",Toast.LENGTH_SHORT).show();
        }else {
            return false;
        }
        return true;
    }
}
