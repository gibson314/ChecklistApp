package com.cs465.litian.roommate.Tools;

/**
 * Created by litian on 2016/4/23.
 */

import android.app.Application;
import android.provider.Settings;

import com.cs465.litian.roommate.R;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import com.cs465.litian.roommate.model.category;

public class GlobalParameterApplication extends Application{

    private static String publicgroup;

    private static ArrayList<category> category_list = new ArrayList<>();

    private static int LoginStatus;

    public GlobalParameterApplication() {
        category_list.add(new category("All Item", R.drawable.car));
        category_list.add(new category("Bedroom", R.drawable.car));
        category_list.add(new category("Kitchen", R.drawable.car));
        category_list.add(new category("School", R.drawable.car));
        category_list.add(new category("Bathroom", R.drawable.car));
        category_list.add(new category("Laundry", R.drawable.car));
        category_list.add(new category("Clothing", R.drawable.car));
        category_list.add(new category("Food", R.drawable.calendar));
        category_list.add(new category("Computer", R.drawable.calendar));
        LoginStatus = 0;
    }

    public static String getPublicgroup () {
        return publicgroup;
    }
    public static void setPublicgroup(String name) {
        publicgroup = name;
    }
    public static List<category> getCategory_list() {return category_list;}
    public static int getLoginStatus() {
        return LoginStatus;
    }

    public static void setLoginStatus(int s){
        LoginStatus = s;
    }
}
