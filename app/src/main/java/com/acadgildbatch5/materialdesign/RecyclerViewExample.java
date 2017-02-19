package com.acadgildbatch5.materialdesign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.acadgildbatch5.R;

import java.util.ArrayList;
import java.util.logging.Handler;

/**
 * Created by Divyanshu on 31-12-2016.
 */

public class RecyclerViewExample extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static  String LOG_TAG = "RecyclerViewExample";
    SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);


        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
//        swipeLayout.setOnRefreshListener(savedInstanceState);
        swipeLayout.setColorSchemeColors(android.R.color.holo_blue_dark,android.R.color.holo_green_dark,android.R.color.holo_orange_light,android.R.color.holo_red_dark,android.R.color.white);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Toast.makeText(getApplicationContext(),"refreshed",Toast.LENGTH_SHORT).show();
                swipeLayout.setRefreshing(false);
            }
        });



        //Add some values
        //((MyRecyclerViewAdapter)mAdapter).addItem(obj,index);


        // Remove
        //((MyRecyclerViewAdapter)mAdapter).deleteItem(index);


    }


    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter)mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter.MyClickListener(){
            @Override
            public void onItemClick(int position, View v) {
                Toast.makeText(getApplicationContext(),"You click "+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<DataObject> getDataSet(){
        ArrayList results = new ArrayList<DataObject>();
        for(int index=0; index<40;index++){
            DataObject obj = new DataObject("Acadgild  : "+index," Batch 5 : "+index);
            results.add(index,obj);
        }
        return results;
    }
}
