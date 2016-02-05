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

    static final String TAG = "MyPainter";
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
        init();
    }

    public MyPainter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyPainter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyPainter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    void init(){
        super.setOnTouchListener(this);

    }
    void init(AttributeSet attr){
        init();
//        TypedArray array = getContext().obtainStyledAttributes(attr, R.styleable.MyPainter);
        //init class members from xml


//        array.recycle();
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

        int action=event.getActionMasked();
        Point currentPoint=new Point(event.getX(),event.getY());
        Paint paint=new Paint();
        paint.setColor(selectedColor.getValue());
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);

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
                        tempShape=new Circle(startPoint,currentPoint,paint);
                        break;
                    case Rectangle:
                        tempShape= new Rectangle(startPoint,currentPoint,paint);
                        break;
                    case Line:
                        tempShape=new Line(startPoint,currentPoint,paint);
                        break;
                    case Path:
                        if (tempShape==null)
                            tempShape=new Path(startPoint,currentPoint,paint);
                        else
                            Log.d(TAG, "onTouch: Path "+((Path)tempShape));
                            ((Path)tempShape).addToPath(currentPoint);
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

        this.invalidate();

        return true;
    }

    /**
     * resets the view with 0 shapes
     * @return the shapes array before reset
     */
    public ArrayList<Shape> clearAllShapes(){
        ArrayList<Shape> returnArray= (ArrayList<Shape>) shapes.clone();
        shapes.clear();
        this.invalidate();
        return returnArray;
    }

    public Shape removeLastShape() {
        if(shapes.size()>0) {
            Shape shape =shapes.remove(shapes.size() - 1);
            this.invalidate();
            return shape;
        }
        else return null;
    }

    public void addShape(Shape newShape){
        shapes.add(newShape);
        this.invalidate();
    }
// TODO: 04/02/2016 save state
}
