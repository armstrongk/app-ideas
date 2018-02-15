package com.example.armstrong.college;

/**
 * Created by armstrong on 7/1/2017.
 */
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

import com.example.armstrong.college.houses.HouseAdapter;
import com.example.armstrong.college.houses.HouseModel;
import com.example.armstrong.college.transactions.TransactionAdapter;
import com.example.armstrong.college.transactions.TransactionModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HouseTransaction extends AppCompatActivity {

    String house, meterNum;
    float previous;
    float totalConsumption, totalPayments, balance;
    TextView hse, mtr, curRead;
    TextView total, bal;
    SQLiteDatabase storeData;
    TenantModel tnt;
    List<TransactionModel> transactionList;
    RecyclerView transactionRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        hse = (TextView) findViewById(R.id.hse);
        mtr = (TextView) findViewById(R.id.mtr);
        curRead = (TextView) findViewById(R.id.curRead);
        total = (TextView) findViewById(R.id.total);
        bal = (TextView) findViewById(R.id.balance);

        house = getIntent().getCharSequenceExtra("house").toString();
        meterNum = getIntent().getCharSequenceExtra("mtr").toString();

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        initElements();

        transactionRecycler = (RecyclerView) findViewById(R.id.transactions);
        populateTransactionsRecycler();
        transactionRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inside_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.newReading) {
            if (getUnitCost()==0)
                setUnitCost();
            else
                addNewReading();
            return true;
        }else if (id == R.id.addPayment){
            addNewPayment();
            return true;
        }else if (id == R.id.unitCost){
            if (getUnitCost()==0)
                setUnitCost();
            else
                updateUnitCost();
            return true;
        }else if (id == R.id.tenant){
            if (getTenant() == null)
                setTenant();
            else
                updateTenant();
            return true;
        }else if (id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void addNewReading(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_new_reading, null);
        final TextView prev = (TextView) dialogView.findViewById(R.id.prev);
        final EditText current = (EditText) dialogView.findViewById(R.id.current);

        prev.setText(toString().valueOf(previous)+" Units");
        prev.setTextColor(getResources().getColor(R.color.colorAccent));

        builder.setView(dialogView);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (current.getText().length()>0){
                    String p, c, cost;
                    p = toString().valueOf(previous);
                    c = current.getText().toString();
                    cost = toString().valueOf((Float.parseFloat(c) - previous)*getUnitCost());

                    storeData = openOrCreateDatabase("powerTally",MODE_PRIVATE,null);
                    storeData.execSQL("CREATE TABLE IF NOT EXISTS meterReading(eid int auto_increment primary key, meterNum varchar(100), oldReading float, newReading float, cost float);");
                    storeData.execSQL("INSERT INTO meterReading(meterNum,oldReading,newReading,cost) VALUES ('"+ meterNum+"','"+p+"','"+c+"','"+cost+"');");
                    storeData.close();
                    initElements();
                    dialog.dismiss();

                }else {
                    current.requestFocus();
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

    public void addNewPayment(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_new_payment, null);
        final TextView cBalance = (TextView) dialogView.findViewById(R.id.bal);
        final EditText newPayment = (EditText) dialogView.findViewById(R.id.payment);

        if (balance > 0){
            cBalance.setText(toString().valueOf(balance)+"/=");
            cBalance.setTextColor(getResources().getColor(R.color.notNill));
        }else {
            cBalance.setText(toString().valueOf(balance)+"/=");
            cBalance.setTextColor(getResources().getColor(R.color.nill));
        }

        builder.setView(dialogView);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (newPayment.getText().length()>0){
                    String paid = newPayment.getText().toString();
                    String date = new Date_Manager().dateToString(new Date());
                    storeData = openOrCreateDatabase("powerTally",MODE_PRIVATE,null);
                    storeData.execSQL("CREATE TABLE IF NOT EXISTS meterPayments(pid int auto_increment primary key, meterNum varchar(100),ddate date, amount float);");
                    storeData.execSQL("INSERT INTO meterPayments(meterNum,ddate,amount) VALUES ('"+ meterNum+"','"+date+"','"+paid+"');");
                    storeData.close();
                    initElements();
                    populateTransactionsRecycler();
                    dialog.dismiss();

                }else {
                    newPayment.requestFocus();
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

    private void setBalance(){
        storeData = openOrCreateDatabase("powerTally", MODE_PRIVATE, null);
        try {
            storeData.execSQL("CREATE TABLE IF NOT EXISTS meterPayments(pid int auto_increment primary key, meterNum varchar(100),ddate date, amount float);");
            Cursor payments = storeData.rawQuery("SELECT sum(amount) FROM meterPayments where meterNum='"+meterNum+"';", null);
            payments.moveToFirst();
            totalPayments = payments.getFloat(0);

        }catch (Exception ex){
            totalPayments = 0;
        }

        try {
            storeData.execSQL("CREATE TABLE IF NOT EXISTS meterReading(eid int auto_increment primary key, meterNum varchar(100), oldReading float, newReading float, cost float);");
            Cursor consumed = storeData.rawQuery("SELECT sum(cost) FROM meterReading where meterNum='"+meterNum+"';", null);
            consumed.moveToFirst();
            totalConsumption = consumed.getFloat(0);

        }catch (Exception ex){
            totalConsumption = 0;
        }
        balance = totalConsumption - totalPayments;
        System.out.println("Rem" + balance);

        try {
            storeData.execSQL("CREATE TABLE IF NOT EXISTS meterReading(eid int auto_increment primary key, meterNum varchar(100), oldReading float, newReading float, cost float);");
            Cursor all = storeData.rawQuery("SELECT max(newReading) FROM meterReading where meterNum='"+meterNum+"';", null);
            all.moveToFirst();
            previous = all.getFloat(0);

        }catch (Exception ex){
            previous = 0;
        }
        storeData.close();
    }

    private void initElements(){
        setBalance();

        hse.setText(house);
        mtr.setText(meterNum);
        curRead.setText(toString().valueOf(previous)+ " Units");
        total.setText(toString().valueOf(totalPayments)+"/=");
        tnt = getTenant();

        if (tnt != null){
            getSupportActionBar().setTitle(tnt.getName());
        }
        if (balance > 0){
            bal.setText(toString().valueOf(balance)+"/=");
            bal.setTextColor(getResources().getColor(R.color.notNill));
        }else {
            bal.setText(toString().valueOf(balance)+"/=");
            bal.setTextColor(getResources().getColor(R.color.nill));
        }
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

    private void populateTransactionsRecycler() {
        TransactionModel a;
        TransactionAdapter adapter;

        transactionList = new ArrayList<>();
        adapter = new TransactionAdapter(getApplicationContext(), transactionList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        transactionRecycler.setLayoutManager(mLayoutManager);
        transactionRecycler.setItemAnimator(new DefaultItemAnimator());
        transactionRecycler.setAdapter(adapter);

        try {
            storeData = openOrCreateDatabase("powerTally",MODE_PRIVATE,null);
            storeData.execSQL("CREATE TABLE IF NOT EXISTS meterPayments(pid int auto_increment primary key, meterNum varchar(100),ddate date, amount float);");
            Cursor all = storeData.rawQuery("SELECT ddate,amount FROM meterPayments where meterNum='"+meterNum+"';", null);
            all.moveToFirst();
            do{
                a = new TransactionModel(all.getString(0), all.getString(1));
                transactionList.add(a);
            }while(all.moveToNext());
            storeData.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
        adapter.notifyDataSetChanged();
    }

    private TenantModel getTenant(){
        storeData = openOrCreateDatabase("powerTally", MODE_PRIVATE, null);
        try {
            storeData.execSQL("CREATE TABLE IF NOT EXISTS tenant(tId int auto_increment primary key, houseNum varchar(100), name varchar(100), contact varchar(100));");
            Cursor tenant = storeData.rawQuery("SELECT name, contact FROM tenant where houseNum='"+house+"';", null);
            tenant.moveToFirst();
            storeData.close();
            return new TenantModel(tenant.getString(0), tenant.getString(1));
        }catch (Exception ex){
            storeData.close();
            return null;
        }
    }

    public void setTenant(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_tenant, null);
        final EditText tName = (EditText) dialogView.findViewById(R.id.tName);
        final EditText tContact = (EditText) dialogView.findViewById(R.id.tContact);

        builder.setView(dialogView);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (tName.getText().length()>0 && tContact.getText().length()>0){
                    String name = tName.getText().toString();
                    String contact = tContact.getText().toString();
                    storeData = openOrCreateDatabase("powerTally",MODE_PRIVATE,null);
                    storeData.execSQL("CREATE TABLE IF NOT EXISTS tenant(tId int auto_increment primary key, houseNum varchar(100), name varchar(100), contact varchar(100));");
                    storeData.execSQL("INSERT INTO tenant(houseNum, name, contact) VALUES ('"+house+"','"+name+"','"+contact+"');");
                    storeData.close();
                    initElements();
                    dialog.dismiss();
                }else {
                    if (tName.getText().length() <= 0)
                        tName.requestFocus();
                    else
                        tContact.requestFocus();
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

    public void updateTenant(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_tenant, null);
        final EditText tName = (EditText) dialogView.findViewById(R.id.tName);
        final EditText tContact = (EditText) dialogView.findViewById(R.id.tContact);

        TenantModel tenant = getTenant();
        if (tenant != null){
            tName.setText(tenant.getName());
            tContact.setText(tenant.getContact());
        }

        builder.setView(dialogView);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (tName.getText().length()>0 && tContact.getText().length()>0){
                    String name = tName.getText().toString();
                    String contact = tContact.getText().toString();
                    storeData = openOrCreateDatabase("powerTally",MODE_PRIVATE,null);
                    storeData.execSQL("CREATE TABLE IF NOT EXISTS tenant(tId int auto_increment primary key, houseNum varchar(100), name varchar(100), contact varchar(100));");
                    storeData.execSQL("INSERT INTO tenant(houseNum, name, contact) VALUES ('"+house+"','"+name+"','"+contact+"');");
                    storeData.close();
                    initElements();
                    dialog.dismiss();
                }else {
                    if (tName.getText().length() <= 0)
                        tName.requestFocus();
                    else
                        tContact.requestFocus();
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

}
