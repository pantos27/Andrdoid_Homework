package com.pantos27.www.lesson13_custom_view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by aattar on 31/01/2016.
 */
public class ExpandView extends TextView implements View.OnClickListener{
    public static final String TAG = "ExpandView_TAG";
    CharSequence firstRow;
    CharSequence secondRow;
    Boolean expanded=false;
    StateChangedListener stateChangedListener;

    public ExpandView(Context context) {
        super(context);
        Log.d(TAG, "ExpandView: Context");

        init();
    }

    public ExpandView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "ExpandView: Context,AttributeSet");
        init();
    }

    public ExpandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "ExpandView: Context,AttributeSet,int");
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExpandView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG, "ExpandView: Context,AttributeSet,int");
        init();
    }


    public Boolean isExpanded() {
        return expanded;

    }

    private void init() {
        Log.d(TAG, "init:");
        super.setOnClickListener(this);
    }


    public CharSequence getSecondRow() {
        return secondRow;
    }

    public void setSecondRow(CharSequence secondRow) {
        this.secondRow = secondRow;
        this.requestLayout();
    }

    public CharSequence getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(CharSequence firstRow) {
        this.firstRow = firstRow;
        this.requestLayout();
    }



    public void setStateChangedListener(StateChangedListener stateChangedListener) {
        this.stateChangedListener = stateChangedListener;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        if (expanded){
            this.setText(firstRow + "\n" + secondRow);
        }
        else{
            this.setText(firstRow);

        }
        super.onDraw(canvas);
    }

    public void expand(){
        if (!expanded){
           onClick(this);
        }

    }
    public void collapse(){
        if (expanded){
           onClick(this);
        }
    }

    @Override
    public void setOnClickListener(OnClickListener l) {

    }

    @Override
    public void onClick(View v) {
        expanded = !expanded;
        this.requestLayout();
        if (stateChangedListener != null) {
            stateChangedListener.onStateChanged(this);

        }
    }
}
