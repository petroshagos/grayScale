package model.Shape;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class Triangle extends Shape {
    private double width, height;
    private TriangleOrientation orientation;

    /**
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     * @param filled
     * @param outOfBounds
     * @param collidable
     * @param orientation
     */
    public Triangle(double x, double y, double width, double height, int color,
                    boolean filled, boolean outOfBounds, boolean collidable, TriangleOrientation orientation) {
        super(x,y,width,height,color,filled,outOfBounds,collidable);
        this.orientation = orientation;
    }

    public TriangleOrientation getOrientation() {
        return this.orientation;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public boolean collision(Shape shape) {
        if (isCollidable()) {
            return (getX() > shape.getX() && getX()+width < shape.getX() ||
                    getY() > shape.getY() && getY()+height < shape.getY());
        }
        return false;
    }
}
