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

    @Override
    public boolean collision(Shape shape) {
        if (isCollidable()) {
            return ((getX() + getWidth() > shape.getX() && getX()+getWidth() < shape.getX()+shape.getWidth()) &&
                    getY() > shape.getY() && getY()+height < shape.getY() + shape.getHeight());
        }
        return false;
    }

    @Override
    public void constrain() {
        if (isCollidable()) {
            if (getX() < 0) {
                setDirectionConstraint(Direction.LEFT,true);
            }
            if (getX()+getWidth() > 800) {
                setDirectionConstraint(Direction.RIGHT,true);
            }
            if (getY() < 0) {
                setDirectionConstraint(Direction.UP, true);
            }
            if (getY()+getHeight() > 350) {
                setDirectionConstraint(Direction.DOWN, true);
            }
            if (getX() > 0 && getX()+getWidth()<800 && getY()>0 && getY()+getHeight()<350) {
                setDirectionConstraint(Direction.LEFT,false);
                setDirectionConstraint(Direction.RIGHT,false);
                setDirectionConstraint(Direction.UP,false);
                setDirectionConstraint(Direction.DOWN,false);
            }
        }
    }
}
