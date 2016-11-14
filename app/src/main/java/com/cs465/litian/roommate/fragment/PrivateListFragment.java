package com.cs465.litian.roommate.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs465.litian.roommate.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by litian on 11/13/16.
 */
public class PrivateListFragment extends SupportFragment {
    private PagerAdapter mAdapter;


    public static PrivateListFragment newInstance(){
        Bundle args = new Bundle();
        PrivateListFragment fragment = new PrivateListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mylist_private, container, false);
        return view;
    }
}
