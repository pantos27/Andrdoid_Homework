package com.example.veierovioum.lesson14_painter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by aattar on 03/02/2016.
 */
public class MyPainter extends View implements View.OnTouchListener {

    ArrayList<Shape> shapes;
    public int selectedShape=1;
    public ShapeColor selectedColor;
    //shape types
    static final int RECTANGLE=100;

    //colors list
    static final int BLUE= Color.BLUE;
    static final int BLACK= Color.BLACK;
    static final int GRAY= Color.GRAY;
    static final int GREEN= Color.GREEN;
    static final int RED= Color.RED;
    static final int CYAN= Color.CYAN;
    static final int YELLOW= Color.YELLOW;


    public enum ShapeColor{
        BLUE ("Blue",Color.BLUE);

        private final int value;
        private final String name;
        ShapeColor(String _name,int color) {
            this.name=_name;
            this.value=color;
        }

        public int getValue(){return value;}
        public String getName(){return name;}

    }



    public MyPainter(Context context) {
        super(context);
    }

    public MyPainter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPainter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyPainter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    void init(){
        super.setOnTouchListener(this);

    }

    public void setSelectedShape(int selectedShape) {
        this.selectedShape = selectedShape;
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Shape shape : shapes) {
            shape.draw(canvas);
        }
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO: 03/02/2016 catch touch event

        return true;
    }
}
