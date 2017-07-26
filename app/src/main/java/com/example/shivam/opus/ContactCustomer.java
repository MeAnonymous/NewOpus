package com.example.shivam.opus;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shivam.opus.dbutil.OCons;
import com.example.shivam.opus.dbutil.OMng;

public class ContactCustomer extends AppCompatActivity {
    EditText e1, e2;
    SQLiteDatabase sb;
    OMng o;
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
        getSupportActionBar().setCustomView(R.layout.custom_ab);

        setContentView(R.layout.activity_contact_customer);
        e1 = (EditText) findViewById(R.id.txtmemid);
        e2 = (EditText) findViewById(R.id.txtmessage);
        o = new OMng(this);
        sb = o.open();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void done(View v) {
        Intent i = new Intent(this, Main.class);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(this,
                android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
        startActivity(i, bundle);
    }

    public void call(View v) {
        String num = null;
        String mid = e1.getText().toString();
        String mess = e2.getText().toString();
        Cursor c = sb.rawQuery("SELECT MemberPhone FROM Member WHERE MemberId=\""+mid+"\"", null);
        if(c != null && c.moveToFirst()){
            num = c.getString(c.getColumnIndex(OCons.MPhN));
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + num));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(callIntent);
        }
        else {
            toast =  Toast.makeText(this, "memID not valid", Toast.LENGTH_SHORT);
            tv = (TextView) toast.getView().findViewById(android.R.id.message);
            tv.setBackgroundColor(Color.alpha(0));
            tv.setTextColor(Color.WHITE);
            toast.show();
        }
    }
    public void message(View v) {
        String num = null;
        String mid = e1.getText().toString();
        String mess = e2.getText().toString();
        if(mess.isEmpty() || mess.length() == 0 || mess.equals("") || mess == null){
            toast = Toast.makeText(this, "Enter message", Toast.LENGTH_SHORT);
            tv = (TextView) toast.getView().findViewById(android.R.id.message);
            tv.setBackgroundColor(Color.alpha(0));
            tv.setTextColor(Color.WHITE);
            toast.show();
        }else {
            Cursor c = sb.rawQuery("SELECT MemberPhone FROM Member WHERE MemberId=\"" + mid + "\"", null);
            if (c != null && c.moveToFirst()) {
                num = c.getString(c.getColumnIndex(OCons.MPhN));
                SmsManager manager = SmsManager.getDefault();
                manager.sendTextMessage(num, null, mess, null, null);
                toast = Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT);
                tv = (TextView) toast.getView().findViewById(android.R.id.message);
                tv.setBackgroundColor(Color.alpha(0));
                tv.setTextColor(Color.WHITE);
                toast.show();
            } else {
                toast = Toast.makeText(this, "memID not valid", Toast.LENGTH_SHORT);
                tv = (TextView) toast.getView().findViewById(android.R.id.message);
                tv.setBackgroundColor(Color.alpha(0));
                tv.setTextColor(Color.WHITE);
                toast.show();
            }
        }
    }

    public void mail(View v) {
        String mid = e1.getText().toString();
        String mess = e2.getText().toString();
        String mail = null;
        if(mess.isEmpty() || mess.length() == 0 || mess.equals("") || mess == null){
            toast = Toast.makeText(this, "Enter message", Toast.LENGTH_SHORT);
            tv = (TextView) toast.getView().findViewById(android.R.id.message);
            tv.setBackgroundColor(Color.alpha(0));
            tv.setTextColor(Color.WHITE);
            toast.show();
        }else {
            Cursor c = sb.rawQuery("SELECT MemberEmail FROM Member WHERE MemberId=\"" + mid + "\"", null);
            if (c != null && c.moveToFirst()) {
                mail = c.getString(c.getColumnIndex(OCons.MEmail));
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setData(Uri.parse("mailto:"));
                String[] to = {mail};
                i.putExtra(Intent.EXTRA_EMAIL, to);
                i.putExtra(Intent.EXTRA_SUBJECT, "A message from keeper of books - Opus");
                i.putExtra(Intent.EXTRA_TEXT, mess);
                i.setType("message/rfc822");
                Intent chooser = Intent.createChooser(i, "Send Email");
                startActivity(chooser);
            } else {
                toast = Toast.makeText(this, "memID not valid", Toast.LENGTH_SHORT);
                tv = (TextView) toast.getView().findViewById(android.R.id.message);
                tv.setBackgroundColor(Color.alpha(0));
                tv.setTextColor(Color.WHITE);
                toast.show();
            }
        }
    }
    public void logout(View v){
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle("Do you want to logout?");
        ad.setMessage("");
        ad.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(ContactCustomer.this, Log.class);
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(ContactCustomer.this,
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
                startActivity(i, bundle);
            }
        });
        ad.setNegativeButton("No", null);
        AlertDialog adb = ad.create();
        adb.show();
    }
}
