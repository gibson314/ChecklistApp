package com.cs465.litian.roommate.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.cs465.litian.roommate.HelloWorld;
import com.cs465.litian.roommate.R;
import com.cs465.litian.roommate.Tools.GlobalParameterApplication;
import com.cs465.litian.roommate.activity.UserLogin;
import com.cs465.litian.roommate.activity.UserProfile;
import com.cs465.litian.roommate.adapter.MyListFragmentAdapter;


import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by litia on 11/10/2016.
 */

public class MylistFragment extends SupportFragment {
    private TabLayout mTab;
    private Toolbar mToolbar;
    private ViewPager mViewPager;

    public static MylistFragment newInstance() {
        Bundle args = new Bundle();
        MylistFragment fragment = new MylistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mylist_fragment, container, false);
//        View tex = view.findViewById(R.id.helloworld);
//        tex.setOnClickListener (new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), HelloWorld.class);
//                getActivity().startActivity(intent);
//            }
//        });
//
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTab = (TabLayout) view.findViewById(R.id.tab);

        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
//        mToolbar.setTitle("My Lists");
        //(mToolbar);
        mTab.addTab(mTab.newTab().setText("Public List"));
        mTab.addTab(mTab.newTab().setText("Private List"));
        mTab.addTab(mTab.newTab().setText("Chores"));


        mViewPager.setAdapter(new MyListFragmentAdapter(getChildFragmentManager()));
        mTab.setupWithViewPager(mViewPager);

        ImageView user_btn = (ImageView) view.findViewById(R.id.user_2);
        user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (GlobalParameterApplication.getLoginStatus() == 0) {
                    Intent intent = new Intent(getActivity(), UserLogin.class);
                    startActivity(intent);
                }
                else if (GlobalParameterApplication.getLoginStatus() == 1) {
                    Intent intent = new Intent(getActivity(), UserProfile.class);
                    startActivity(intent);
                }
            }
        });

    }


}
