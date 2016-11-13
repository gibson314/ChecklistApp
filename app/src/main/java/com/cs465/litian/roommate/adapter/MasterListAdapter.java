package com.cs465.litian.roommate.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cs465.litian.roommate.R;
import com.cs465.litian.roommate.model.item;

import java.util.ArrayList;


/**
 * Created by litia on 11/13/2016.
 */

public class MasterListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<String> mDatas = new ArrayList<>();
    private ViewHolder holder;

    public MasterListAdapter(Context context, ArrayList<String> mDatas) {
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.masterlistitem, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.item_name);
            holder.add = (ImageView) convertView.findViewById(R.id.item_add);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final item entity = (item) getItem(position);
        holder.name.setText(entity.getName());


        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });



        return convertView;
    }

    private class ViewHolder {
        TextView name;
        ImageView add;
    }

}
