package com.pantos27.www.lesson13_custom_view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by aattar on 31/01/2016.
 */
public class ExpandView extends TextView {
    CharSequence firstRow;
    CharSequence secondRow;
    Boolean expanded=false;
    StateChangedListener stateChangedListener;

    public ExpandView(Context context) {
        super(context);
    }

    public ExpandView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ExpandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExpandView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        throw new UnsupportedOperationException("use setFirstRow/setSecondRow");
    }

    public Boolean isExpanded() {
        return expanded;
    }


    public CharSequence getSecondRow() {
        return secondRow;
    }

    public void setSecondRow(CharSequence secondRow) {
        this.secondRow = secondRow;
    }

    public CharSequence getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(CharSequence firstRow) {
        this.firstRow = firstRow;
    }

    @Override
    public CharSequence getText() {
        //return super.getText();
        throw new UnsupportedOperationException("use getFirstRow/getSecondRow");

    }

    public void setStateChangedListener(StateChangedListener stateChangedListener) {
        this.stateChangedListener = stateChangedListener;
    }

    public void expand(){
        if (!expanded){
            expanded=true;
            this.setText(firstRow+"\n"+secondRow);

            if (stateChangedListener!=null){
                stateChangedListener.onStateChanged(this);

            }
        }

    }
    public void collapse(){
        if (expanded){
            expanded=false;
            this.setText(firstRow);

            if (stateChangedListener!=null){
                stateChangedListener.onStateChanged(this);

            }
        }
    }

    @Override
    public boolean callOnClick() {

        return false;
    }

    @Override
    public boolean performClick() {
        return this.callOnClick();
    }

}
