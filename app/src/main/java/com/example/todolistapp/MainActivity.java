package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.todolistapp.Activity.TagActivity;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mHandler = new Handler();

        startTime();




    }



    private void startTime() {
        mHandler.removeCallbacks(mUpdateTimeTask);
        mHandler.postDelayed(mUpdateTimeTask, 3000);
    }

    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {

            startActivity(new Intent(MainActivity.this, TagActivity.class));

            finish();
        }
    };
}
