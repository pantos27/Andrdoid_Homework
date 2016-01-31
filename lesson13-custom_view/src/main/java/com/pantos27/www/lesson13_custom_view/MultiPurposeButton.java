package com.pantos27.www.lesson13_custom_view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by aattar on 31/01/2016.
 */
public class MultiPurposeButton extends Button{

    ArrayList<OnClickListener> listeners=new ArrayList<>();

    public MultiPurposeButton(Context context) {
        super(context);

    }

    public MultiPurposeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiPurposeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MultiPurposeButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
    public boolean callOnClick() {
        for (OnClickListener l : listeners) {
            l.onClick(this);
        }
        if (listeners.isEmpty()){
            return false;
        }
        else return true;
    }

    @Override
    public boolean performClick() {
        return this.callOnClick();
    }
}
