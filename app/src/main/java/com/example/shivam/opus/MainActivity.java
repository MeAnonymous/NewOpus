//This activity is intended to create all the tables, only.
package com.example.shivam.opus;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shivam.opus.dbutil.OMng;

public class MainActivity extends AppCompatActivity {
    OMng o;
    SQLiteDatabase sb;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        o = new OMng(this);
        sb = o.open();
        //sb.delete("Login",null,null);
        String count = "SELECT count(*) FROM Login";
        Cursor c = sb.rawQuery(count, null);
        c.moveToFirst();
        int icount = c.getInt(0);
        if(icount>0){
            i = new Intent(this, Log.class);
            startActivity(i);
            this.finish();
        } else{
            i = new Intent(this, MakeAccount.class);
            startActivity(i);
            this.finish();
        }
    }
}
