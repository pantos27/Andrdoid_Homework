package com.pantos27.www.lesson16_screenshot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    boolean firstRun=true;
    int i=0;
    String[] arr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= (TextView) findViewById(R.id.txtView);

        if (savedInstanceState != null) {
            firstRun=false;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (firstRun){
            arr=getResources().getStringArray(R.array.welcomeMessage);
            tv.setText("");
            mRun();
        }

    }

    private void mRun(){

        tv.postDelayed(splashhRunnable, 1000);
    }
    private Runnable splashhRunnable=new Runnable() {
        @Override
        public void run() {
            Log.d("TAGGG", "run: " + i + " " + arr[i]);
            tv.setText(tv.getText() + "\n" +arr[i]);
            if (i++<arr.length-1){
                mRun();
            }
            else
                tv.postDelayed(nextActRunnable,4000);


        }
    };

    private Runnable nextActRunnable=new Runnable() {
        @Override
        public void run() {
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        }
    };

}
