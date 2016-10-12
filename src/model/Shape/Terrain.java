package model.Shape;

/**
 * @author Petros Hagos & Dag Oldenburg
 */
public class Terrain extends Shape {

    private double[] xPoints, yPoints;

    public Terrain() {
        super();
    }

    public Terrain(double x, double y, int color, boolean filled,
                   boolean outOfBounds, boolean collidable, double[] xPoints, double[] yPoints) {
        super(x,y,color,filled,outOfBounds,collidable);
        this.xPoints = xPoints;
        this.yPoints = yPoints;
    }

    public double[] getXPoints() {
        return xPoints;
    }

    public double[] getYPoints() {
        return yPoints;
    }

    public int getNrOfPoints() {
        return xPoints.length;
    }

    public double[] getLastPoint() {
        return (new double[]{this.xPoints[xPoints.length-3], this.yPoints[yPoints.length-3]});
    }

    @Override
    public void move(long elapsedTimeNs) {
        for (int i=0; i<xPoints.length;i++) {
            xPoints[i] += getDx() * elapsedTimeNs / BILLION;
        }
        for (int i=0; i<yPoints.length;i++) {
            yPoints[i] += getDy() * elapsedTimeNs / BILLION;
        }
        setX(getX() + getDx() * elapsedTimeNs / BILLION);
        setY(getY() + getDy() * elapsedTimeNs / BILLION);
    }
}
