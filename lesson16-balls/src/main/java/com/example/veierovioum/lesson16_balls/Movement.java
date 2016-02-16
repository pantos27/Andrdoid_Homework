package com.example.veierovioum.lesson16_balls;

import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;

import java.util.Random;

/**
 * Created by Veierovioum on 08/02/2016.
 */
public class Movement implements Runnable{
    private static final String TAG = "BouncingBalls";
    View thisView;
    View otherView;
    RectF rect1;
    RectF rect2;
    RectF rectParent;
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

    private synchronized void changePostion(final float _x, final float _y){
        //update rectangulars
        updateRects();
        //check if going out of bounds
        if (checkOutOfBoundsRect(_x, _y))
        {
            //check  overlapping with other view
            if (checkOverlapRect(_x, _y))
            {
                Log.d(TAG, "changePostion: collision");
                //go to opposite direction
//                vectorX=-1*_x;
//                vectorY=-1*_y;
                calcNewVector();
                changePostion(vectorX,vectorY);
                return;
            }
            //set new location
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    thisView.setX(thisView.getX() + _x);
                    thisView.setY(thisView.getY() + _y);
                    thisView.setRotation(thisView.getRotation() + velocity);
                    //Log.d(TAG,this.toString()+ " postion changed x: "+thisView.getX()+" y: "+thisView.getY());
                    if(isRunning())
                        //continue in the same vector
                        changePostion(_x,_y);
                }

            }, 5);



        }
        else
        {
            //out of bounds, change direction
            Log.d(TAG, "changePostion: out of bounds");
            calcNewVector();
            changePostion(vectorX, vectorY);
        }

    }

    private void updateRects() {

        this.rect1=getRecF(this.thisView);
        this.rect2=getRecF(this.otherView);
        this.rectParent=getRecF((View)this.thisView.getParent());
    }


    private boolean checkOutOfBoundsRect(float _x,float _y)
    {
        //Log.d(TAG, "checkOutOfBoundsRect: ");
        rect1.offset(_x,_y);

        return rectParent.contains(rect1);
    }

    private boolean checkOverlapRect(float _x,float _y){
        //Log.d(TAG, "checkOverlapRect: ");
        rect1.offset(_x, _y);
        return RectF.intersects(rect1,rect2);
    }

    @Override
    public void run() {
        changePostion(vectorX, vectorY);

    }

    void calcNewVector(){
        Log.d(TAG, "calcNewVector: "+thisView.getId());
        Random rnd=new Random();
        this.angel=rnd.nextInt(360);
        this.vectorX= (float) (velocity*Math.cos((this.angel)));
        this.vectorY= (float) (velocity*Math.sin((this.angel)));
//        this.vectorX= (float) (10- rnd.nextInt(20));
//        this.vectorY= (float)(10- rnd.nextInt(20));

    }


    private RectF getRecF(View v){

        return new RectF(v.getX(),v.getY(),v.getX()+v.getWidth(),v.getY()+v.getHeight());
    }
}
