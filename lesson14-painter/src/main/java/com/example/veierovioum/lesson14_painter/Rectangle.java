package com.example.veierovioum.lesson14_painter;

/**
 * Created by Veierovioum on 02/02/2016.
 */
public class Rectangle extends Shape{
    Point point;
    float width;
    float height;

    public Rectangle(float x, float y, float width, float height) {
        super();
        point=new  Point(x, y);
        this.width = width;
        this.height = height;
    }
    public float getX() {
        return this.point.x;
    }
    public void setX(float x) {
        this.point.x = x;
    }
    public float getY() {
        return this.point.y;
    }
    public void setY(float y) {
        this.point.y = y;
    }
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rectangle [point=" + point + ", width=" + width + ", height=" + height + "]";
    }
    float area(){
        return width*height;
    }

    float perimeter(){
        return 2*(width+height);
    }

    void resize(float precent){

        float num=(precent/100);
        this.setWidth(Math.round(this.getWidth()*num));
        this.setHeight(Math.round(this.getHeight()*num));
    }

    void move(float _x,float _y){
        setX(_x);
        setY(_y);
    }

    void move(Point p){
        this.move(p.getX(), p.getY());
    }

    boolean isEqual(Rectangle r){
        if (r.area()==this.area()) {
            return true;
        } else {
            return false;
        }
    }

    void resize(float _width,float _height){
        setWidth(_width);
        setHeight(_height);
    }

    Rectangle union(Rectangle r){
        float _x=Math.min(r.getX(), this.getX());
        float _y=Math.min(r.getY(), this.getY());

        float _width=Math.max(r.getWidth()+r.getX(), this.getWidth()+this.getX());
        float _height=Math.max(r.getHeight()+r.getY(), this.getHeight()+this.getY());

        return new Rectangle(_x, _y, _width, _height);
    }
    boolean intersect(Point p){

        boolean answer=false;

        if (this.getX()<= p.getX() && (this.getX()+this.getWidth())>=p.getX()
                && this.getY()<= p.getY() && (this.getY()+this.getHeight())>=p.getY()){
            answer=true;
        }

        return answer;

    }

    boolean intersect(float x,float y){
        return this.intersect(new Point(x, y));
    }

    boolean intersect(Rectangle r){

        boolean answer = false;

        if (this.intersect(r.getX(),r.getY()) || this.intersect(r.getX(),r.getY()+r.getHeight())
                || this.intersect(r.getX()+r.getWidth(), r.getY())
                || this.intersect(r.getX()+r.getWidth(), r.getY()+r.getHeight())) {
            answer=true;
        }

        return answer;
    }
}
