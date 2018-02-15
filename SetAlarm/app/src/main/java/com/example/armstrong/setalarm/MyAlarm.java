package com.example.armstrong.setalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by armstrong on 1/17/2017.
 */
public class MyAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"alarm is running after 7 minutes",Toast.LENGTH_LONG).show();
    }
}
