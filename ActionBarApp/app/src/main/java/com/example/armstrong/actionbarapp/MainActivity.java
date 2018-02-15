package com.example.armstrong.actionbarapp;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab= getActionBar();
        ab.setLogo(R.mipmap.ic_launcher);
        ab.setDisplayUseLogoEnabled(true);
        ab.setDisplayShowHomeEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater()
        MenuInflater.inflate(R.menu.main_activity_menu,menu)
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.info_id;
                Toast.makeText(getApplicationContext(),"info icon is selected",Toast.LENGTH_SHORT).show();
                case R.id.settings_id;
                    Toast.makeText(getApplicationContext(),"settings icon is selected",Toast.LENGTH_SHORT).show();
                 default:
                     return super.onOptionsItemSelected(item);


        }


    }
}
