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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.opus.dbutil.OMng;

import org.w3c.dom.Text;

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
    }
    public void returnBooks(View v){
        String bid=e1.getText().toString();
        String mid=e2.getText().toString();
        Cursor c=sb.rawQuery("SEARCH DateOfReturn FROM Issue WHERE MemberId=\""+mid+"\"",null);
        if (c != null && c.moveToFirst()) {
            //long returndate = c.getLong(c.getColumnIndex(---));
            c.close();
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
