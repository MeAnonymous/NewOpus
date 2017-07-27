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
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.shivam.opus.dbutil.OCons;
import com.example.shivam.opus.dbutil.OMng;

import java.util.ArrayList;
import java.util.Date;

public class RentedBooks extends AppCompatActivity {
    ListView lv;
    ArrayList<RBook> dist;
    OMng o;
    SQLiteDatabase sb;
    RBook d;
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
        setContentView(R.layout.activity_rented_books);
        o=new OMng(this);
        sb=o.open();
        lv=(ListView)findViewById(R.id.lv);
        dist = new ArrayList<RBook>();
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
        MyAdapter1 ad = new MyAdapter1(this, dist);
        lv.setAdapter(ad);
    }
    public void fillList0(){
        Cursor c = sb.rawQuery("SELECT InvoiceNo, DateOfRent, BookName FROM Issue, Book WHERE Issue.BookId=Book.BookID", null);
        if (c != null && c.moveToFirst()){
            do{
                String name = c.getString(c.getColumnIndex(OCons.BName));
                String inv = c.getString(c.getColumnIndex(OCons.InvoiceNo));
                String longV = c.getString(c.getColumnIndex(OCons.IRentDate));
                long millisecond = Long.parseLong(longV);
                String var = DateFormat.format("dd/MM/yyyy", new Date(millisecond)).toString();
                d = new RBook();
                d.setInv(inv);
                d.setName(name);
                d.setVar(var);
                dist.add(d);
            }while(c.moveToNext());
        }
    }
    public void fillList1(){
        Cursor c = sb.rawQuery("SELECT MemberId, InvoiceNo, BookName FROM Issue, Book WHERE Issue.BookId=Book.BookID", null);
        if (c != null && c.moveToFirst()){
            do{
                String name = c.getString(c.getColumnIndex(OCons.BName));
                String inv = c.getString(c.getColumnIndex(OCons.InvoiceNo));
                String var = c.getString(c.getColumnIndex(OCons.IMId));
                d = new RBook();
                d.setInv(inv);
                d.setName(name);
                d.setVar(var);
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
                Intent i = new Intent(RentedBooks.this, Log.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(RentedBooks.this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });
        ad.setNegativeButton("No", null);
        AlertDialog adb = ad.create();
        adb.show();
    }
}
