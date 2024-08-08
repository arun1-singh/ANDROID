package com.example.androidapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidapp.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(), Register.class));

            }
        },3500);

    }
}