package com.cs465.litian.roommate.fragment;

import android.content.ClipData;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

import com.cs465.litian.roommate.R;
import com.cs465.litian.roommate.adapter.ExpandableListAdapter;
import com.cs465.litian.roommate.database.ItemDBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Handler;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by litian on 11/13/16.
 */
public class PrivateListFragment extends SupportFragment {

    private PagerAdapter mAdapter;

    private SQLiteDatabase db;


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;




    public static PrivateListFragment newInstance(){
        Bundle args = new Bundle();
        PrivateListFragment fragment = new PrivateListFragment();
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
        View view = inflater.inflate(R.layout.mylist_private, container, false);

        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.private_list);



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

        android.support.design.widget.FloatingActionButton refresh_btn = (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.private_refresh);
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
                ItemDBHelper dbHelper = new ItemDBHelper(getActivity().getApplicationContext());
                db = dbHelper.getReadableDatabase();
                //"itemStatus=?"new String[]{"1"}
                Cursor c = db.query(
                        "LIST",  // The table to query
                        null,
                        null,                                // The columns for the WHERE clause
                        null,                            // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        null                                      // The sort order
                );

                if (c != null) {
                    for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                        String cg = c.getString(c.getColumnIndex("itemCategory"));
                        String nm = c.getString(c.getColumnIndex("itemName"));
                        Log.i("Query!", cg+" "+nm);
                        if (!listDataHeader.contains(cg)){
                            listDataHeader.add(cg);
                            listDataChild.put(cg, new ArrayList<String>(){});
                        }
                        List<String> ori = listDataChild.get(cg);
                        ori.add(nm);
                        listDataChild.put(cg, ori);
                    }
                }
                c.close();
                Message msg = new Message();
                msg.what = 123;
                nHandler.sendMessage(msg);
            }
        }).start();




    }

    private void deleteItem(String itemName) {
        ItemDBHelper mDbHelper = new ItemDBHelper(getActivity().getApplicationContext());
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.delete("LIST",
                "itemName=?",
                new String[]{itemName}
        );

    }



}





