package com.pantos27.www.lesson13_custom_view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MultiPurposeButton mpb= (MultiPurposeButton) findViewById(R.id.mpb);

        mpb.addOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiPurposeButton _mbp= (MultiPurposeButton) v;
                _mbp.setBackgroundColor(Color.CYAN);
            }
        });

        mpb.addOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiPurposeButton _mbp= (MultiPurposeButton) v;
                _mbp.setText("changed");
            }
        });

        TextView tv;
       // tv.setText("");
    }
}
