package model;

/**
 * Created by Petros on 2016-10-09.
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

    public double[] getLastPoint() {
        return (new double[]{this.xPoints[xPoints.length-3], this.yPoints[yPoints.length-3]});
    }

}
