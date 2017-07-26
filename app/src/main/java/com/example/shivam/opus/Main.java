package com.example.shivam.opus;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.opus.dbutil.OMng;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    OMng o;
    SQLiteDatabase sb;
    Toast toast;
    TextView tv;
    SharedPreferences sharedPrefs = getSharedPreferences("myFile", MODE_PRIVATE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //hide notification/status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Enable actionbar to display custom_bar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        //To really display our own custom_bar
        getSupportActionBar().setCustomView(R.layout.custom_ab2);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        /*ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        o = new OMng(this);
        sb = o.open();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            return;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        Intent i;
        if(id==R.id.addcat){
            if(sharedPrefs.getInt("usertype", 0) == 1) {
                i = new Intent(this, AddCategory.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
            else{
                toast = Toast.makeText(this, "You are not authorised", Toast.LENGTH_SHORT);
                tv = (TextView) toast.getView().findViewById(android.R.id.message);
                tv.setBackgroundColor(Color.alpha(0));
                tv.setTextColor(Color.WHITE);
                toast.show();
            }
        }
        else if (id == R.id.addbooks) {
            i = new Intent(this,AddBooks.class);
            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                    android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
            startActivity(i, bundle);

        } else if (id == R.id.updatebooks) {
            i = new Intent(this,UpdateBooks.class);
            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                    android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
            startActivity(i, bundle);

        } else if (id == R.id.deletebooks) {
            i = new Intent(this,DeleteBooks.class);
            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                    android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
            startActivity(i, bundle);

        } else if (id == R.id.searchbooks) {
            i = new Intent(this,SearchBooks.class);
            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                    android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
            startActivity(i, bundle);

        } else if (id == R.id.addmembers) {
            i = new Intent(this,AddMembers.class);
            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                    android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
            startActivity(i, bundle);

        } else if (id == R.id.updatemembers) {
            i = new Intent(this,UpdateMembers.class);
            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                    android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
            startActivity(i, bundle);

        } else if (id == R.id.deletemembers) {
            i = new Intent(this,DeleteMembers.class);
            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                    android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
            startActivity(i, bundle);

        } else if (id == R.id.createhelper) {
            if(sharedPrefs.getInt("usertype", 0) == 1) {
                i = new Intent(this, CreateHelper.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
            else{
                toast = Toast.makeText(this, "You are not authorised", Toast.LENGTH_SHORT);
                tv = (TextView) toast.getView().findViewById(android.R.id.message);
                tv.setBackgroundColor(Color.alpha(0));
                tv.setTextColor(Color.WHITE);
                toast.show();
            }

        } else if (id == R.id.contactcustomer) {
            if(sharedPrefs.getInt("usertype", 0) == 1) {
                i = new Intent(this, ContactCustomer.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
            else{
                toast = Toast.makeText(this, "You are not authorised", Toast.LENGTH_SHORT);
                tv = (TextView) toast.getView().findViewById(android.R.id.message);
                tv.setBackgroundColor(Color.alpha(0));
                tv.setTextColor(Color.WHITE);
                toast.show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void logout(View v){
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Do you want to logout?");
        ad.setMessage("");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Main.this, Log.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(Main.this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });
        ad.setNegativeButton("No", null);
        AlertDialog adb = ad.create();
        adb.show();
    }
    public void pullout(View v){
        //Toggle drawer open and close event on hamburger icon when a user taps on it.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void rent(View v){
        Intent i = new Intent(this, RentBooks.class);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
        startActivity(i, bundle);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void searchBooks(View v){
        Intent i = new Intent(this, SearchBooks.class);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
        startActivity(i, bundle);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void returnBooks(View v){
        Intent i = new Intent(this, ReturnBooks.class);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
        startActivity(i, bundle);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void rentedBooks(View v){
        if(sharedPrefs.getInt("usertype", 0) == 1) {
            Intent i = new Intent(this, RentedBooks.class);
            Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                    android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
            startActivity(i, bundle);
        }
        else{
            toast = Toast.makeText(this, "You are not authorised", Toast.LENGTH_SHORT);
            tv = (TextView) toast.getView().findViewById(android.R.id.message);
            tv.setBackgroundColor(Color.alpha(0));
            tv.setTextColor(Color.WHITE);
            toast.show();
        }
    }
    /*Wanna use a toast somewhere? Just copy all the lines below and replace "Logout" with your message.
    Toast toast = Toast.makeText(this, "Logout", Toast.LENGTH_SHORT);
    TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
        tv.setBackgroundColor(Color.alpha(0));
        tv.setTextColor(Color.WHITE);
        toast.show();*/
}
