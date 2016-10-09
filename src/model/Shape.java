package model;

/**
 * Created by Petros on 2016-10-09.
 */
abstract public class Shape {

    public static final double BILLION = 1_000_000_000.0;

    private double x, y; // position of the balls center
    private double dx, dy; // velocity measured in pixels/second
    private int color;
    private boolean filled, outOfBounds, collidable;


    protected Shape(double x, double y, int color, boolean filled, boolean outOfBounds, boolean collidable) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.filled = filled;
        this.outOfBounds = outOfBounds;
        this.collidable = collidable;
    }
}
