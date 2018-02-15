package com.example.armstrong.apptwo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void OpenActivity1fromapp1(View view){
        Intent intent= new Intent("com.example.armstrong.learningintentappone.ActivityOneAppOne");
        startActivity(intent);
    }
}
