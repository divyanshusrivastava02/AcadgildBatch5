package com.acadgildbatch5.fragmentsexample;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Divyanshu on 17-12-2016.
 */

public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(getIntent().getExtras());
        getFragmentManager().beginTransaction().add(android.R.id.content,detailsFragment).commit();
    }
}
