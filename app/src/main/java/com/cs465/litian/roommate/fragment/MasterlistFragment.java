package com.cs465.litian.roommate.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.cs465.litian.roommate.R;

import me.yokeyword.fragmentation.SupportFragment;

import com.cs465.litian.roommate.adapter.MasterListAdapter;
import com.cs465.litian.roommate.model.item;

import java.util.ArrayList;
import java.util.List;
import com.cs465.litian.roommate.popupwindow.category_popup;
import com.labo.kaji.relativepopupwindow.RelativePopupWindow;

/**
 * Created by litia on 11/10/2016.
 */

public class MasterlistFragment extends SupportFragment {
    private ListView itemlist;
    private ArrayList<item> itemlist_data = new ArrayList<>();
    private TextView bathroom, kitchen;
    private MasterListAdapter masterlistadapter;

    public static MasterlistFragment newInstance() {
        Bundle args = new Bundle();
        MasterlistFragment fragment = new MasterlistFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public Thread mThread = new Thread() {
        @Override
        public void run() {
            super.run();
            initData();
            Message msg = new Message();
            msg.what = 123;
            nHandler.sendMessage(msg);
        }
    };

    public Handler nHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 123:
                    masterlistadapter = new MasterListAdapter(
                            getActivity(),
                            itemlist_data);

                    itemlist.setAdapter(masterlistadapter);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //getActivity().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.masterlist_fragment, container, false);
        itemlist = (ListView) view.findViewById(R.id.item_list);
        //=====================================================
        bathroom = (TextView) view.findViewById(R.id.bathroom);
        kitchen = (TextView) view.findViewById(R.id.kitchen);
        bathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData("Bathroom");

            }
            }
        );

        kitchen.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            getData("Kitchen");
                                            masterlistadapter.notifyDataSetChanged();
                                        }
                                    }
        );
        //======================================================

        Button category = (Button) view.findViewById(R.id.ml_category);
        category.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                new category_popup(view.getContext()).showOnAnchor(view, RelativePopupWindow.VerticalPosition.ALIGN_TOP, RelativePopupWindow.HorizontalPosition.CENTER);
            }
        });






        mThread.start();



        return view;
    }


    public void initData() {
        itemlist_data.add(new item("Bowls", 1));
        itemlist_data.add(new item("Dish washer", 0));
        itemlist_data.add(new item("Knives", 2));
    }


    public void getData(String category){
        itemlist_data.clear();
        Log.i("Data changed", "1");
//        itemlist_data.add(new item(category, 1));
//        itemlist_data.add(new item("Body wash", 0));
//        itemlist_data.add(new item("Shampoo", 2));
//        itemlist_data.add(new item("Pen Pineapple", 2));
        AVQuery<AVObject> query = new AVQuery<>("Item");
        query.whereEqualTo("ItemCategory", category);
        query.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                //List<AVObject> ItemsInCategory = list;
                if (list != null) {
                    Log.i("XXX", list.size() + " ");
                    for (int i = 0; i < list.size(); ++i) {
                        itemlist_data.add(new item(list.get(i).getString("ItemName"), 0));
                    }
                    masterlistadapter.notifyDataSetChanged();
                }
            }
        });


    }


    public class MasterListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private ArrayList<item> mDatas = new ArrayList<>();
        private ViewHolder holder;

        public MasterListAdapter(Context context, ArrayList<item> mDatas) {
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
                    Toast.makeText(getActivity().getApplicationContext(), "Add to List", Toast.LENGTH_SHORT).show();
                }
            });


            return convertView;
        }

        class ViewHolder {
            TextView name;
            ImageView add;
        }


    }
}

