package com.acadgildbatch5.tabview;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Divyanshu on 17-12-2016.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    final  int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"MOVIES","TV SHOWS", "About"};
    Context context;

    ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentTab2 fragmentTab2 = new FragmentTab2();
                return fragmentTab2;

            case 1:
                FragmentTab1 fragmentTab1 = new FragmentTab1();
                return fragmentTab1;
            case 2:
                FragmentTab3 fragmentTab3 = new FragmentTab3();
                return fragmentTab3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

   @Override
    public CharSequence getPageTitle(int postion){
       return tabTitles[postion];
   }
}
