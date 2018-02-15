package com.example.armstrong.autocompleteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView acTextView;
    String[]countries={
            "albania",
            "afgastan",
            "algeria",
            "angola",
            "argentina",
            "america"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        acTextView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.select_dialog_item,countries);
        acTextView.setThreshold(1);
        acTextView.setAdapter(adapter);
    }
}
