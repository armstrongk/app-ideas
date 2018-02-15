package com.example.armstrong.college;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.armstrong.college.DividerItemDecoration;
import com.example.armstrong.college.Houses;
import com.example.armstrong.college.R;
import com.example.armstrong.college.RecyclerTouchListener;
import com.example.armstrong.college.apartments.ApartmentModel;
import com.example.armstrong.college.apartments.ApartmentsAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase storeData;
    RecyclerView apartmentRecycler;
    List<ApartmentModel> apartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(fabClick);

        apartmentRecycler = (RecyclerView) findViewById(R.id.apartments);
        populateApartmentRecycler();
        apartmentRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        apartmentRecycler.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), apartmentRecycler,new RecyclerTouchListener.ClickListener(){

            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), Houses.class);
                intent.putExtra("apartment", apartList.get(position).getApartmentName());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                //To at long click.
            }
        }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateApartmentRecycler();
    }

    public void addApartment(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_apartment, null);
        final EditText apartmentName = (EditText) dialogView.findViewById(R.id.aName);
        final EditText location = (EditText) dialogView.findViewById(R.id.location);
        final EditText meterNo = (EditText) dialogView.findViewById(R.id.meterNo);

        builder.setView(dialogView);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (apartmentName.getText().length()>0 && location.getText().length()>0 && meterNo.getText().length()>0){
                    storeData = openOrCreateDatabase("powerTally",MODE_PRIVATE,null);
                    storeData.execSQL("CREATE TABLE IF NOT EXISTS apartment( name varchar(100) primary key, location varchar(100),meterNo varchar(100)); ");
                    storeData.execSQL("INSERT INTO apartment VALUES ('"+ apartmentName.getText().toString()+"','"+location.getText().toString()+"','"+meterNo.getText().toString()+"');");
                    storeData.close();
                    dialog.dismiss();

                }else {
                    if (apartmentName.getText().length() <= 0)
                        apartmentName.requestFocus();
                    else if (location.getText().length() <= 0)
                        location.requestFocus();
                    else if (meterNo.getText().length() <= 0)
                        meterNo.requestFocus();
                    else
                        dialog.dismiss();
                }
                populateApartmentRecycler();
            }
        });
        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }

    View.OnClickListener fabClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addApartment();
        }
    };

    private void populateApartmentRecycler() {
        ApartmentModel a;
        ApartmentsAdapter adapter;

        apartList = new ArrayList<>();
        adapter = new ApartmentsAdapter(getApplicationContext(), apartList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        apartmentRecycler.setLayoutManager(mLayoutManager);
        apartmentRecycler.setItemAnimator(new DefaultItemAnimator());
        apartmentRecycler.setAdapter(adapter);

        try {
            storeData = openOrCreateDatabase("powerTally",MODE_PRIVATE,null);
            Cursor all = storeData.rawQuery("SELECT * FROM apartment;",null);
            storeData.execSQL("CREATE TABLE IF NOT EXISTS house( houseNum varchar(100) primary key, apartment varchar(100), meterNo varchar(100));");
            all.moveToFirst();
            do{
                Cursor num = storeData.rawQuery("SELECT count(houseNum) from house where apartment='"+all.getString(0)+"';", null);
                num.moveToFirst();
                a = new ApartmentModel(all.getString(0), num.getInt(0), all.getString(1), all.getString(2));

                apartList.add(a);
            }while(all.moveToNext());
            storeData.close();

        }catch (Exception ex){
            System.out.println(ex);
        }
        adapter.notifyDataSetChanged();
    }

}
