package com.example.shivam.opus;


import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.example.shivam.opus.dbutil.OCons;
import com.example.shivam.opus.dbutil.OMng;



public class AddMembers extends AppCompatActivity  {

    OMng o;
    SQLiteDatabase sb;
    EditText e1,e2,e3,e4,e5,e6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide notification/status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Enable actionbar to display custom_bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //To really display our own custom_bar
        getSupportActionBar().setCustomView(R.layout.custom_ab);

        setContentView(R.layout.activity_add_members);
        o = new OMng(this);
        sb = o.open();

        e1 = (EditText) findViewById(R.id.txtname);
        e2 = (EditText) findViewById(R.id.txtmemid);
        e3 = (EditText) findViewById(R.id.txtemail);
        e4 = (EditText) findViewById(R.id.txtaddress);
        e5 = (EditText) findViewById(R.id.txtphone);
        e6 = (EditText) findViewById(R.id.txtdob);
    }
    public void add(View v){
        String name=e1.getText().toString();
        String id=e2.getText().toString();
        String email=e3.getText().toString();
        String address=e4.getText().toString();
        String phone=e5.getText().toString();
        String dob=e6.getText().toString();
        String fee="500";//membershipfee
        ContentValues cv= new ContentValues();
        cv.put(OCons.MName,name);
        cv.put(OCons.MId,id);
        cv.put(OCons.MEmail,email);
        cv.put(OCons.MAdd,address);
        cv.put(OCons.MPhN,phone);
        cv.put(OCons.MDob,dob);
        cv.put(OCons.MFee,fee);
        long l=sb.insert(OCons.MTable,null,cv);
        if(l>0)
            Toast.makeText(this, "Member Added", Toast.LENGTH_SHORT).show();
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
                Intent i = new Intent(AddMembers.this, Log.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(AddMembers.this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });
        ad.setNegativeButton("No", null);
        AlertDialog adb = ad.create();
        adb.show();
    }
}
