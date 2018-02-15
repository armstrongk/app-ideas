package com.example.armstrong.myapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String MY_TAG = "the_custom_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(MY_TAG,"onCreate");
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(MY_TAG,"onStart");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(MY_TAG,"onResume");

    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i(MY_TAG,"onPause");

    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(MY_TAG,"onStop");

    }


}
