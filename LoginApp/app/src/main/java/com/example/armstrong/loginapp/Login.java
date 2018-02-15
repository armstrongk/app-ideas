package com.example.armstrong.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private static EditText name_text;
    private static EditText password;
    private static TextView login;
    private static TextView attempts;
    private static TextView user_name;
    private static Button login_but;
    int attempt_counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Onclicklogin();
    }
    public void Onclicklogin(){
       name_text = (EditText)findViewById(R.id.editText_name);
        password = (EditText)findViewById(R.id.editText_password);
        attempts =(TextView) findViewById(R.id.textView_attempts);
        login_but =(Button)findViewById(R.id.button_login);

        attempts.setText(Integer.toString(attempt_counter));
        login_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( name_text.getText().toString().equals("user") &&
                        password.getText().toString().equals("password")

                        ){
                    Toast.makeText(Login.this,"user and password is correct",
                            Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent("com.example.armstrong.loginapp.user");
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this,"user and password is not correct",
                            Toast.LENGTH_SHORT ).show();
                    attempt_counter--;
                    attempts.setText(Integer.toString(attempt_counter));

                    if(attempt_counter == 0){
                        login_but.setEnabled(false);
                    }

                }
            };


        });

    }
}