package com.example.veierovioum.lesson14_painter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Custom Painter View
 */
public class MyPainter extends View implements View.OnTouchListener {

    static final String TAG = "MyPainter";
    private ArrayList<Shape> shapes=new ArrayList<>();
    private Shape tempShape;
    private Point startPoint;
    private Shapes selectedShape=Shapes.Rectangle;
    private SelectedColor selectedColor=SelectedColor.BLACK;

    /**
     * a collection of possible shapes to draw
     */
    public enum Shapes{
        Rectangle,Circle,Triangle,Line,Path,
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

    private void init(){
        super.setOnTouchListener(this);

    }
    private void init(AttributeSet attr){
        init();

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
                    case Triangle:
                        tempShape=new Triangle(startPoint,currentPoint,paint);
                        break;
                    case Line:
                        tempShape=new Line(startPoint,currentPoint,paint);
                        break;
                    case Path:
                        if (tempShape==null)
                            tempShape=new Path(startPoint,currentPoint,paint);
                        else
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

    public void restoreShapes(ArrayList<Shape> _shapes){
        if (_shapes != null) {
            this.shapes=_shapes;
            invalidate();
        }
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SaveState ss= (SaveState) state;

        this.shapes=ss.shapes;

        super.onRestoreInstanceState(ss);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable p= super.onSaveInstanceState();
        SaveState ss=new SaveState(p);

        ss.shapes=this.shapes;

        return ss;
    }

    private static class SaveState extends BaseSavedState{
        ArrayList<Shape> shapes;

        public SaveState(Parcel source) {
            super(source);
            shapes=new ArrayList<>();
            shapes=source.readArrayList(shapes.getClass().getClassLoader());
        }

        public SaveState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeList(shapes);
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
