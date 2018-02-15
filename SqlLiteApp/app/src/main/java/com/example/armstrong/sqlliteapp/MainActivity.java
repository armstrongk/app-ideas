package com.example.armstrong.sqlliteapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editSurname,editMarks,editId;
    Button addDatabtn;
    Button viewDatabtn;
    Button updateDatabtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper( this);
        editName =(EditText)findViewById(R.id.editText4);
        editSurname=(EditText)findViewById(R.id.editText5);
        editMarks  =(EditText)findViewById(R.id.editText6);
        editId =(EditText)findViewById(R.id.editText);
        addDatabtn =(Button)findViewById(R.id.button);
        viewDatabtn =(Button)findViewById(R.id.button2);
        updateDatabtn =(Button)findViewById(R.id.button3);

        addDAta();
        viewData();
        updateData();

    }

    public void updateData(){
        updateDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean Isupdated = myDb.updateData(editId.getText().toString(),
                        editName.getText().toString(),editSurname.getText().toString(),
                        editMarks.getText().toString());
                if(Isupdated == true)
                    Toast.makeText(MainActivity.this,"data updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"data not updated",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addDAta(){
        addDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInsterted = myDb.insertData(editName.getText().toString(),
                        editSurname.getText().toString(),
                        editMarks.getText().toString());
                if(isInsterted == true)
                    Toast.makeText(MainActivity.this,"data inserted",Toast.LENGTH_LONG).show();
                    else
                    Toast.makeText(MainActivity.this,"data not inserted",Toast.LENGTH_LONG).show();

            }
        });
    }
    public  void viewData(){
        viewDatabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount()== 0){
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id: "+ res.getString(0)+ "\n");
                    buffer.append("Name: "+ res.getString(1)+ "\n");
                    buffer.append("Surname: "+ res.getString(2)+ "\n");
                    buffer.append("Marks: "+ res.getString(3)+ "\n");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();

    }

}
