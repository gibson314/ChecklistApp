package com.cs465.litian.roommate.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.cs465.litian.roommate.R;
import com.cs465.litian.roommate.Tools.GlobalParameterApplication;
import com.cs465.litian.roommate.activity.AddReceipt;
import com.cs465.litian.roommate.activity.ReceiptDetail;
import com.cs465.litian.roommate.activity.ReceiptDetail;
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
                    Intent intent = new Intent(getActivity(), ReceiptDetail.class);
                    startActivity(intent);
                }
                else if (GlobalParameterApplication.getLoginStatus() == 1) {
                    Intent intent = new Intent(getActivity(), UserProfile.class);
                    startActivity(intent);
                }
            }
        });
        ImageButton add_receipt_btn = (ImageButton) view.findViewById(R.id.addReceipt);
        add_receipt_btn.setOnClickListener(new View.OnClickListener(){
                                               @Override
                                               public void onClick(View v) {
                                                   GlobalParameterApplication.setLoginStatus(0);
                                                   Intent intent = new Intent(getActivity(), AddReceipt.class);
                                                   startActivity(intent);
                                               }
                                           }

        );

        ImageButton receipt1_detail_btn = (ImageButton) view.findViewById(R.id.receipt1);
        receipt1_detail_btn.setOnClickListener(new View.OnClickListener(){
                                                   @Override
                                                   public void onClick(View v) {
                                                       GlobalParameterApplication.setLoginStatus(0);
                                                       Intent intent = new Intent(getActivity(), ReceiptDetail.class);
                                                       startActivity(intent);
                                                   }
                                               }

        );

        ImageButton receipt2_detail_btn = (ImageButton) view.findViewById(R.id.receipt2);
        receipt2_detail_btn.setOnClickListener(new View.OnClickListener(){
                                                   @Override
                                                   public void onClick(View v) {
                                                       GlobalParameterApplication.setLoginStatus(0);
                                                       Intent intent = new Intent(getActivity(), ReceiptDetail.class);
                                                       startActivity(intent);
                                                   }
                                               }

        );

        return view;
    }
}
