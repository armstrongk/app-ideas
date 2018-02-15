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
import android.widget.TextView;

import com.example.armstrong.college.apartments.ApartmentModel;
import com.example.armstrong.college.apartments.ApartmentsAdapter;
import com.example.armstrong.college.houses.HouseAdapter;
import com.example.armstrong.college.houses.HouseModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Houses extends AppCompatActivity {

    String apartment;
    RecyclerView houseRecycler;
    List<HouseModel> houseList;
    SQLiteDatabase storeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(fabClick);

        apartment = getIntent().getCharSequenceExtra("apartment").toString();
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(apartment);
        }

        houseRecycler = (RecyclerView) findViewById(R.id.houses);
        populateHouseRecycler();
        houseRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        houseRecycler.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), houseRecycler, new RecyclerTouchListener.ClickListener(){

            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), HouseTransaction.class);
                intent.putExtra("house", houseList.get(position).getHouseNum());
                intent.putExtra("mtr", houseList.get(position).getMeterNum());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                //To at long click.
            }
        }));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.unitCost) {
            if (getUnitCost()==0)
                setUnitCost();
            else
                updateUnitCost();
            return true;
        }else if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener fabClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addHouse();
        }
    };

    public void addHouse(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_house, null);
        final EditText house = (EditText) dialogView.findViewById(R.id.roomName);
        final EditText meterNo = (EditText) dialogView.findViewById(R.id.meter);

        builder.setView(dialogView);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (house.getText().length()>0 && meterNo.getText().length()>0){
                    storeData = openOrCreateDatabase("powerTally",MODE_PRIVATE,null);
                    storeData.execSQL("CREATE TABLE IF NOT EXISTS house( houseNum varchar(100) primary key, apartment varchar(100), meterNo varchar(100));");
                    storeData.execSQL("INSERT INTO house VALUES ('"+ house.getText().toString()+"','"+apartment+"','"+meterNo.getText().toString()+"');");
                    storeData.close();
                    dialog.dismiss();

                }else {
                    if (house.getText().length() <= 0)
                        house.requestFocus();
                    else if (meterNo.getText().length() <= 0)
                        meterNo.requestFocus();
                    else
                        dialog.dismiss();
                }
                populateHouseRecycler();
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

    public void setUnitCost(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.set_unit_cost, null);
        final EditText unitCost = (EditText) dialogView.findViewById(R.id.uCost);

        builder.setView(dialogView);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (unitCost.getText().length()>0){
                    String paid = unitCost.getText().toString();
                    storeData = openOrCreateDatabase("powerTally",MODE_PRIVATE,null);
                    storeData.execSQL("CREATE TABLE IF NOT EXISTS unitCost(costId int auto_increment primary key, amount float);");
                    storeData.execSQL("INSERT INTO unitCost(amount) VALUES ('"+paid+"');");
                    storeData.close();
                    dialog.dismiss();

                }else {
                    unitCost.requestFocus();
                }
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

    public void updateUnitCost(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.set_unit_cost, null);
        final EditText unitCost = (EditText) dialogView.findViewById(R.id.uCost);

        unitCost.setText(toString().valueOf(getUnitCost()));
        builder.setView(dialogView);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (unitCost.getText().length()>0){
                    String paid = unitCost.getText().toString();
                    storeData = openOrCreateDatabase("powerTally",MODE_PRIVATE,null);
                    storeData.execSQL("CREATE TABLE IF NOT EXISTS unitCost(costId int auto_increment primary key, amount float);");
                    storeData.execSQL("UPDATE unitCost SET amount='"+paid+"';");
                    storeData.close();
                    dialog.dismiss();

                }else {
                    unitCost.requestFocus();
                }
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

    private float getUnitCost(){
        storeData = openOrCreateDatabase("powerTally", MODE_PRIVATE, null);
        try {
            storeData.execSQL("CREATE TABLE IF NOT EXISTS unitCost(costId int auto_increment primary key, amount float);");
            Cursor uCost = storeData.rawQuery("SELECT amount FROM unitCost limit 1;", null);
            uCost.moveToFirst();
            storeData.close();
            return uCost.getFloat(0);
        }catch (Exception ex){
            storeData.close();
            return 0;
        }
    }

    private void populateHouseRecycler() {
        HouseModel a;
        HouseAdapter adapter;

        houseList = new ArrayList<>();
        adapter = new HouseAdapter(getApplicationContext(), houseList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        houseRecycler.setLayoutManager(mLayoutManager);
        houseRecycler.setItemAnimator(new DefaultItemAnimator());
        houseRecycler.setAdapter(adapter);

        try {
            storeData = openOrCreateDatabase("powerTally",MODE_PRIVATE,null);
            Cursor all = storeData.rawQuery("SELECT * FROM house where apartment='"+apartment+"';",null);
            all.moveToFirst();
            do{
                a = new HouseModel(all.getString(0), all.getString(2), true);
                houseList.add(a);
            }while(all.moveToNext());
            storeData.close();

        }catch (Exception ex){
            System.out.println(ex);
        }
        adapter.notifyDataSetChanged();
    }
}
