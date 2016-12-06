package com.cs465.litian.roommate.Tools;

/**
 * Created by litian on 2016/4/23.
 */

import android.app.Application;
import android.provider.Settings;
import android.widget.EditText;

import com.cs465.litian.roommate.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import com.cs465.litian.roommate.model.category;

public class GlobalParameterApplication extends Application{

    private static String publicgroup;

    private static ArrayList<category> category_list = new ArrayList<>();
    private static ArrayList<EditText> people_List = new ArrayList<>();

    private static int LoginStatus;

    public GlobalParameterApplication() {
        category_list.add(new category("All Item", R.drawable.allitem));
        category_list.add(new category("Bedroom", R.drawable.bed));
        category_list.add(new category("Kitchen", R.drawable.kitchen));
        category_list.add(new category("School", R.drawable.school));
        category_list.add(new category("Bathroom", R.drawable.bathroom));
        category_list.add(new category("Laundry", R.drawable.laundry));
        category_list.add(new category("Clothing", R.drawable.clothing));
        category_list.add(new category("Food", R.drawable.food));
        category_list.add(new category("Computer", R.drawable.computer));
        LoginStatus = 0;
    }

    public static void setPeopleList(EditText name)
    {
        people_List.add(name);
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

    public static HashMap<String, Integer> _childtocolor = new HashMap<String, Integer>();


}
