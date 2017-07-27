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
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shivam.opus.dbutil.OCons;
import com.example.shivam.opus.dbutil.OHelp;
import com.example.shivam.opus.dbutil.OMng;

import java.util.List;

public class UpdateBooks extends AppCompatActivity implements Spinner.OnItemSelectedListener{

    SQLiteDatabase sb;
    OMng o;
    Spinner c;
    String label;
    EditText e1,e2,e3;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        //hide notification/status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Enable actionbar to display custom_bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //To really display our own custom_bar
        getSupportActionBar().setCustomView(R.layout.custom_ab);

        setContentView(R.layout.activity_update_books);
        o=new OMng(this);
        sb=o.open();

        e1=(EditText)findViewById(R.id.txtbid);
        e2=(EditText)findViewById(R.id.txtbname);
        e3=(EditText)findViewById(R.id.txttcopy);
        c=(Spinner)findViewById(R.id.spcat);
        c.setOnItemSelectedListener(this);
        loadSpinnerData();


    }
    public void loadSpinnerData(){
        OHelp db = new OHelp(getApplicationContext(),OCons.DB_Name,null,OCons.DB_Version);

        // Spinner Drop down elements
        List<String> lables = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        c.setAdapter(dataAdapter);

    }
    public void updateBooks(View v){
        String id = e1.getText().toString();
        String name = e2.getText().toString();
        String tcopies = e3.getText().toString();
        String exp[] = {OCons.CId};
        String args[] = {label};
        String b = null;
        int existT=0,existR=0;
        int total=Integer.parseInt(tcopies);//from edittext
         Cursor c=sb.rawQuery("SELECT TotalCopies,RemainingCopies FROM Book WHERE BookID=\""+id+"\"",null);
        if (c != null && c.moveToFirst()) {
            existT=c.getInt(c.getColumnIndex(OCons.BTCopies));
            existR=c.getInt(c.getColumnIndex(OCons.BRCopies));
            c.close();

        }
        int newTotal=total+existT;
        int newRemain=newTotal+existR;
        String newT=String.valueOf(newTotal);
        String newR=String.valueOf(newRemain);
        Toast.makeText(this, "original total="+existT+" original remaining="+existR, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "New Total="+newT+" new Remain="+newR, Toast.LENGTH_SHORT).show();

       Cursor c1 = sb.rawQuery("SELECT CatID FROM Category WHERE CatName=\""+label+"\"", null);
        //Cursor c = sb.query(OCons.CTable,exp,OCons.CName,args,null,null,null);
        if (c1 != null && c1.moveToFirst()) {
            b = c1.getString(c1.getColumnIndex(OCons.CId));
            c1.close();
        }
        ContentValues cv=new ContentValues();
        String ar[]={id};
        cv.put(OCons.BName,name);
        cv.put(OCons.BTCopies,newT);
        cv.put(OCons.BRCopies,newR);
        cv.put(OCons.BCId,b);
        long l=sb.update(OCons.BTable,cv,OCons.BId+"=?",ar);
        if(l>0)
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
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
                Intent i = new Intent(UpdateBooks.this, Log.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(UpdateBooks.this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });
        ad.setNegativeButton("No", null);
        AlertDialog adb = ad.create();
        adb.show();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        label = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
//Remaining_copies = Remaining_copies + Updated_total_Copies. And Total_Copies = Total_Copies + Updated_Total_Copies