package com.group8.ciu196.beaconproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SwipePagerAdapter extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 5;

    public SwipePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {

        return BookFragment.newInstance(position, "Page #" + position + 1);
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Page " + position;
    }

    @Override
    public float getPageWidth (int position) {
       //return 0.93f;
        //return 0.75f;
        return 0.88f;
    }

}