package com.cs465.litian.roommate.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVCloudQueryResult;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.CloudQueryCallback;
import com.avos.avoscloud.FindCallback;
import com.cs465.litian.roommate.R;
import com.cs465.litian.roommate.adapter.ExpandableListAdapter;
import com.cs465.litian.roommate.database.ItemDBHelper;
import com.cs465.litian.roommate.model.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by litian on 11/13/16.
 */

public class PublicListFragment extends SupportFragment  {

    //private PagerAdapter mAdapter;

    //private SQLiteDatabase db;


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;




    public static PublicListFragment newInstance(){
        Bundle args = new Bundle();
        PublicListFragment fragment = new PublicListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public Handler nHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 123:

                    listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

                    // setting list adapter
                    expListView.setAdapter(listAdapter);


                    expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                        @Override
                        public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                            TextView tv = (TextView) view.findViewById(R.id.lblListItem);
                            String data = tv.getText().toString();
                            Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
                            deleteItem(data);
                            prepareListData();
                            return true;
                        }
                    });

                    Log.i("Changed!!", "Yeah!");
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mylist_public, container, false);
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.public_list);
        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                TextView tv = (TextView) view.findViewById(R.id.lblListItem);
                String data = tv.getText().toString();
                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
                deleteItem(data);
                prepareListData();
                return true;
            }
        });


        // preparing list data
        prepareListData();
//
//        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);
//
//        // setting list adapter
//        expListView.setAdapter(listAdapter);
//
//
//        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
//                TextView tv = (TextView) view.findViewById(R.id.lblListItem);
//                String data = tv.getText().toString();
//                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
//                return true;
//            }
//        });

        android.support.design.widget.FloatingActionButton refresh_btn = (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.public_refresh);
        refresh_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                prepareListData();
            }
        });
        return view;
    }




    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                AVQuery<AVObject> query = new AVQuery<>("PublicList");
//                query.whereEqualTo("ItemCategory", category);
                query.findInBackground(new FindCallback<AVObject>() {
                    @Override
                    public void done(List<AVObject> list, AVException e) {
                        for (int i = 0; i < list.size(); ++i) {
                            String cg = list.get(i).getString("itemCategory");
                            String nm = list.get(i).getString("itemName");
                            Log.i("CG and NM", cg + " " + nm);
                            Log.i("Query!", cg + " " + nm);
                            if (!listDataHeader.contains(cg)) {
                                listDataHeader.add(cg);
                                listDataChild.put(cg, new ArrayList<String>() {
                                });
                            }
                            List<String> ori = listDataChild.get(cg);
                            ori.add(nm);
                            listDataChild.put(cg, ori);
                        }
                        listAdapter.notifyDataSetChanged();
                    }
                });
                Message msg = new Message();
                msg.what = 123;
                nHandler.sendMessage(msg);
            }
        }).start();




    }

    private void deleteItem(String itemName) {
        Log.i("!!", "delete from PublicList where itemName='"+ itemName +"'");
        AVQuery.doCloudQueryInBackground("delete from PublicList where objectId='582d526391e79b0066b6634d'", new CloudQueryCallback<AVCloudQueryResult>() {
            @Override
            public void done(AVCloudQueryResult avCloudQueryResult, AVException e) {
                Log.i("Delete Done", "!");
            }
        });

    }



}
