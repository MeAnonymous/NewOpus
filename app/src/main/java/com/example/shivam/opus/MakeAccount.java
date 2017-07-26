package com.example.shivam.opus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        SharedPreferences sharedPrefs = getSharedPreferences("myFile", MODE_PRIVATE);
        SharedPreferences.Editor ed;
        if(!sharedPrefs.contains("accept")){
            ed = sharedPrefs.edit();
            ed.putInt("accept", 2);
            ed.commit();
        }
    }
    @Override
    public void onBackPressed() {
        return;
    }
    public void make(View v){
       Intent i = new Intent(this, Log.class);
        startActivity(i);
        this.finish();
    }
}
