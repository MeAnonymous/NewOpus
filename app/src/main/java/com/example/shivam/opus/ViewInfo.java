package com.example.shivam.opus;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.shivam.opus.dbutil.OCons;
import com.example.shivam.opus.dbutil.OMng;

import java.util.ArrayList;

public class ViewInfo extends AppCompatActivity {
    ListView lv;
    ArrayList<Book> dist;
    OMng o;
    SQLiteDatabase sb;
    Book d;
    Spinner sp;
    int select = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide notification/status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Enable actionbar to display custom_bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //To really display our own custom_bar
        getSupportActionBar().setCustomView(R.layout.custom_ab);
        setContentView(R.layout.activity_view_info);
        o=new OMng(this);
        sb=o.open();
        lv=(ListView)findViewById(R.id.lv);
        dist = new ArrayList<Book>();
        sp = (Spinner)findViewById(R.id.sp);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) select = 0;
                if(position == 1) select = 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void search(View v){
        if(select == 0) {
            dist.clear();
            fillList0();
        }
        if(select == 1) {
            dist.clear();
            fillList1();
        }
        MyAdapter ad = new MyAdapter(this, dist);
        lv.setAdapter(ad);
    }
    public void fillList0(){//view books
        Cursor c = sb.rawQuery("SELECT BookName, BookID FROM Book", null);
        if (c != null && c.moveToFirst()){
            do{
                String name = c.getString(c.getColumnIndex(OCons.BName));
                String cid = c.getString(c.getColumnIndex(OCons.BId));
                d = new Book();
                d.setCid(cid);
                d.setName(name);
                dist.add(d);
            }while(c.moveToNext());
        }
    }
    public void fillList1(){//view members
        Cursor c = sb.rawQuery("SELECT MemberId, MemberName FROM Member", null);
        if (c != null && c.moveToFirst()){
            do{
                String name = c.getString(c.getColumnIndex(OCons.MName));
                String cid = c.getString(c.getColumnIndex(OCons.MId));
                d = new Book();
                d.setCid(cid);
                d.setName(name);
                dist.add(d);
            }while(c.moveToNext());
        }
    }
    public void logout(View v){
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Do you want to logout?");
        ad.setMessage("");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(ViewInfo.this, Log.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(ViewInfo.this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });
        ad.setNegativeButton("No", null);
        AlertDialog adb = ad.create();
        adb.show();
    }
}
