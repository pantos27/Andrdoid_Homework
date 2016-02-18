package com.pantos27.www.lesson16_screenshot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.logging.Handler;

public class SecondActivity extends AppCompatActivity {
    View button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button=findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        blinkButtonRun();
    }

    private void blinkButtonRun(){
        button.postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setVisibility(button.getVisibility()==View.INVISIBLE ? View.VISIBLE:View.INVISIBLE);
                blinkButtonRun();
            }
        },800);
    }
}
