package model.Shape;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class Rectangle extends Shape {

    private double width, height;

    public Rectangle(double x, double y, double width, double height, int color, boolean filled, boolean outOfBounds, boolean collidable) {
        super(x,y,width,height,color,filled,outOfBounds,collidable);

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
            return ((getX() + getWidth() > shape.getX() && getX()+getWidth() < shape.getX()+shape.getWidth()) &&
                    getY() > shape.getY() && getY()+height < shape.getY() + shape.getHeight());
        }
        return false;
    }
    @Override
    public void constrain(){}
}
