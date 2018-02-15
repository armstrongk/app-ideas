package com.example.armstrong.setalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setAlarm(View view){
        Intent intent = new Intent(this,MyAlarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(this.getApplicationContext(),1,intent,0);
        AlarmManager am =(AlarmManager)getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+(7*1000),pi);
        Toast.makeText(this,"Set alarm after 7 minutes",Toast.LENGTH_LONG).show();
    }
}
