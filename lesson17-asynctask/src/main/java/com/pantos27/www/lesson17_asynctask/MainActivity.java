package com.pantos27.www.lesson17_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    AsyncTask<String,String,Boolean> asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void OnClick(View v){
        String[] arr={"1","2","3","4","5"};

        //check if already running
        if (v.getId()==R.id.buttonSerial && asyncTask!=null && asyncTask.getStatus()== AsyncTask.Status.RUNNING){
            if(asyncTask.cancel(true))
                Toast.makeText(MainActivity.this, "Stating Over", Toast.LENGTH_SHORT).show();
        }

        for (String url : arr) {
            asyncTask =new AsyncTask<String, String, Boolean>() {
                @Override
                protected Boolean doInBackground(String... params) {
                    for (String url : params) {

                        try {
                            Thread.sleep(360);
                            if (this.isCancelled())
                                return false;
                            this.publishProgress(url);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                }

                @Override
                protected void onProgressUpdate(String... values) {
                    Toast.makeText(MainActivity.this,values[0]+ " File(s) downloaded", Toast.LENGTH_SHORT).show();
                }


            };

            if (v.getId()==R.id.buttonParallel){
                asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url );
            }
            else{
                asyncTask.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR,arr );
                return;
            }
        }


    }




}
