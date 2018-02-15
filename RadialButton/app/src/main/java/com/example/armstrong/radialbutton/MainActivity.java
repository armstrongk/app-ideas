package com.example.armstrong.radialbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static RadioGroup radioGroup;
    private static Button button1;
    private  static RadioButton radioButton_b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onClickButton();
    }
    public void onClickButton(){
        radioGroup = (RadioGroup)findViewById(R.id.rg);
        button1 = (Button)findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_id = radioGroup.getCheckedRadioButtonId();
                radioButton_b = (RadioButton)findViewById(selected_id);


                Toast.makeText(MainActivity.this , radioButton_b.getText(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}
