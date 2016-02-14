package com.pantos27.www.lesson16_screenshot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv= (TextView) findViewById(R.id.txtView);


    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
