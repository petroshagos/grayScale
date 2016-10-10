package model;

/**
 * Created by Petros on 2016-10-09.
 */
public class Triangle extends Shape {
    private double width, height;
    private String type;

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     * @param filled
     * @param outOfBounds
     * @param collidable
     * @param type
     */
    public Triangle(double x, double y, double width, double height, int color,
                    boolean filled, boolean outOfBounds, boolean collidable, String type) {
        super(x,y,color,filled,outOfBounds,collidable);
        this.width = width;
        this.height = height;
        this.type = type;
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
