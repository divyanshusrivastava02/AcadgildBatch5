package com.acadgildbatch5.fragmentsexample;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 17-12-2016.
 */

public class TitlesFragments extends ListFragment {

    boolean mDualPane;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_activated_1,Data.TITLES));

        View detailsFrame = getActivity().findViewById(R.id.details);

        mDualPane = detailsFrame !=null && detailsFrame.getVisibility()== View.VISIBLE;

        Toast.makeText(getActivity(),"mDual Pane:- "+mDualPane, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ShowDetails(position);
    }

    void ShowDetails(int index){

        if(mDualPane){
            getListView().setItemChecked(index,true);
            DetailsFragment details = (DetailsFragment)getFragmentManager().findFragmentById(R.id.details);
            if(details==null || details.getShowIndex()!=index){
                details = DetailsFragment.newInstance(index);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.details,details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        }else {

            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailsActivity.class);
            intent.putExtra("index",index);
            startActivity(intent);
        }
    }
}
