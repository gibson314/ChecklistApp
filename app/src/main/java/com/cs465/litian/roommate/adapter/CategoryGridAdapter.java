package com.cs465.litian.roommate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs465.litian.roommate.R;
import com.cs465.litian.roommate.model.category;

import java.util.List;

import javax.microedition.khronos.opengles.GL;

/**
 * Created by litia on 11/15/2016.
 */

public class CategoryGridAdapter extends ArrayAdapter<category>{
    private LayoutInflater mInflater;
    private int resourceId;
    public category c;

    public CategoryGridAdapter (Context context,
                           int textViewResourseId,
                           List<category> objects) {
        super(context, textViewResourseId, objects);
        resourceId = textViewResourseId;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        c = getItem(position);


        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

        ImageView cImage = (ImageView) view.findViewById(R.id.category_img);
        TextView cName = (TextView) view.findViewById(R.id.category_name);
        cImage.setImageResource(c.getImg());
        cName.setText(c.getName());
        return view;
    }
}
