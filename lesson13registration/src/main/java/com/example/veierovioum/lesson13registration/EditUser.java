package com.example.veierovioum.lesson13registration;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class EditUser extends AppCompatActivity implements TextWatcher {
    Boolean emailValid=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSave);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et= (EditText) findViewById(R.id.editTextEmail);
                afterTextChanged(et.getText());

                if (emailValid){
                    User user= (User) getIntent().getSerializableExtra(getString(R.string.KEY_USERDETAILS));
                    user.setEmail(et.getText().toString());

                    et = (EditText) findViewById(R.id.editTextPhone);
                    user.setPhoneNumber(et.getText().toString());

                    et = (EditText) findViewById(R.id.editTextAddress);
                    user.setAddress(et.getText().toString());

                    et = (EditText) findViewById(R.id.editTextID);
                    user.setId(et.getText().toString());

                    setResult(view.getId(), getIntent());
                    finish();
                }
                    else {
                    Toast.makeText(EditUser.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    et.requestFocus();
                }
            }
        });

        if(savedInstanceState==null) {

            User user = (User) getIntent().getSerializableExtra(getString(R.string.KEY_USERDETAILS));

            if (user != null) {

                TextView tv= (TextView) findViewById(R.id.textViewNameHeader);
                tv.append(" " + user);

                EditText et = (EditText) findViewById(R.id.editTextID);
                et.setText(user.getId());

                et = (EditText) findViewById(R.id.editTextAddress);
                et.setText(user.getAddress());

                et = (EditText) findViewById(R.id.editTextPhone);
                et.setText(user.getPhoneNumber());

                et = (EditText) findViewById(R.id.editTextEmail);
                et.setText(user.getEmail());

            } else
                Toast.makeText(EditUser.this, "No user details", Toast.LENGTH_SHORT).show();
        }

        EditText et= (EditText) findViewById(R.id.editTextEmail);
        et.addTextChangedListener(this);


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (validateEmail(s.toString())){
            emailValid();
        }
        else emailInvalid();
    }

    private boolean validateEmail(String s) {
        EmailValidator ev=new EmailValidator();

        return ev.validate(s);

    }

    private void emailValid()
    {
        EditText et= (EditText) findViewById(R.id.editTextEmail);
        et.setTextColor(Color.BLACK);
        emailValid=true;
    }

    private void emailInvalid()
    {
        EditText et= (EditText) findViewById(R.id.editTextEmail);
        et.setTextColor(Color.RED);
        emailValid=false;
        et.requestFocus();
    }
}
