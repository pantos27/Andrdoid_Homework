package com.example.veierovioum.lesson14_painter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by aattar on 03/02/2016.
 */
public class MyPainter extends View implements View.OnTouchListener {

    private static final String TAG = "MyPainter";
    ArrayList<Shape> shapes=new ArrayList<>();
    Shape tempShape;
    Point startPoint;
    public Shapes selectedShape=Shapes.Rectangle;
    public SelectedColor selectedColor=SelectedColor.BLACK;
    //shape types
    static final int RECTANGLE=100;


//    //colors list
//    static final int BLUE= Color.BLUE;
//    static final int BLACK= Color.BLACK;
//    static final int GRAY= Color.GRAY;
//    static final int GREEN= Color.GREEN;
//    static final int RED= Color.RED;
//    static final int CYAN= Color.CYAN;
//    static final int YELLOW= Color.YELLOW;


    public enum ShapeColor{
        BLUE ("Blue",Color.BLUE),
        BLACK ("Black",Color.BLACK);

        private  int value;
        private  String name;
        ShapeColor(String _name,int color) {
            this.name=_name;
            this.value=color;
        }

        public int getValue(){return value;}
        public String getName(){return name;}

    }

    public enum Shapes{
        Rectangle,Circle,Line,Path;
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
    void init(AttributeSet attr){
        init();
        TypedArray array = getContext().obtainStyledAttributes(attr, R.styleable.MyPainter);
        //init class members from xml


        array.recycle();
    }

    public void setSelectedShape(Shapes selectedShape) {
        this.selectedShape = selectedShape;
    }

    public void setSelectedColor(SelectedColor selectedColor) {
        this.selectedColor = selectedColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw static shapes
        for (Shape shape : shapes) {
            shape.draw(canvas);
        }
        //draw active shape
        if (tempShape!=null) tempShape.draw(canvas);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO: 03/02/2016 catch touch event

        int action=event.getActionMasked();
        Point currentPoint=new Point(event.getX(),event.getY());
        Paint paint=new Paint();
        paint.setColor(selectedColor.getValue());

        switch (action){

            case MotionEvent.ACTION_DOWN:
                //save start point
                startPoint=currentPoint;
                Log.d(TAG, "onTouch: down");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onTouch: move start");
                switch (selectedShape) {
                    case Circle:
// TODO: 04/02/2016 add circle
                        break;
                    case Rectangle:
                        tempShape= new Rectangle(startPoint,currentPoint,paint);
                        break;
                    case Line:
                        // TODO: 04/02/2016 add line
                        break;
                    case Path:
                        if (tempShape==null)
                        //new path

                        //todo:add to path
                        break;
                }
                Log.d(TAG, "onTouch: move end");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouch: up");
                if(tempShape!=null)
                    shapes.add(tempShape);
                tempShape=null;
                break;
        }
        return true;
    }

    /**
     * resets the view with 0 shapes
     * @return the shapes array before reset
     */
    public ArrayList<Shape> clearAllShapes(){
        ArrayList<Shape> returnArray= (ArrayList<Shape>) shapes.clone();
        shapes.clear();
        return returnArray;
    }

    public Shape removeLastShape() {
        if(shapes.size()>0)
            return shapes.remove(shapes.size()-1);
        else return null;
    }

    public void addShape(Shape newShape){
        shapes.add(newShape);
    }

}
