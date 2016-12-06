package com.cs465.litian.roommate.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.cs465.litian.roommate.R;

import me.khrystal.library.widget.CircularViewMode;
import me.khrystal.library.widget.ItemViewMode;
import me.khrystal.library.widget.ScaleXViewMode;
import me.khrystal.library.widget.ScaleYViewMode;
import me.yokeyword.fragmentation.SupportFragment;

import com.cs465.litian.roommate.Tools.GlobalParameterApplication;
import com.cs465.litian.roommate.activity.UserLogin;
import com.cs465.litian.roommate.activity.UserProfile;
import com.cs465.litian.roommate.adapter.CategoryGridAdapter;
import com.cs465.litian.roommate.adapter.MasterListAdapter;
import com.cs465.litian.roommate.database.ItemDBHelper;
import com.cs465.litian.roommate.model.category;
import com.cs465.litian.roommate.model.item;

import java.util.ArrayList;
import java.util.List;

import com.cs465.litian.roommate.popupwindow.add_popup;
import com.cs465.litian.roommate.popupwindow.category_popup;
import com.labo.kaji.relativepopupwindow.RelativePopupWindow;
import com.tencent.qc.stat.common.User;
import me.khrystal.library.widget.CircleRecyclerView;
import com.bumptech.glide.Glide;
import com.cs465.litian.roommate.adapter.CircleRecyclerAdapter;

import org.w3c.dom.Text;

/**
 * Created by litia on 11/10/2016.
 */

public class MasterlistFragment extends SupportFragment {
    private ListView itemlist;
    private ArrayList<item> itemlist_data = new ArrayList<>();
    private TextView bathroom, kitchen;
    private MasterListAdapter masterlistadapter;
    private category_popup pp;
    private Spinner sp;
    private View add_pop;
    private add_popup add_pop_window;
    private String current_item_added;
    private String current_item_category;
    private item current_item;
    private static String addToPos = "Private List";
    private ArrayAdapter<String> add_adapter;

    private CircleRecyclerView mCircleRecyclerView;
    private ItemViewMode mItemViewMode;
    private LinearLayoutManager mLayoutManager;

    private SQLiteDatabase db;

    private String[] cg = {"Laundry", "Food", "Clothing", "Bedroom", "Bathroom", "Kitchen", "School", "Chores"};

    public static MasterlistFragment newInstance() {
        Bundle args = new Bundle();
        MasterlistFragment fragment = new MasterlistFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public class thread extends Thread {
        private String type;
        public thread(String type) {
            this.type = type;
        }
        @Override
        public void run() {
            super.run();
            getData(type);
//            Message msg = new Message();
//            msg.what = 123;
//            nHandler.sendMessage(msg);
        }
    }

    public class add_private_thread extends Thread {
        private item cur_item;

        public add_private_thread(item cur) {
            this.cur_item = cur;
        }

        @Override
        public void run() {
            super.run();
            ItemDBHelper dbHelper = new ItemDBHelper(getActivity().getApplicationContext());
            db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("itemName", current_item.getName());
            values.put("itemCategory", current_item.getCategory());
            //values.put("itemStatus", 1);
            db.insert(
                    "LIST",
                    null,
                    values);
            Log.i("Insert", "!");

//            Fragment frg = null;
//            frg = getSupportFragmentManager().findFragmentByTag("Your_Fragment_TAG");
//            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.detach(frg);
//            ft.attach(frg);
//            ft.commit();

        }
    }

//    public Handler nHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case 123:
//                    masterlistadapter.notifyDataSetChanged();
//                    Log.i("Changed!!", itemlist_data.toString());
////                    masterlistadapter = new MasterListAdapter(
////                            getActivity(),
////                            itemlist_data);
////                    itemlist.setAdapter(masterlistadapter);
//            }
//        }
//    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        //getActivity().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.masterlist_fragment, container, false);
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.ml_toolbar);

        // add pop up window
        add_pop = LayoutInflater.from(getActivity()).inflate(R.layout.add_popup_window, null);
        add_pop_window = new add_popup(view.getContext(), getActivity());
        add_pop_window.setContentView(add_pop);



        ImageView user_btn = (ImageView) view.findViewById(R.id.user);
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


        itemlist = (ListView) view.findViewById(R.id.item_list);
        //=====================================================
//        bathroom = (TextView) view.findViewById(R.id.bathroom);
//        kitchen = (TextView) view.findViewById(R.id.kitchen);
//        bathroom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getData("Bathroom");
//
//            }
//            }
//        );
//
//        kitchen.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            getData("Kitchen");
//                                            masterlistadapter.notifyDataSetChanged();
//                                        }
//                                    }
//        );

        mCircleRecyclerView = (CircleRecyclerView) view.findViewById(R.id.circle_rv);
        mItemViewMode = new ScaleXViewMode();
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mCircleRecyclerView.setLayoutManager(mLayoutManager);
        mCircleRecyclerView.setViewMode(mItemViewMode); // T implements ItemViewMode, after setLayoutManager(manager)
        mCircleRecyclerView.setNeedCenterForce(true); // when SCROLL_STATE_IDLE == state, nearly center itemview scroll to center

        mCircleRecyclerView.setNeedLoop(true); // default is true

// if setCenterForce(true), can set this callback

