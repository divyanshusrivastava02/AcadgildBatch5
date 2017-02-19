package com.acadgildbatch5.CustomList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.acadgildbatch5.CustomList.ListData;

import com.acadgildbatch5.R;

import java.util.ArrayList;

/**
 * Created by Divyanshu on 27-11-2016.
 */

public class MyBaseAdapter extends BaseAdapter {

    ArrayList myList = new ArrayList();
    LayoutInflater inflater;
    Context context;

    public MyBaseAdapter(Context context, ArrayList myList){
        this.context = context;
        this.myList = myList;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ListData getItem(int position) {
        return (ListData)myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder myViewHolder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.layout_list_item,parent,false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        }
        else {
            myViewHolder = (MyViewHolder)convertView.getTag();
        }

        System.out.println("POSITION::::::::"+position);
        ListData currentListData = getItem(position);

        myViewHolder.tvTitle.setText(currentListData.getTitle());
        myViewHolder.tvDesc.setText(currentListData.getDescription());
        myViewHolder.ivIcon.setImageResource(currentListData.getImgResID());


        return convertView;
    }


    private class MyViewHolder{
        TextView tvTitle, tvDesc;
        ImageView ivIcon;
        public MyViewHolder(View item){
            tvTitle = (TextView)item.findViewById(R.id.tvTitle);
            tvDesc = (TextView)item.findViewById(R.id.tvDesc);
            ivIcon = (ImageView)item.findViewById(R.id.ivicom);
        }
    }
}
