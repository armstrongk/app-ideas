package com.example.armstrong.clocks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.DigitalClock;

public class MainActivity extends AppCompatActivity {
    private static Button button_sub;
    private static DigitalClock digitalClock;
    private static AnalogClock analogClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Onclicklistener();
    }

    public void Onclicklistener(){
        digitalClock = (DigitalClock)findViewById(R.id.digitalClock);
        analogClock = (AnalogClock)findViewById(R.id.analogClock);
        button_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(digitalClock.getWindowVisibility() ==  digitalClock.GONE ){
                    digitalClock.setVisibility(digitalClock.VISIBLE);
                } else {
                    digitalClock.setVisibility( digitalClock.GONE);
                    digitalClock.setVisibility(digitalClock.VISIBLE);
                }
            }
        });
    }
}
