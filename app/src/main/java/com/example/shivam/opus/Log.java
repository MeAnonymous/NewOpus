package com.example.shivam.opus;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.opus.dbutil.OCons;
import com.example.shivam.opus.dbutil.OMng;
//For Shopkeeper and Helper Login
public class Log extends AppCompatActivity {
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
        getSupportActionBar().setCustomView(R.layout.custom_ab1);

        setContentView(R.layout.activity_log2);
        e1=(EditText)findViewById(R.id.UserId);
        e2=(EditText)findViewById(R.id.UserPass);
        o=new OMng(this);
        sb=o.open();
    }
    @Override
    public void onBackPressed() {
        return;
    }
    public void submit(View v){
        String id = e1.getText().toString();
        String pass = e2.getText().toString();

        SharedPreferences sharedPrefs = getSharedPreferences("myFile", MODE_PRIVATE);
        SharedPreferences.Editor ed;
        ed = sharedPrefs.edit();

        if(sharedPrefs.getInt("accept", 0) == 2){ //Add Shopkeeper and login
            ed.putInt("accept", 1);
            ed.putInt("usertype", 1); //1 for shopkeeper, 2 for helper.
            ed.commit();
            String type="Shopkeeper"; //Add shopkeeper
            ContentValues cv= new ContentValues();
            cv.put(OCons.UId,id);
            cv.put(OCons.UPass,pass);
            cv.put(OCons.UType,type);
            Long l=sb.insert(OCons.LTable, null, cv);
            if(l>0){
                toast =  Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT);
                tv = (TextView) toast.getView().findViewById(android.R.id.message);
                tv.setBackgroundColor(Color.alpha(0));
                tv.setTextColor(Color.WHITE);
                toast.show();
            }
            i = new Intent(Log.this, Main.class); //Login to Main Drawer
            startActivity(i);
            Log.this.finish();
        }
        else if(sharedPrefs.getInt("accept", 0) == 1){ //search for record in order to Login
            Cursor c = sb.rawQuery("SELECT UserID, UserPass, Usertype FROM Login WHERE UserID=\""+id+"\"", null);
            if (c != null ) {
                c.moveToFirst();
                String dbpass = c.getString(c.getColumnIndex("UserPass"));
                String type = c.getString(c.getColumnIndex("Usertype"));
                if(dbpass.equals(pass)){
                    toast = Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT);
                    tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setBackgroundColor(Color.alpha(0));
                    tv.setTextColor(Color.WHITE);
                    toast.show();
                    if(type.equals("Shopkeeper"))
                        ed.putInt("usertype", 1); //1 for shopkeeper, 2 for helper.
                    else
                        ed.putInt("usertype", 2);
                    ed.commit();
                    i = new Intent(Log.this, Main.class); //Login to Main Drawer
                    startActivity(i);
                    Log.this.finish();
                }else{
                    toast = Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT);
                    tv = (TextView) toast.getView().findViewById(android.R.id.message);
                    tv.setBackgroundColor(Color.alpha(0));
                    tv.setTextColor(Color.WHITE);
                    toast.show();
                }
            }
            else{
                toast = Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT);
                tv = (TextView) toast.getView().findViewById(android.R.id.message);
                tv.setBackgroundColor(Color.alpha(0));
                tv.setTextColor(Color.WHITE);
                toast.show();
            }
        }
    }
}
