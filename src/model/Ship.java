package model;

import model.Shape.Direction;
import model.Shape.Shape;
import model.Shape.Triangle;

import java.util.ArrayList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
abstract public class Ship {

    private double x, y;
    private boolean isAlive, isExploded;
    private int healthPoints;
    private ArrayList<Triangle> shipGeometry = new ArrayList<>();
    private double weaponPosX, weaponPosY;


    protected Ship(double x, double y, int healthPoints) {
        this.x = x;
        this.y = y;
        this.isAlive = true;
        this.healthPoints = healthPoints;
        this.isExploded = false;
    }

    public double getX() {
        if (!shipGeometry.isEmpty())
            return shipGeometry.get(0).getX();
        return 0;
    }

    public double getY() {
        if (!shipGeometry.isEmpty())
            return shipGeometry.get(0).getY();
        return 0;
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

    public void setExploded(boolean exploded) {
        isExploded = exploded;
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void decreaseHealthPoints(int hP) {
        this.healthPoints-= hP;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public void explodeShip(){
        for (Shape s: shipGeometry) {
            s.explode();
        }
    }

    public boolean getShipConstraint(Direction direction) {
        for (Shape s: shipGeometry) {
            return s.getDirectionConstraint(direction);
        }
        return false;
    }

    public void moveShip(double newX, double newY) {
        for (Shape s: shipGeometry) {
            s.moveTo(s.getX()+newX,s.getY()+newY);
        }
    }

    public boolean isOutOfBounds() {

        for (Shape s: shipGeometry) {
            if (!s.isOutOfBounds())
                return false;
        }
        return true;
    }

    public void setCollidable(boolean collidable) {
        for (Shape s: shipGeometry) {
            s.setCollidable(collidable);
        }
    }

    public void setShipVelocity(double dx, double dy) {
        for (Shape s: shipGeometry) {
            s.setVelocity(dx,dy);
        }
    }

    public abstract ArrayList<Triangle> makeShip(double x, double y, double width, double height);


}
