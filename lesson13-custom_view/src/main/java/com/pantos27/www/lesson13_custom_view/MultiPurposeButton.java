package com.pantos27.www.lesson13_custom_view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by aattar on 31/01/2016.
 */
public class MultiPurposeButton extends Button implements View.OnClickListener{

    ArrayList<OnClickListener> listeners=new ArrayList<>();

    public MultiPurposeButton(Context context) {
        super(context);
        init();

    }


    public MultiPurposeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public MultiPurposeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MultiPurposeButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();

    }
    private void init() {
        super.setOnClickListener(this);
    }


    @Override
    public void setOnClickListener(OnClickListener l) {
        throw new NullPointerException("Use addOnClickListener");
    }

    public void addOnClickListener(OnClickListener l){
        listeners.add(l);
    }

    public void removeOnClickListener(OnClickListener l){
        listeners.remove(l);
    }

    public void removeAllOnClickListeners(){
        listeners.clear();
    }


    @Override
    public void onClick(View v) {
        for (OnClickListener l : listeners) {
            l.onClick(this);
        }

    }


}
