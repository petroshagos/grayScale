package model;

import java.util.LinkedList;

/**
 * Created by Petros on 2016-10-09.
 */
public class Projectile {

    private LinkedList<Shape> projectile;

    public Projectile(double x, double y) {
        for (int i=0;i<3;i++){
            for (int j=0;j<2;j++){
                this.projectile.add(new Rectangle(x+6*i,y+6*j,6,6,4,true,false,true));
            }
        }
        for (Shape s: this.projectile) {
            s.setVelocity(40,0);
        }
    }

    public LinkedList<Shape> getProjectile() {
        return this.projectile;
    }
}
