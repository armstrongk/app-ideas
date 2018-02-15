package com.example.armstrong.latest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {
    EditText first,second,student_id,course,year,email,age,sex,residence,password;
    String str_first,str_second,str_student_id,str_course,str_year,str_email,str_age,str_sex,str_residence,str_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        first = (EditText)findViewById(R.id.editText2);
        second =(EditText)findViewById(R.id.editText3);
        student_id=(EditText)findViewById(R.id.editText4);
        course=(EditText)findViewById(R.id.editText5);
        year=(EditText)findViewById(R.id.editText6);
        email=(EditText)findViewById(R.id.editText7);
        age=(EditText)findViewById(R.id.editText8);
        sex=(EditText)findViewById(R.id.editText9);
        residence=(EditText)findViewById(R.id.editText10);
        password=(EditText)findViewById(R.id.editText11);
    }
    public void onReg(View view){
        String str_first= first.getText().toString();
        String str_second=second.getText().toString();
        String str_student_id=student_id.getText().toString();
        String str_course=course.getText().toString();
        String str_year= year.getText().toString();
        String str_email=email.getText().toString();
        String str_age= age.getText().toString();
        String str_sex=sex.getText().toString();
        String str_residence= residence.getText().toString();
        String str_password= password.getText().toString();
        String type ="register";
        BackgroundWolker backgroundWolker = new BackgroundWolker(this);
        backgroundWolker.execute(type,str_first,str_second,str_student_id,str_course,str_year,str_email,str_age,str_sex,str_residence,str_password);
    }
}

