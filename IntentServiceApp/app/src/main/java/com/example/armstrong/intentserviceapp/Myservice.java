package com.example.armstrong.intentserviceapp;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by armstrong on 1/16/2017.
 */
public class Myservice extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public Myservice() {
        super("My_Intent_Thread");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(Myservice.this,"service started",Toast.LENGTH_LONG).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(Myservice.this,"service stopped",Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        synchronized (this){
            try {
                wait(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
