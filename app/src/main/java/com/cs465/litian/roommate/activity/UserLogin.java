package com.cs465.litian.roommate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cs465.litian.roommate.MainActivity;
import com.cs465.litian.roommate.R;
import com.cs465.litian.roommate.Tools.GlobalParameterApplication;
import com.cs465.litian.roommate.fragment.MylistFragment;

/**
 * Created by litia on 11/15/2016.
 */
public class UserLogin extends AppCompatActivity {
    Button button = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        EditText name = (EditText)findViewById(R.id.EnterOnlineID);
        GlobalParameterApplication.setPeopleList(name);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlogin);
        button = (Button)findViewById(R.id.Signin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalParameterApplication.setLoginStatus(1);
                Intent intent = new Intent(UserLogin.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}