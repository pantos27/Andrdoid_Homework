package com.pantos27.www.lesson13_custom_view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(ExpandView.TAG, "onCreate: before mpb");
        final MultiPurposeButton mpb= (MultiPurposeButton) findViewById(R.id.mpb);

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
                MultiPurposeButton _mbp = (MultiPurposeButton) v;
                _mbp.setText("changed");
            }
        });
        Log.d(ExpandView.TAG, "onCreate: before expandview");

        ExpandView ev= (ExpandView) findViewById(R.id.expandView);

         Log.d(ExpandView.TAG, "onCreate: before expand state listener");

        ev.setStateChangedListener(new StateChangedListener() {
            @Override
            public void onStateChanged(ExpandView v) {
                if (v.isExpanded()) mpb.setText("Expanded");
                else mpb.setText("Collapsed");
            }
        });

    }
}
