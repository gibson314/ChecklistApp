package com.cs465.litian.roommate.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cs465.litian.roommate.R;
import com.cs465.litian.roommate.Tools.GlobalParameterApplication;
import com.cs465.litian.roommate.activity.UserLogin;
import com.cs465.litian.roommate.activity.UserProfile;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by litia on 11/10/2016.
 */

public class ReceiptFragment extends SupportFragment{
    public static ReceiptFragment newInstance() {
        Bundle args = new Bundle();
        ReceiptFragment fragment = new ReceiptFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.receipt_fragment, container, false);
        ImageView user_btn = (ImageView) view.findViewById(R.id.user_3);
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

        return view;
    }
}
