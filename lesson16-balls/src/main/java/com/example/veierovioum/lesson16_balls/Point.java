package com.example.veierovioum.lesson16_balls;

/**
 * Created by Veierovioum on 02/02/2016.
 */
public class Point {
    private float x;
    private float y;

    /**
     * @return the x
     */
    public float getX() {
        return x;
    }
    /**
     * @param x the x to set
     */
    public void setX(float x) {
        this.x = x;
    }
    /**
     * @return the y
     */
    public float getY() {
        return y;
    }
    /**
     * @param y the y to set
     */
    public void setY(float y) {
        this.y = y;
    }
    /**
     * @param x
     * @param y
     */
    public Point(float x, float y) {
        super();
        this.x = x;
        this.y = y;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Point [x=" + x + ", y=" + y + "]";
    }
    /**
     * helper method to claculate distance
     * @param _x X
     * @param _y Y
     * @return
     */
    private float pythagoras(float _x,float _y){

        return (float)Math.sqrt(Math.pow(_x, 2)+Math.pow(_y, 2));
    }

    public float distance()
    {
        float dist=0;

        dist=pythagoras(getX(), getY());

        return dist;
    }


    public float distance(float _x,float _y){
        float dist;

        dist = pythagoras(_x-getX(), _y-getY());

        return dist;

    }

    public float distance(Point p){

        return this.distance(p.getX(), p.getY());
    }

}
