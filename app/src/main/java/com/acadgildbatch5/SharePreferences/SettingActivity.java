package com.acadgildbatch5.SharePreferences;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 24-12-2016.
 */

public class SettingActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
