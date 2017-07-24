package com.example.shivam.opus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddBooks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_books);
        //call this activity after AddCategory, if this the user's first time
    }
}
