package com.cs465.litian.roommate;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by litian on 11/13/16.
 */

public class MyLeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "YxBXEoHYDKYwou5uXiturGkr-MdYXbMMI", "jTxEUb4kYcYr8leMEMWvpkRV");
    }
}
