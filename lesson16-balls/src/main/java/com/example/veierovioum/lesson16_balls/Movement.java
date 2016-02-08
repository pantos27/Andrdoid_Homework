package com.example.veierovioum.lesson16_balls;

import android.os.Handler;
import android.view.View;

/**
 * Created by Veierovioum on 08/02/2016.
 */
public class Movement implements Runnable{
    View thisView;
    View otherView;
    private int vector;
    private int speed;
    Handler handler;



    public Movement(View thisView, View otherView, Handler handler) {
        this.thisView = thisView;
        this.otherView = otherView;
        this.handler = handler;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    private void changePostion(final int _x, final int _y){
        //check plane going out of boinds
        if (checkOutOfBounds(_x, _y))
        {
            //check planes overlapping
            if (checkOverlap(_x,_y))
            {
                // TODO: 08/02/2016 collision, change direction
                return;
            }
            //set new location
            // TODO: 08/02/2016 set new location on ui thread
            handler.post(new Runnable() {
                @Override
                public void run() {
                    thisView.setX(thisView.getX()+_x);
                    thisView.setY(thisView.getY() + _y);
                }
            });
            //thisView.setX(thisView.getX()+_x);
            //thisView.setY(thisView.getY()+_y);

        }
        else
        {
            // TODO: 08/02/2016 out of bounds change direction
        }

    }

    private boolean checkOutOfBounds(int newX,int newY){
        View parent = (View) thisView.getParent();
        int parenMaxWidth= parent.getWidth();
        int parentMaxHeight=parent.getHeight();
        //check X movement
        if (thisView.getX()+newX<0 || thisView.getX()+ thisView.getWidth()+newX>parenMaxWidth)
            return false;
        //check Y movement
        if (thisView.getY()+newY<0 || thisView.getY()+ thisView.getHeight()+newY>parentMaxHeight)
            return false;

        // all good
        return true;
    }

    private boolean checkOverlap(int newX,int newY){
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

    }

    int calcNewVector(){
        return Math.atan2();
        Math.atan()
    }
}
