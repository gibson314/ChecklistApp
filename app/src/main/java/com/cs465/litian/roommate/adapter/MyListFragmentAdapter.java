package com.cs465.litian.roommate.adapter;

/**
 * Created by litian on 11/13/16.
 */
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cs465.litian.roommate.fragment.ChoresFragment;
import com.cs465.litian.roommate.fragment.PublicListFragment;
import com.cs465.litian.roommate.fragment.PrivateListFragment;
import com.cs465.litian.roommate.fragment.MylistFragment;


public class MyListFragmentAdapter extends FragmentPagerAdapter {
    private String[] mTab = new String[]{"Public list", "Private list", "Chores"};

    public MyListFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return PublicListFragment.newInstance();
        }
        else if (position == 1) {
            return PrivateListFragment.newInstance();
        }
        else {
            return ChoresFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return mTab.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTab[position];
    }
}