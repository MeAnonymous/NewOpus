package com.example.shivam.opus;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.opus.dbutil.OCons;
import com.example.shivam.opus.dbutil.OMng;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RentBooks extends AppCompatActivity {
    EditText e1,e2;
    SQLiteDatabase sb;
    OMng o;
    Toast toast;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide notification/status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Enable actionbar to display custom_bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //To really display our own custom_bar
        getSupportActionBar().setCustomView(R.layout.custom_ab);
        o=new OMng(this);
        sb=o.open();

        setContentView(R.layout.activity_rent_books);
        e1=(EditText)findViewById(R.id.txtbid);
        e2=(EditText)findViewById(R.id.txtmemid);
    }
    public void rentBooks(View v) {
        String bid = e1.getText().toString();//bookid
        String mid = e2.getText().toString();//memberid
        String b = null;
        int d=0;//BRCopies
        int flag = 0;
        String holdId = null;
        Cursor c = sb.rawQuery("SELECT BCatID, RemainingCopies FROM Book, Category WHERE BookID=\"" + bid + "\" AND BCatID=CatID", null);
        if (c != null && c.moveToFirst()) {
            b = c.getString(c.getColumnIndex(OCons.BCId));
            d =  c.getInt(c.getColumnIndex(OCons.BRCopies));
            c.close();
        }
        Cursor c1 = sb.rawQuery("SELECT BookId FROM Issue WHERE MemberId=\"" + mid + "\"", null);
        if (c1 != null && c1.moveToFirst()) {
            do{
                holdId = c1.getString(c1.getColumnIndex(OCons.IBId));
                //Toast.makeText(this, holdId, Toast.LENGTH_SHORT).show();
                if(holdId.equals(bid))
                    flag = 1;
            }while(c1.moveToNext());
            c1.close();
        }
        if(d>0 && flag == 0){

        ContentValues cv = new ContentValues();
        cv.put(OCons.IBId, bid);
        cv.put(OCons.IMId, mid);
        cv.put(OCons.ICID, b);
        cv.put(OCons.IRentDate, System.currentTimeMillis());
        cv.put(OCons.IReturnDate, System.currentTimeMillis() + 864000000);
        cv.put(OCons.ITCost,100);

        long l = sb.insert(OCons.ITable, null, cv);
        if (l > 0) {
            toast = Toast.makeText(this, "Book rented", Toast.LENGTH_SHORT);
            tv = (TextView) toast.getView().findViewById(android.R.id.message);
            tv.setBackgroundColor(Color.alpha(0));
            tv.setTextColor(Color.WHITE);
            toast.show();
            d--;
            //Toast.makeText(this, String.valueOf(d), Toast.LENGTH_SHORT).show();
        }
            /*String args[]={bid};
            cv.put(OCons.BRCopies, String.valueOf(d));
            long r=sb.update(OCons.BTable,cv,"BookID="+bid,null);
            if(r>0)
                Toast.makeText(this, "reduced", Toast.LENGTH_SHORT).show();*/
            sb.execSQL("UPDATE Book SET RemainingCopies=" + d + " WHERE BookID="+bid);
            //Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

        }
        else{
            if(flag == 1){
                toast = Toast.makeText(this, "You've already issued this book", Toast.LENGTH_SHORT);
                tv = (TextView) toast.getView().findViewById(android.R.id.message);
                tv.setBackgroundColor(Color.alpha(0));
                tv.setTextColor(Color.WHITE);
                toast.show();
            }
            else {
                toast = Toast.makeText(this, "Book not available", Toast.LENGTH_SHORT);
                tv = (TextView) toast.getView().findViewById(android.R.id.message);
                tv.setBackgroundColor(Color.alpha(0));
                tv.setTextColor(Color.WHITE);
                toast.show();
            }
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
                Intent i = new Intent(RentBooks.this, Log.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(RentBooks.this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });
        ad.setNegativeButton("No", null);
        AlertDialog adb = ad.create();
        adb.show();
    }
}
