package model;

import model.Shape.Triangle;

import java.util.ArrayList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
abstract public class Ship {

    private double x, y;
    private boolean isAlive;
    private int healthPoints;
    private ArrayList<Triangle> shipGeometry;

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

    abstract ArrayList<Triangle> makeShip();
}
