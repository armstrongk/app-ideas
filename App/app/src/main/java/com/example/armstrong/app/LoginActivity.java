package com.example.armstrong.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewsignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!= null){
            //profile here
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }


        progressDialog = new ProgressDialog(this);
        editTextEmail =(EditText)findViewById(R.id.editText);
        editTextPassword =(EditText)findViewById(R.id.editText2);
        buttonSignIn =(Button) findViewById(R.id.buttonsignin);
        textViewsignup =(TextView)findViewById(R.id.textViewsignup);

        buttonSignIn.setOnClickListener(this);
        textViewsignup.setOnClickListener(this);
    }

    private void userLogin(){
        String email= editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this,"please enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering please wait.............");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            //start profile activity
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v ==buttonSignIn ){
            userLogin();
        }
        if(v == textViewsignup){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
