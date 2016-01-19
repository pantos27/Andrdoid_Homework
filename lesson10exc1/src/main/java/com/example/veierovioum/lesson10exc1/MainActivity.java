package com.example.veierovioum.lesson10exc1;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    View chosenPlane;
    ImageView switchImage;
    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw= (Switch) findViewById(R.id.switch1);
        switchImage = (ImageView) findViewById(R.id.chosenPlane);

        sw.setOnCheckedChangeListener(this);
        setSwitchImage(sw.isChecked());




    }

    public void onArrowClick(View v){
        EditText eTxt= (EditText) findViewById(R.id.editText);

        int value=Integer.valueOf(eTxt.getText().toString());

        switch (v.getTag().toString()){
            case "down":
                changePostion(0,value);
                break;
            case "up":
                changePostion(0,0-value);
                break;
            case "left":
                changePostion(0-value,0);

                break;
            case "right":
                changePostion(value,0);

                break;
        }

    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        setSwitchImage(isChecked);
    }

    /**
     * change the UI according to the chosen switch mode
     * @param bool switch state
     */
    private void setSwitchImage (Boolean bool){
        if (bool){
            switchImage.setImageResource(R.mipmap.plane2);
            chosenPlane=findViewById(R.id.plane2);


        }
        else {
            switchImage.setImageResource(R.mipmap.plane1);
            chosenPlane=findViewById(R.id.plane1);


        }
    }

    private void changePostion(int _x,int _y){

        if (chechOutOfBounds(_x, _y))
        {
            chosenPlane.setX(chosenPlane.getX()+_x);
            chosenPlane.setY(chosenPlane.getY()+_y);

        }
        else
        {
            Toast.makeText(this,"Out of bounds",Toast.LENGTH_SHORT).show();
        }

    }

    private boolean chechOutOfBounds(int newX,int newY){
        View parent = (View) chosenPlane.getParent();
        int parenMaxWidth= parent.getWidth();
        int parentMaxHeight=parent.getHeight();
        //check X movement
        if (chosenPlane.getX()+newX<0 || chosenPlane.getX()+chosenPlane.getWidth()+newX>parenMaxWidth)
            return false;
        //check Y movement
        if (chosenPlane.getY()+newY<0 || chosenPlane.getY()+chosenPlane.getHeight()+newY>parentMaxHeight)
            return false;

        // all good
        return true;
    }
}
