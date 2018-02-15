package com.example.armstrong.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button_sel;
    private CheckBox checkBox1, checkBox2, checkBox3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddOnClickListener();
    }

    public void AddOnClickListener() {
        checkBox1 = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        button_sel = (Button) findViewById(R.id.button);

        button_sel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StringBuffer result = new StringBuffer();
                        result.append("Dog : ").append(checkBox1.isChecked());
                        result.append("cat : ").append(checkBox2.isChecked());
                        result.append("cow : ").append(checkBox3.isChecked());

                        Toast.makeText(
                                MainActivity.this, result.toString(),
                                Toast.LENGTH_LONG).show();

                    }


                }
        );
    };
}
