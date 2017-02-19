package com.acadgildbatch5.fragmentsexample;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Divyanshu on 17-12-2016.
 */

public class DetailsFragment extends Fragment {

    public static DetailsFragment newInstance(int index){
        DetailsFragment f = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("index",index);
        f.setArguments(args);
        return  f;
    }

    public  int getShowIndex(){
        return getArguments().getInt("index",0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setText(Data.DETAILS[getShowIndex()]);
        tv.setTextColor(Color.BLACK);
        Toast.makeText(getActivity(),"onCreateView called",Toast.LENGTH_SHORT).show();
        return tv;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Toast.makeText(getActivity(),"onAttach called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getActivity(),"onCreate called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(getActivity(),"onActivityCreated called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getActivity(),"onStart called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getActivity(),"onResume called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(getActivity(),"onPause called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(getActivity(),"onStop called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getActivity(),"onDestroyView called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getActivity(),"onDestroy called",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Toast.makeText(getActivity(),"onDetach called",Toast.LENGTH_SHORT).show();
    }
}
