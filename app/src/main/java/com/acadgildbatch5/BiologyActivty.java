package com.acadgildbatch5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Divyanshu on 21-01-2017.
 */

public class BiologyActivty extends Activity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biologylogin);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }


    public void buttonCLick(View view){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "Some string");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Arvind");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Prashant");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }
}