        mCircleRecyclerView.setOnCenterItemClickListener(new CircleRecyclerView.OnCenterItemClickListener() {
            @Override
            public void onCenterItemClick(View v) {
                TextView t = (TextView) v.findViewById(R.id.item_text);
                //Toast.makeText(getContext(), t.getText() + "Center Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        CircleRecyclerAdapter rAdapter = new CircleRecyclerAdapter(cg);
        mCircleRecyclerView.setAdapter(rAdapter);
//        rAdapter.setOnItemClickListener(new CircleRecyclerAdapter.MyOnItemClickListener(){
//            @Override
//            public void OnItemClickListener(View view, int i) {
//                Toast.makeText(getContext(), i + " Clicked", Toast.LENGTH_SHORT).show();
//            }
//        });

        //======================================================

        ImageButton category_button = (ImageButton) view.findViewById(R.id.ml_category);
        category_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //pp.showOnAnchor(view, RelativePopupWindow.VerticalPosition.ALIGN_TOP, RelativePopupWindow.HorizontalPosition.LEFT, -95, 0);
                pp.showAsDropDown(view, -550, 0, Gravity.CENTER_HORIZONTAL);

                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 0.6f; //0.0-1.0
                getActivity().getWindow().setAttributes(lp);
            }
        });


        pp = new category_popup(view.getContext(), getActivity());
        CategoryGridAdapter adapter = new CategoryGridAdapter(getActivity(), R.layout.category_item, GlobalParameterApplication.getCategory_list());
        GridView grid = (GridView) pp.getContentView().findViewById(R.id.category_grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener( new AdapterView.OnItemClickListener() {
                                         @Override
                                         public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                             category c = GlobalParameterApplication.getCategory_list().get(i);
                                             getData(c.getName());
                                             pp.dismiss();
                                         }
                                     }
        );


        pp.setOnDismissListener(
                new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                        lp.alpha = 1.0f; //0.0-1.0
                        getActivity().getWindow().setAttributes(lp);
                    }
                }
        );


        //==================================== Add item to list =====================================
        ImageView add_img = (ImageView) add_pop_window.getContentView().findViewById(R.id.pop_add_img);
        Spinner sp = (Spinner) add_pop_window.getContentView().findViewById(R.id.add_spinner);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        addToPos="Private List";
                        Toast.makeText(parent.getContext(), addToPos, Toast.LENGTH_SHORT).show();

                        break;
                    case 1:
                        addToPos="Public List";
                        Toast.makeText(parent.getContext(), addToPos, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });

        add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity().getApplicationContext(), "Add " + current_item.getName() + " to " + addToPos, Toast.LENGTH_SHORT).show();
                if (addToPos.equals("Private List")) {
                    add_private_thread aThread = new add_private_thread(current_item);
                    Log.i("??", "!!");
                    aThread.run();
                }
                else if (addToPos.equals("Public List")) {
                    AVObject todoFolder = new AVObject("PublicList");//
                    todoFolder.put("itemCategory", current_item.getCategory());//
                    todoFolder.put("itemName", current_item.getName());//
                    todoFolder.put("Person", "0");
                    todoFolder.saveInBackground();// 保存到服务端
                }
                add_pop_window.dismiss();
            }
        });


        //mThread.start();
        masterlistadapter = new MasterListAdapter(getActivity(), itemlist_data);
        itemlist.setAdapter(masterlistadapter);

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        mCircleRecyclerView.setOnScrollListener(new CircleRecyclerView.OnScrollListener() {
            @Override
            public void onScrollChanged(int l, int t, int oldl, int oldt) {
                //Toast.makeText(getContext(), l + t + oldl + oldt + "Center Clicked", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onScrollStateChanged(int state) {
                Log.i("ScrollStateChanged", state + "");
                if (state == 2) {
                    View v = mCircleRecyclerView.findViewAtCenter();
                    final TextView t = (TextView) v.findViewById(R.id.item_text);
                    //Toast.makeText(getContext(), t.getText() + "Center Clicked", Toast.LENGTH_SHORT).show();
                    thread gThread = new thread(t.getText().toString());
                    gThread.start();
                }
            }

            @Override
            public void onScrolled(int dx, int dy) {
            }
        });

    }

//    public void initData() {
//
//        itemlist_data.add(new item("Bowls", 1));
//        itemlist_data.add(new item("Dish washer", 0));
//        itemlist_data.add(new item("Knives", 2));
//    }


    public void getData(String category){

        itemlist_data.clear();
        Log.i("Data changed", "1 " + category);
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
                Log.i("NULL", "!");
                if (list != null) {
                    Log.i("XXX", list.size() + " ");
                    for (int i = 0; i < list.size(); ++i) {
                        itemlist_data.add(new item(list.get(i).getString("ItemName"), list.get(i).getString("ItemCategory"), 0));
                    }
                    Log.i("List", itemlist_data.toString());
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
                    Toast.makeText(getActivity().getApplicationContext(), "Add to List Pop up", Toast.LENGTH_SHORT).show();
                    add_pop_window.showAtLocation(view, Gravity.BOTTOM, 0, 220);
                    //current_item_added = entity.getName();
                    //current_item_category = entity.getCategory();
                    current_item = entity;
                }
            });


            return convertView;
        }

        class ViewHolder {
            TextView name;
            ImageView add;
        }


    }



    class SpinnerSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

        }

        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
}

