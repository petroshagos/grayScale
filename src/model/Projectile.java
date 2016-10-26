package model;

import model.Shape.Rectangle;
import model.Shape.Shape;

import java.util.LinkedList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class Projectile {

    private LinkedList<Rectangle> projectile = new LinkedList<>();
    private boolean isPlayer;
    private int damage;

    public Projectile(double x, double y,boolean isPlayer,int damage) {
        for (int i=0;i<3;i++){
            for (int j=0;j<2;j++){
                this.projectile.add(new Rectangle(x+2*i,y+2*j,4,4,4,true,false,true));
            }
        }
        this.damage = damage;
        this.isPlayer = isPlayer;
        setVelocity(250,0);
    }
    public int getDamage(){
        return damage;
    }
    public void setCollidable(boolean b) {
        for (Shape s: projectile) {
            s.setCollidable(b);
        }
    }
    public boolean isPlayer(){
        return isPlayer;
    }
    public void explodeProjectile() {
        for (Shape s: projectile) {
            s.explode();
        }
    }
    public void setVelocity(double dx, double dy) {
        for (Shape s : this.projectile) {
            s.setVelocity(dx, dy);
        }
    }
    public boolean isOutOfBounds() {

        for (Shape s: projectile) {
            if (!s.isOutOfBounds())
            return false;
        }
        return true;
    }

    public LinkedList<Rectangle> getProjectile() {
        return this.projectile;
    }

}
