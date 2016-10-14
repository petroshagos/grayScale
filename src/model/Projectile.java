package model;

import model.Shape.Rectangle;
import model.Shape.Shape;

import java.util.LinkedList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class Projectile {

    private LinkedList<Rectangle> projectile = new LinkedList<>();

    public Projectile(double x, double y) {
        for (int i=0;i<3;i++){
            for (int j=0;j<2;j++){
                this.projectile.add(new Rectangle(x+2*i+75,y+2*j+25,2,2,4,true,false,true));
            }
        }
        for (Shape s: this.projectile) {
            s.setVelocity(40,0);
        }
    }

    public LinkedList<Rectangle> getProjectile() {
        return this.projectile;
    }

}
