package model;

import java.util.ArrayList;

/**
 * Created by Petros on 2016-10-09.
 */
abstract public class Ship {

    private double x, y;
    private boolean isAlive;
    private int healthPoints;
    private ArrayList<Shape> shipGeometry;

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

    public ArrayList<Shape> getShipGeometry() {
        return shipGeometry;
    }

    public void setShipGeometry(ArrayList<Shape> shipGeometry) {
        this.shipGeometry = shipGeometry;
    }

    abstract ArrayList<Shape> makeShip();
}
