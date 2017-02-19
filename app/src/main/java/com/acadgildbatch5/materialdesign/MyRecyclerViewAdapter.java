package com.acadgildbatch5.materialdesign;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acadgildbatch5.R;

import java.util.ArrayList;

/**
 * Created by Divyanshu on 31-12-2016.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.DataObjectHolder> {


    private static String LOG_TAG="MyRecyclerviewAdapter";
    private ArrayList<DataObject> mDataSet;
    private static MyClickListener myClickListener;



    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView lable;
        TextView dateTime;

        public DataObjectHolder(View itemView){
            super(itemView);
            lable = (TextView)itemView.findViewById(R.id.textview1);
            dateTime = (TextView)itemView.findViewById(R.id.textview2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(),v);
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener){
        this.myClickListener = myClickListener;
    }
    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataSet){
        mDataSet = myDataSet;
    }


    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_row,parent,false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.lable.setText(mDataSet.get(position).getmText1());
        holder.dateTime.setText(mDataSet.get(position).getmText2());

    }

    public void addItem(DataObject dataObject, int index){
        mDataSet.add(index,dataObject);
        notifyItemInserted(index);
    }

    public  void deleteItem(int index){
        mDataSet.remove(index);
        notifyItemRemoved(index);
    }
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public interface MyClickListener{
        public void onItemClick(int position, View v);
    }
}
