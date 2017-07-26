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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shivam.opus.dbutil.OCons;
import com.example.shivam.opus.dbutil.OHelp;
import com.example.shivam.opus.dbutil.OMng;

import java.util.List;

public class AddBooks extends AppCompatActivity {
    SQLiteDatabase sb;
    OMng o;
    EditText e1,e2,e3;
    //Spinner c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide notification/status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Enable actionbar to display custom_bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //To really display our own custom_bar
        getSupportActionBar().setCustomView(R.layout.custom_ab);

        setContentView(R.layout.activity_add_books);
        //call this activity after AddCategory, if this the user's first time
        o=new OMng(this);
        sb=o.open();
        e1=(EditText)findViewById(R.id.txtbid);
        e2=(EditText)findViewById(R.id.txtbname);
        e3=(EditText)findViewById(R.id.txttcopy);
       // c=(Spinner)findViewById(R.id.spcat);

    }
  /*  public void spin(View v){
       OHelp db= new OHelp(getApplicationContext(), OCons.DB_Name,null,OCons.DB_Version);
        List<String> labels = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        c.setAdapter(dataAdapter);

    }*/
    public void addBooks(View v){
        String id=e1.getText().toString();
        String name=e2.getText().toString();
        String tcopies=e3.getText().toString();
       // String sp=c.getSelectedItem().toString();
       // String t="select "+OCons.CId+" from "+OCons.CTable+" where "+OCons.CName+"="+sp;
        ContentValues cv=new ContentValues();
        cv.put(OCons.BId,id);
        cv.put(OCons.BName,name);
        cv.put(OCons.BTCopies,tcopies);
        cv.put(OCons.BRCopies,tcopies);
       // cv.put(OCons.BCId,t);
        long l=sb.insert(OCons.BTable,null,cv);

        Toast.makeText(this, "blahblahblah", Toast.LENGTH_SHORT).show();

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
                Intent i = new Intent(AddBooks.this, Log.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(AddBooks.this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });
        ad.setNegativeButton("No", null);
        AlertDialog adb = ad.create();
        adb.show();
    }
}
