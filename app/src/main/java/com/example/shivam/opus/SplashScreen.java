package com.example.shivam.opus;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //To hide notification bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        /* New Handler to start the Main activity
           and close this Splash-Screen after specified milliseconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //Intent to start Login 1.0 or 2.0 based on Shared Preferences
                Intent i = new Intent(SplashScreen.this, MainActivity.class); //MainActivity is for creating tables, nothing more.
                startActivity(i);
                SplashScreen.this.finish();
            }
        }, 2500);

    }
}
