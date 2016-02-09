package com.example.veierovioum.lesson16_balls;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created by Veierovioum on 08/02/2016.
 */
public class Movement implements Runnable{
    private static final String TAG = "BouncingBalls";
    View thisView;
    View otherView;
    private float vectorX;
    private float vectorY;
    private int velocity =5;
    Handler handler;
    private boolean running=true;
    private int angel;


    public Movement(View thisView, View otherView, Handler handler) {
        Log.d(TAG, "Movement: constractor");
        this.thisView = thisView;
        this.otherView = otherView;
        this.handler = handler;

        calcNewVector();
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private  void changePostion(final float _x, final float _y){
        //check if going out of bounds
        if (checkOutOfBounds(_x, _y))
        {
            //check  overlapping with other view
            if (checkOverlap(_x,_y))
            {
                Log.d(TAG, "changePostion: collision");
                calcNewVector();
                changePostion(vectorX,vectorY);
            }
            //set new location
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    thisView.setX(thisView.getX() + _x);
                    thisView.setY(thisView.getY() + _y);
                    thisView.setRotation(thisView.getRotation() + velocity);
                    //Log.d(TAG,this.toString()+ " postion changed x: "+thisView.getX()+" y: "+thisView.getY());
                    changePostion(_x,_y);
                }

            }, (6-velocity));



        }
        else
        {
            //out of bounds, change direction
            Log.d(TAG, "changePostion: out of bounds.\nx:" + thisView.getX() + " y:" + thisView.getY() +" Vx "+_x+" Vy "+_y);
            calcNewVector();
            changePostion(vectorX, vectorY);

//            Log.d(TAG, "changePostion: out of bounds. Vx:"+_x+" Vy:"+_y);

        }

    }

    private boolean checkOutOfBounds(float newX,float newY){
        View parent = (View) thisView.getParent();
        int parenMaxWidth= parent.getWidth();
        int parentMaxHeight=parent.getHeight();
        //Log.d(TAG, "checkOutOfBounds: "+parenMaxWidth+" "+parentMaxHeight);
        float xMaxWidth=thisView.getX()+ thisView.getWidth()+newX;
        float xMinWidth=thisView.getX()+newX;
        float yMax=thisView.getY()+ thisView.getHeight()+newY;
        float yMin=thisView.getY()+newY;
//        Log.d(TAG, "checkOutOfBounds: xMaxWidth "+xMaxWidth);
//        Log.d(TAG, "checkOutOfBounds: xmin: "+xMinWidth);
//        Log.d(TAG, "checkOutOfBounds: yMaxWidth "+yMax);
//        Log.d(TAG, "checkOutOfBounds: ymin "+yMin);
        //check X movement
        if ((thisView.getX()+newX)<0 || (thisView.getX()+ thisView.getWidth()+newX)>parenMaxWidth)
            return false;
        //check Y movement
        if ((thisView.getY()+newY)<0 || (thisView.getY()+ thisView.getHeight()+newY)>parentMaxHeight)
            return false;

        // all good
        return true;
    }

    private boolean checkOverlap(float newX,float newY){
        //check the four point of the view for overlapping
        if (checkPoint(thisView.getX()+newX, thisView.getY()+newY, otherView)
                || checkPoint(thisView.getX()+ thisView.getWidth()+newX, thisView.getY()+newY, otherView)
                || checkPoint(thisView.getX()+newX, thisView.getY()+ thisView.getHeight()+newY, otherView)
                || checkPoint(thisView.getX()+ thisView.getWidth()+newX, thisView.getY()+ thisView.getHeight()+newY, otherView))

            return true;

        return false;
    }

    /**
     * checks a certain point if overlaps given view
     * @param x
     * @param y
     * @param v
     * @return
     */
    private boolean checkPoint(float x, float y,View v){

        if (x>=v.getX() && x<=v.getX()+v.getWidth()
                && y>=v.getY() && y<=v.getY()+v.getHeight())
            return true;

        return false;
    }

    @Override
    public void run() {
        changePostion(vectorX, vectorY);

//        while (running) {
//            //  Point nextMove=calcNextStep();
//
////                try {
////                    Thread.sleep((6-velocity)*5);
////                } catch (InterruptedException e) {
////                    e.printStackTrace();
////                    Log.e(TAG, "run: sleep error", e);
////                }
//        }
    }

    void calcNewVector(){
        //Log.d(TAG, "calcNewVector: "+thisView.getId());
        Random rnd=new Random();
        this.angel=rnd.nextInt(360);
        this.vectorX= (float) (Math.cos((this.angel)));
        this.vectorY= (float) (Math.sin((this.angel)));
//        this.vectorX= (float) (10- rnd.nextInt(20));
//        this.vectorY= (float)(10- rnd.nextInt(20));

    }

    Point calcNextStep(){

        Point next=new Point(thisView.getX()+this.vectorX,thisView.getY()+this.vectorY);
        return next;

    }
}
