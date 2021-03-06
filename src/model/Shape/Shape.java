package model.Shape;

import java.util.Random;

/**
 * @author Petros Hagos & Dag Oldenburg.
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

    protected Shape() {
        this.x = 0;
        this.y = 0;
        this.color = 0;
        this.filled = true;
        this.outOfBounds = false;
        this.collidable = true;
    }

    /**
     * @return x-coordinate of the upper-left corner
     */
    public double getX() {
        return x;
    }

    /**
     * @return y-coordinate of the upper-left corner
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the new x coordinate of the the upper-left corner
     *
     * @param newX
     */
    public void setX(double newX) {
        x = newX;
    }

    /**
     * Sets the new y coordinate of the the upper-left corner
     *
     * @param newY
     */
    public void setY(double newY) {
        y = newY;
    }

    /**
     * @return the color of this shape
     */
    public int getColor() {
        return color;
    }

    /**
     * Set the new color for this shape.
     *
     * @param newColor
     */
    public void setColor(int newColor) {
        this.color = newColor;
    }

    /**
     * @return the velocity in the x-direction, pixels/second
     */
    public double getDx() {
        return dx;
    }

    /**
     * @return the velocity in the y-direction, pixels/second
     */
    public double getDy() {
        return dy;
    }

    /**
     * @return true if the shape in question is filled, otherwise false
     */
    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean isOutOfBounds() {
        return outOfBounds;
    }

    public void setOutOfBounds(boolean outOfBounds) {
        this.outOfBounds = outOfBounds;
    }

    public boolean isCollidable() {
        return collidable;
    }

    public void setCollidable(boolean collidable) {
        this.collidable = collidable;
    }

    /**
     * Sets the velocity, pixels/second, to (newDx, newDy).
     *
     * @param newDx
     * @param newDy
     */
    public void setVelocity(double newDx, double newDy) {
        dx = newDx;
        dy = newDy;
    }

    /**
     * Moves the center of the ball to (newX, newY).
     *
     * @param newX
     * @param newY
     */
    public void moveTo(double newX, double newY) {
        x = newX;
        y = newY;
    }

    /**
     * Move the shape a distance depending on the elapsed time in nanoseconds.
     * NB - the velocity is measured in pixels/second.
     *
     * @param elapsedTimeNs the elapsed time in nanoseconds.
     */
    public void move(long elapsedTimeNs) {
        x += dx * elapsedTimeNs / BILLION;
        y += dy * elapsedTimeNs / BILLION;
    }

    /**
     * Gives a shape the velocity of 500 in
     * a randomly generated direction (360 degrees).
     *
     */
    public void explode() {
        Random random = new Random();
        double x,y;
        int c = random.nextInt(5);
        x = (random.nextDouble()*500);
        y = Math.sqrt(250000-(x*x));
        switch (c) {
            case 1:     x=-x;
                break;
            case 2:     y=-y;
                break;
            case 3:     x=-x;
                        y=-y;
                        break;
            default:    break;
        }
        setVelocity(x, y);
    }

}
