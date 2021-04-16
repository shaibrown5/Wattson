package com.example.wattson;

import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        /** Create Thread that will sleep for 5 seconds**/
        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);

                    // After 5 seconds redirect to another intent
                    Intent intent = new Intent(getApplicationContext(),HomePage.class);
                    startActivity(intent);

                    //Remove activity
                    finish();
                } catch (Exception e) {
                    Log.e("[SPLASH THREAD]", e.toString());
                }
            }
        };
        // start thread
        background.start();

    }
}