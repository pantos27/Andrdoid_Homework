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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

public class NewUser extends AppCompatActivity implements TextWatcher {
    Boolean emailValid=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabNewUser);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //vaildte emial field
                EditText et= (EditText) findViewById(R.id.editTextEmail);
                afterTextChanged(et.getText());

                if (emailValid){

                    String email = et.getText().toString();
                    String empty="";
                    //validte the rest of teh fields
                    et = (EditText) findViewById(R.id.editTextPhone);
                    String phone=et.getText().toString();
                    if (phone.equals(empty)){
                        notifyEmpty(et);
                        return;
                    }

                    et = (EditText) findViewById(R.id.editTextAddress);
                    String address = et.getText().toString();
                    if (address.equals(empty)){
                        notifyEmpty(et);
                        return;
                    }

                    et = (EditText) findViewById(R.id.editTextID);
                    String ID=et.getText().toString();
                    if (ID.equals(empty)){
                        notifyEmpty(et);
                        return;
                    }
                    et = (EditText) findViewById(R.id.editTextFName);
                    String fName=et.getText().toString();
                    if (fName.equals(empty)){
                        notifyEmpty(et);
                        return;
                    }
                    et = (EditText) findViewById(R.id.editTextLName);
                    String lName=et.getText().toString();
                    if (lName.equals(empty)){
                        notifyEmpty(et);
                        return;
                    }
                    et = (EditText) findViewById(R.id.editTextPassword);
                    String password=et.getText().toString();
                    if (password.equals(empty)){
                        notifyEmpty(et);
                        return;
                    }
                    //create new user and send to main activity
                    User user=new User(fName,lName,email,phone,address,ID,password);
                    Intent intent=new Intent();
                    intent.putExtra(getString(R.string.KEY_USERDETAILS),user);
                    setResult(view.getId(), intent);
                    finish();
                }
                else {
                    Toast.makeText(NewUser.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    et.requestFocus();
                }
            }
        });

        EditText et= (EditText) findViewById(R.id.editTextEmail);
        et.addTextChangedListener(this);


    }

    private void notifyEmpty(EditText et) {
        et.requestFocus();
        Toast.makeText(NewUser.this, "Please complete all fields", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d("EditText",s.toString());

        if (validateEmail(s.toString())){
            emailValid();
        }
        else emailInvalid();
    }

    private Boolean validateEmail(String str){
        EmailValidator ev=new EmailValidator();
        return ev.validate(str);
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
