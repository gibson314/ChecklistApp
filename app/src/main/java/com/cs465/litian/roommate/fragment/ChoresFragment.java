package com.cs465.litian.roommate.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cs465.litian.roommate.R;
import com.cs465.litian.roommate.Tools.GlobalParameterApplication;
import com.cs465.litian.roommate.activity.UserLogin;
import com.cs465.litian.roommate.activity.UserProfile;
import com.cs465.litian.roommate.adapter.ExpandableListAdapter;
import com.cs465.litian.roommate.database.ItemDBHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by litian on 11/13/16.
 */

/*public class ChoresFragment extends SupportFragment {
    private PagerAdapter mAdapter;


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;



    public static ChoresFragment newInstance(){
        Bundle args = new Bundle();
        ChoresFragment fragment = new ChoresFragment();
        fragment.setArguments(args);
        return fragment;
    }
*/
/*
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mylist_chores, container, false);
        //setContentView(R.layout.activity_main);

        // getSupportActionBar().setDisplayShowTitleEnabled(true);
        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

        //toolbar_menu = (Toolbar) findViewById(R.id.toolbar_menu);
        //tab1 = (Toolbar) findViewById(R.id.chore);
        //tab2 = (Toolbar) findViewById(R.id.reciept);
        //tab3 = (Toolbar) findViewById(R.id.other);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

//        expListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                TextView tv = (TextView) view.findViewById(R.id.lblListItem);
//                String data = tv.getText().toString();
//                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
//            }
//        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                TextView tv = (TextView) view.findViewById(R.id.lblListItem);
                String data = tv.getText().toString();
                Toast.makeText(getActivity(), data, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return view;
    }*/

    /*
     * Preparing the list data
     */
   /* private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data


        listDataHeader.add("David");
        listDataHeader.add("Devaraj");
        listDataHeader.add("Litian");
        listDataHeader.add("Kate");
        listDataHeader.add("Caiwei");
        listDataHeader.add("All");

        // Adding child data
        List<String> all = new ArrayList<String>();

        List<String> david = new ArrayList<String>();
        david.add("Dishes");
        david.add("Wash the countertops");
        all.add("Dishes");
        all.add("Wash the countertops");

        List<String> dev = new ArrayList<String>();
        dev.add("Vacuuum");
        all.add("Vacuuum");

        List<String> lit = new ArrayList<String>();
        lit.add("Take out the trash");
        lit.add("Take out the recycling");
        all.add("Take out the trash");
        all.add("Take out the recycling");

        List<String> kate = new ArrayList<String>();
        kate.add("Walk the dog");
        all.add("Walk the dog");

        List<String> cai = new ArrayList<String>();
        cai.add("Clean the bathroom");
        all.add("Clean the bathroom");


        listDataChild.put(listDataHeader.get(0), david); // Header, Child data
        listDataChild.put(listDataHeader.get(1), dev);
        listDataChild.put(listDataHeader.get(2), lit);
        listDataChild.put(listDataHeader.get(3), kate);
        listDataChild.put(listDataHeader.get(4), cai);
        listDataChild.put(listDataHeader.get(5), all);



    }
}*/

public class ChoresFragment extends SupportFragment {

    private PagerAdapter mAdapter;

    private SQLiteDatabase db;


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;




    public static ChoresFragment newInstance(){
        Bundle args = new Bundle();
        ChoresFragment fragment = new ChoresFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public Handler nHandler = new Handler() { //handler for the item clicks
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
                            String data = tv.getText().toString();                          //THIS IS HOW ITEMS GET DELETED
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
        View view = inflater.inflate(R.layout.mylist_chores, container, false);

        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.chores_list);



        // preparing list data, method down below
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
        android.support.design.widget.FloatingActionButton refresh_btn = (android.support.design.widget.FloatingActionButton) view.findViewById(R.id.chores_refresh);
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
                        "CHORES",  // The table to query
                        null,
                        null,                                // The columns for the WHERE clause
                        null,                            // The values for the WHERE clause
                        null,                                     // don't group the rows
                        null,                                     // don't filter by row groups
                        null                                      // The sort order
                );

                if (c != null) {
                    for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) { //iterate through the DB
                        String cg = c.getString(c.getColumnIndex("personAssigned"));
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
        db.delete("CHORES",
                "itemName=?",
                new String[]{itemName}
        );

    }



}

