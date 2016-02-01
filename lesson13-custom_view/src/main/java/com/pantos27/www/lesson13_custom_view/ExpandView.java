package com.pantos27.www.lesson13_custom_view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
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
        init(attrs);
    }

    public ExpandView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "ExpandView: Context,AttributeSet,int");
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ExpandView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.d(TAG, "ExpandView: Context,AttributeSet,int");
        init(attrs);
    }


    public Boolean isExpanded() {
        return expanded;

    }

    private void init() {
        Log.d(TAG, "init: base");
        super.setOnClickListener(this);
    }

    private void init(AttributeSet attrs){
        init();
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.ExpandView);
        //init class members from xml
        this.expanded=array.getBoolean(R.styleable.ExpandView_expanded, false);
        firstRow=array.getString(R.styleable.ExpandView_firstRow);
        secondRow=array.getString(R.styleable.ExpandView_secondRow);
        Log.d(TAG, "init: with attributes. f.row: "+firstRow+" s.row: "+secondRow);

        array.recycle();
    }

    public CharSequence getSecondRow() {
        return secondRow;
    }

    public void setSecondRow(CharSequence secondRow) {
        this.secondRow = secondRow;
        //recalculates view after text change
        this.requestLayout();
    }

    public CharSequence getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(CharSequence firstRow) {
        this.firstRow = firstRow;
        //recalculates view after text change
        this.requestLayout();
    }



    public void setStateChangedListener(StateChangedListener stateChangedListener) {
        this.stateChangedListener = stateChangedListener;
    }



    @Override
    protected void onDraw(Canvas canvas) {
      //  Log.d(TAG, "onDraw: before if");
        //
        if (expanded){
            this.setText(firstRow + "\n" + secondRow);
        }
        else{
            this.setText(firstRow);

        }
        //Log.d(TAG, "onDraw: before super");

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
        Log.d(TAG, "onClick: ");

        expanded = !expanded;
        //redraw view
        this.requestLayout();
        if (stateChangedListener != null) {
            stateChangedListener.onStateChanged(this);

        }
    }
    //save/restore state methods
    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable p= super.onSaveInstanceState();
        SaveState sState=new SaveState(p);
        //save class memebers
        sState.expanded=this.expanded;
        sState.firstRow=this.firstRow.toString();
        sState.secondRow=this.secondRow.toString();
        return sState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SaveState saveState= (SaveState) state;
        //restore class members
        this.firstRow=saveState.firstRow;
        this.secondRow=saveState.secondRow;
        this.expanded=saveState.expanded;

        super.onRestoreInstanceState(saveState.getSuperState());

    }

    private static class SaveState extends BaseSavedState{
        String firstRow;
        String secondRow;
        Boolean expanded;

        public SaveState(Parcel source) {
            super(source);
            firstRow=source.readString();
            secondRow=source.readString();
            int tmp=source.readInt();
            expanded=tmp==1? true:false;

        }

        public SaveState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeString(firstRow);
            out.writeString(secondRow);
            out.writeInt(expanded ? 1 : 0);

        }

        public static final Parcelable.Creator<SaveState> CREATOR =new Creator<SaveState>() {
            @Override
            public SaveState createFromParcel(Parcel source) {
                return new SaveState(source);
            }

            @Override
            public SaveState[] newArray(int size) {
                return new SaveState[size];
            }
        };
    }
}
