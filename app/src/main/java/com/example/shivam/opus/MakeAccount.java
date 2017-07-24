package com.example.shivam.opus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MakeAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_account);
        //Use shared preferences to check if this is the first time a user has opened the app on his phone
        //if true
            //show the activity
        //else
            //jump to log activity
    }
}
