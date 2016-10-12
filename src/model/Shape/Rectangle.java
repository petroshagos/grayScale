package model.Shape;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class Rectangle extends Shape {

    private double width, height;

    public Rectangle(double x, double y, double width, double height, int color, boolean filled, boolean outOfBounds, boolean collidable) {
        super(x,y,color,filled,outOfBounds,collidable);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
