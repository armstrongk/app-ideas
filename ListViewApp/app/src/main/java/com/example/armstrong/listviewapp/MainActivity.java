package com.example.armstrong.listviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static ListView list_view;
    private static  String Names[]= new String[] {"alex","peter","armstrong","kaahwa", "kellyz"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Listview();
    }
    public void Listview(){
        list_view = (ListView)findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.name_list,Names);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value=(String)list_view.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,"position : "+ position + "value : " + value ,
                        Toast.LENGTH_LONG).show();

            }
        });
    }

}
