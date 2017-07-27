package com.example.shivam.opus;

import android.content.ContentValues;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.opus.dbutil.OCons;
import com.example.shivam.opus.dbutil.OMng;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReturnBooks extends AppCompatActivity {
    OMng o;
    SQLiteDatabase sb;
    EditText e1,e2;
    TextView t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide notification/status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Enable actionbar to display custom_bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //To really display our own custom_bar
        getSupportActionBar().setCustomView(R.layout.custom_ab);
        setContentView(R.layout.activity_return_books);
        e1=(EditText)findViewById(R.id.txtbid);
        e2=(EditText)findViewById(R.id.txtmemid);
        t1=(TextView)findViewById(R.id.lbinvoice);
        t2=(TextView)findViewById(R.id.lbtotalcost);
        o=new OMng(this);
        sb=o.open();
    }
    public void returnBooks(View v) throws ParseException {
        String bid=e1.getText().toString();
        String mid=e2.getText().toString();
        //Cursor c=sb.rawQuery("SEARCH DateOfReturn FROM Issue WHERE MemberId=\""+mid+"\"",null);
        Cursor c = sb.rawQuery("SELECT InvoiceNo, DateOfReturn, TotalCost FROM Issue WHERE MemberId=\""+mid+"\" AND BookId=\""+bid+"\"",null);
        if (c != null && c.moveToFirst()) {
            String inv = c.getString(c.getColumnIndex(OCons.InvoiceNo));
            int tc = c.getInt(c.getColumnIndex(OCons.ITCost));
            t1.setText(inv);
            //Toast.makeText(this, inv, Toast.LENGTH_SHORT).show();
            String ret = c.getString(c.getColumnIndex(OCons.IReturnDate));
            String aret = String.valueOf(System.currentTimeMillis());
            long msec1 = Long.parseLong(ret);
            String var1 = DateFormat.format("dd/MM/yyyy", new Date(msec1)).toString();
            long msec2 = Long.parseLong(aret);
            String var2 = DateFormat.format("dd/MM/yyyy", new Date(msec2)).toString();
            Date date1;
            Date date2;
            SimpleDateFormat dates = new SimpleDateFormat("dd/MM/yyyy");

            //Setting dates
            date1 = dates.parse(var1);
            date2 = dates.parse(var2);
            long difference = date2.getTime() - date1.getTime() ;
            long differenceDates = difference / (24 * 60 * 60 * 1000);
            if(differenceDates <= 0){
                t2.setText(String.valueOf(tc));
            }else{
                tc = (int) (tc + (differenceDates*10));
                t2.setText(String.valueOf(tc));
            }
            Cursor c1 = sb.rawQuery("SELECT RemainingCopies FROM Book WHERE BookID=\""+bid+"\"",null);
            if (c1 != null && c1.moveToFirst()) {
                int rem = c1.getInt(c1.getColumnIndex(OCons.BRCopies));
                rem++;
                sb.execSQL("UPDATE Book SET RemainingCopies=" + rem + " WHERE BookID="+bid);
                //ContentValues cv = new ContentValues();
                //cv.put(OCons.BRCopies, rem);
                //sb.update(OCons.BTable, cv, "BookID=" + bid, null);
                sb.execSQL("DELETE FROM Issue WHERE MemberId=\"" + mid + "\" AND BookId=\"" + bid + "\"");
            }
            //String dayDifference = Long.toString(differenceDates);
             c.close();
            c1.close();
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
                Intent i = new Intent(ReturnBooks.this, Log.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(ReturnBooks.this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });
        ad.setNegativeButton("No", null);
        AlertDialog adb = ad.create();
        adb.show();
    }
}
