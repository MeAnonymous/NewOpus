package com.example.shivam.opus;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shivam.opus.dbutil.OCons;
import com.example.shivam.opus.dbutil.OMng;
//For shopkeeper Login
public class Log extends AppCompatActivity {
    OMng o;
    SQLiteDatabase sb;
    EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log2);
        e1=(EditText)findViewById(R.id.UserId);
        e2=(EditText)findViewById(R.id.UserPass);
        o=new OMng(this);
        sb=o.open();

    }
    public void submit(View v){
        String id=e1.getText().toString();
        String pass=e2.getText().toString();
        String type="Shopkeeper";
        String type2 = "Helper";
        ContentValues cv= new ContentValues();
        cv.put(OCons.UId,id);
        cv.put(OCons.UPass,pass);
        cv.put(OCons.UType,type);
        //if this activity gets called by mainmenu activity for helper account
        //*** cv.put(OCons.UType,type1);
        //firt we need to check whether UID is already inserted or not
        //*** code will go here
        //else
            Long l=sb.insert(OCons.LTable,null,cv);
        if(l>0)
            Toast.makeText(this, "Welcome Admin", Toast.LENGTH_SHORT).show();
    }
}
