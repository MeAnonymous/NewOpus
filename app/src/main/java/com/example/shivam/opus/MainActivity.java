package com.example.shivam.opus;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shivam.opus.dbutil.OMng;

public class MainActivity extends AppCompatActivity {
    OMng o;
    SQLiteDatabase sb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        o=new OMng(this);
        sb=o.open();
        Intent i =new Intent(this,Log.class);
        startActivity(i);

        //Bonjour ca va?
    }
}
