package com.example.shivam.opus;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shivam.opus.dbutil.OCons;
import com.example.shivam.opus.dbutil.OMng;

public class AddCategory extends AppCompatActivity {
    EditText e1,e2;
    OMng o;
    SQLiteDatabase sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide notification/status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Enable actionbar to display custom_bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //To really display our own custom_bar
        getSupportActionBar().setCustomView(R.layout.custom_ab);

        setContentView(R.layout.activity_add_category);
        //this activity will also get called after log activity, if the user has opened the app for the first time.
        o=new OMng(this);
        sb=o.open();
        e1=(EditText)findViewById(R.id.txtcid);
        e2=(EditText)findViewById(R.id.txtcname);
    }
    public void done(View v){
        String id=e1.getText().toString();
        String name=e2.getText().toString();
        ContentValues cv=new ContentValues();
        cv.put(OCons.CId,id);
        cv.put(OCons.CName,name);
        long l=sb.insert(OCons.CTable,null,cv);
        if(l>0)
            Toast.makeText(this, "AddedCategory", Toast.LENGTH_SHORT).show();

    }
}
