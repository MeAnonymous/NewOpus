package com.example.shivam.opus;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.opus.dbutil.OCons;
import com.example.shivam.opus.dbutil.OMng;

public class CreateHelper extends AppCompatActivity {
    OMng o;
    SQLiteDatabase sb;
    EditText e1,e2;
    Intent i;
    TextView tv;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide notification/status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Enable actionbar to display custom_bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //To really display our own custom_bar
        getSupportActionBar().setCustomView(R.layout.custom_ab);

        setContentView(R.layout.activity_create_helper);
        e1=(EditText)findViewById(R.id.UserId);
        e2=(EditText)findViewById(R.id.UserPass);
        o=new OMng(this);
        sb=o.open();
    }
    public void addHelper(View v){
        String id = e1.getText().toString();
        String pass = e2.getText().toString();
        SharedPreferences sharedPrefs = getSharedPreferences("myFile", MODE_PRIVATE);
        SharedPreferences.Editor ed;
        ed = sharedPrefs.edit();
        String type="Helper"; //Add Helper
        ContentValues cv= new ContentValues();
        cv.put(OCons.UId,id);
        cv.put(OCons.UPass,pass);
        cv.put(OCons.UType,type);
        Long l=sb.insert(OCons.LTable, null, cv);
            if(l>0){
                toast =  Toast.makeText(this, "Helper Added", Toast.LENGTH_SHORT);
                tv = (TextView) toast.getView().findViewById(android.R.id.message);
                tv.setBackgroundColor(Color.alpha(0));
                tv.setTextColor(Color.WHITE);
                toast.show();
            }
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
                Intent i = new Intent(CreateHelper.this, Log.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(CreateHelper.this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });
        ad.setNegativeButton("No", null);
        AlertDialog adb = ad.create();
        adb.show();
    }
}
