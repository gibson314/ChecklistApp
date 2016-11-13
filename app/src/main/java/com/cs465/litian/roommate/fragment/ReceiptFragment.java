package com.cs465.litian.roommate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cs465.litian.roommate.R;

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
        return view;
    }
}
