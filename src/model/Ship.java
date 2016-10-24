package model;

import model.Shape.Shape;
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
    private double weaponPosX, weaponPosY;


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

    public void setWeaponPosX(double weaponPosX) {
        this.weaponPosX = weaponPosX;
    }

    public void setWeaponPosY(double weaponPosY) {
        this.weaponPosY = weaponPosY;
    }

    public double getWeaponPosX() {
        return weaponPosX;
    }

    public double getWeaponPosY() {
        return weaponPosY;
    }
    public void updateShipWeaponPos(double dx, double dy){
        setWeaponPosX(dx+75);
        setWeaponPosY(dy+25);
    }

    public void explodeShip(){
        for (Shape s: shipGeometry) {
            s.explode();
        }
    }

    public abstract ArrayList<Triangle> makeShip();


}
