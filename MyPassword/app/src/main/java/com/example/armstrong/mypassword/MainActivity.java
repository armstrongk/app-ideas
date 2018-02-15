package com.example.armstrong.mypassword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private Button button_sbm;
    private EditText pass_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListnerOnButton();
    }
    public void addListnerOnButton(){
        pass_word = (EditText)findViewById(R.id.editText);
        button_sbm = (Button)findViewById(R.id.button);
        button_sbm.setOnClickListener(
                new View.OnClickListener(){
                    @Override
            public void onClick(View v){
                Toast.makeText(
                        MainActivity.this, pass_word.getText(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
