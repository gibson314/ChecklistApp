package com.cs465.litian.roommate.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cs465.litian.roommate.HelloWorld;
import com.cs465.litian.roommate.R;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by litia on 11/10/2016.
 */

public class MylistFragment extends SupportFragment {
    public static MylistFragment newInstance() {
        Bundle args = new Bundle();
        MylistFragment fragment = new MylistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mylist_fragment, container, false);
        View tex = view.findViewById(R.id.helloworld);
        tex.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HelloWorld.class);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }


}
