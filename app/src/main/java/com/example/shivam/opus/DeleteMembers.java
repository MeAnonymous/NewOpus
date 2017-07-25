package com.example.shivam.opus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class DeleteMembers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide notification/status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Enable actionbar to display custom_bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //To really display our own custom_bar
        getSupportActionBar().setCustomView(R.layout.custom_ab);

        setContentView(R.layout.activity_delete_members);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void done(View v){
        Intent i=new Intent(this,Main.class);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
        startActivity(i, bundle);
    }
    public void logout(View v){
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Do you want to logout?");
        ad.setMessage("");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(DeleteMembers.this, Log.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(DeleteMembers.this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });
        ad.setNegativeButton("No", null);
        AlertDialog adb = ad.create();
        adb.show();
    }
}
