package com.example.shivam.opus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddCategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        //this activity will also get called after log activity, if the user has opened the app for the first time.
    }
}
