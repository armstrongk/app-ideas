package com.example.armstrong.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewsignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth =FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!= null){
            //profile here
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }
        progressDialog = new ProgressDialog(this);


        buttonRegister =(Button)findViewById(R.id.button);
        editTextEmail =(EditText)findViewById(R.id.editText);
        editTextPassword=(EditText)findViewById(R.id.editText2);
        textViewsignin=(TextView)findViewById(R.id.textView2);
        buttonRegister.setOnClickListener(this);
        textViewsignin.setOnClickListener(this);
    }
    private void registerUser(){
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

        progressDialog.setMessage("Registering.............");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            //profile here
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));

                        }
                        else{
                            Toast.makeText(MainActivity.this,"could not register pliz try again",Toast.LENGTH_SHORT).show();
                        }
                    }



                });
    }
    @Override
    public void onClick(View view) {
        if (view == buttonRegister){
            registerUser();
        }

        if(view==textViewsignin){
            //will open login activity here
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
