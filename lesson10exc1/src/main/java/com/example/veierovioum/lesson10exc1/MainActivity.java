package com.example.veierovioum.lesson10exc1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSwitchClick(View v){
        Switch sw=(Switch) v;
        ImageView iView= (ImageView) findViewById(R.id.chosenPlane);


    }
}
