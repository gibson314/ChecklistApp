package com.cs465.litian.roommate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cs465.litian.roommate.MainActivity;
import com.cs465.litian.roommate.R;
import com.cs465.litian.roommate.Tools.GlobalParameterApplication;
import com.cs465.litian.roommate.fragment.MylistFragment;

/**
 * Created by litia on 11/15/2016.
 */

public class UserProfile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile);
        Button logoutbtn = (Button) findViewById(R.id.logout);
        logoutbtn.setOnClickListener(new View.OnClickListener(){
                                         @Override
                                         public void onClick(View v) {
                                             GlobalParameterApplication.setLoginStatus(0);
                                             Intent intent = new Intent(UserProfile.this, UserLogin.class);
                                             startActivity(intent);
                                         }
        }

        );
    }
}
