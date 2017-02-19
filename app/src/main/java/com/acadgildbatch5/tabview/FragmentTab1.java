package com.acadgildbatch5.tabview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acadgildbatch5.R;

/**
 * Created by Divyanshu on 17-12-2016.
 */

public class FragmentTab1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fraagmenttab1,container,false);
    }
}
