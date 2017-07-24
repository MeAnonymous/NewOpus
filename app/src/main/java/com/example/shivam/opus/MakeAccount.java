package com.example.shivam.opus;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class MakeAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide notification/status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Enable actionbar to display custom_bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //To really display our own custom_bar
        getSupportActionBar().setCustomView(R.layout.custom_ab1);

        setContentView(R.layout.activity_make_account);
        //Use shared preferences to check if this is the first time a user has opened the app on his phone
        //if true
            //show the activity
        //else
            //jump to log activity
    }
}
