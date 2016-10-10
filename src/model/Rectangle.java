package model;

/**
 * Created by Petros on 2016-10-09.
 */
public class Rectangle extends Shape {

    private double width, height;

    public Rectangle(double x, double y, double width, double height, int color, boolean filled, boolean outOfBounds, boolean collidable) {
        super(x,y,color,filled,outOfBounds,collidable);
        this.width = width;
        this.height = height;
    }

}
