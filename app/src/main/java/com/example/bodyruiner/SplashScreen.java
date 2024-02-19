package com.example.bodyruiner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getSupportActionBar().hide();

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(2000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                        Intent newIntent = new Intent(SplashScreen.this, WelcomeScreen.class);
                        startActivity(newIntent);
                        overridePendingTransition(R.anim.fade_in2,R.anim.fade_out2);
                        finish();
                }
            }
        };
        thread.start();
    }
}