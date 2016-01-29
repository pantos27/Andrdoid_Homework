package com.example.veierovioum.lesson13registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class UserDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabDelete);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                setResult(R.integer.RESULT_DELETE,getIntent());
//                finish();
//            }
//        });
//
//        fab = (FloatingActionButton) findViewById(R.id.fabEdit);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setResult(R.integer.RESULT_EDIT,getIntent());
//                finish();
//            }
//        });

        if(savedInstanceState==null){

            User user= (User) getIntent().getSerializableExtra(getString(R.string.KEY_USERDETAILS));

            if (user!=null) {

                TextView tv = (TextView) findViewById(R.id.textViewFullName);
                tv.setText(user.toString());

                tv = (TextView) findViewById(R.id.textViewID);
                tv.setText(user.getId());

                tv = (TextView) findViewById(R.id.textViewAddress);
                tv.setText(user.getAddress());

                tv = (TextView) findViewById(R.id.textViewEmail);
                tv.setText(user.getEmail());

                tv = (TextView) findViewById(R.id.textViewPhone);
                tv.setText(user.getPhoneNumber());
            }
            else
                Toast.makeText(UserDetails.this, "No user details", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClick(View v){
        setResult(v.getId(),getIntent());
        finish();
    }

}
