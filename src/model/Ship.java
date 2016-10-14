package model;

import model.Shape.Triangle;
import model.Shape.TriangleOrientation;

import java.util.ArrayList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
abstract public class Ship {

    private double x, y;
    private boolean isAlive;
    private int healthPoints;
    private ArrayList<Triangle> shipGeometry;

    public Ship() {
        this.x = 100;
        this.y = 200;
        this.healthPoints = 100;
    }

    protected Ship(double x, double y, int healthPoints) {
        this.x = x;
        this.y = y;
        this.isAlive = true;
        this.healthPoints = healthPoints;

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public ArrayList<Triangle> getShipGeometry() {
        return shipGeometry;
    }

    public void setShipGeometry(ArrayList<Triangle> shipGeometry) {
        this.shipGeometry = shipGeometry;
    }

    private void fill(double x, double y) {
        double x2 = 15;
        double y2 = 15;
        shipGeometry.add(new Triangle(x,y,x2,y2, 0, true, false, false, TriangleOrientation.UpperRight));
        shipGeometry.add(new Triangle(x,y,x2,y2, 0, true, false, false, TriangleOrientation.UpperRight));
        shipGeometry.add(new Triangle(x,y,x2,y2, 0, true, false, false, TriangleOrientation.LowerLeft));
        for (int i=1;i<4;i++){
            shipGeometry.add(new Triangle(x,y,x2,y2, 0, true, false, false, TriangleOrientation.UpperRight));
            shipGeometry.add(new Triangle(x-1,y,x2,y2, 0, true, false, false, TriangleOrientation.LowerLeft));
        }
        shipGeometry.add(new Triangle(x-1,y,x2,y2, 0, true, false, false, TriangleOrientation.UpperRight));

    }

    abstract ArrayList<Triangle> makeShip();
}
