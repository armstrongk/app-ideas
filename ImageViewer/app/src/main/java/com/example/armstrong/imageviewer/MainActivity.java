package com.example.armstrong.imageviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static ImageView imgview;
    private static Button button_sub;
    private int current_image_index;
    int[] images = {R.mipmap.and_image,R.mipmap.armstr,R.mipmap.ic_launcher};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onviewnextimage();
    }
    public void onviewnextimage(){
        imgview = (ImageView)findViewById(R.id.imageView);
        button_sub = (Button)findViewById(R.id.button);

        button_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current_image_index ++;
                current_image_index = current_image_index % images.length;
                imgview.setImageResource(images[current_image_index]);
            }
        });
    }
}
