package com.cs465.litian.roommate.popupwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.cs465.litian.roommate.R;

/**
 * Created by litia on 11/15/2016.
 */

public class add_popup extends PopupWindow {

    public add_popup(Context context, Activity activity) {
        setContentView(LayoutInflater.from(context).inflate(R.layout.popup_card, null));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(300);
        setFocusable(true);
        setAnimationStyle(R.style.PopupWindowStyle);

        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // Disable default animation for circular reveal
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setAnimationStyle(0);
        }
    }
}
