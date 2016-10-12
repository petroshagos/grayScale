package model;

import model.Shape.Triangle;
import model.Shape.TriangleOrientation;

import java.util.ArrayList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class EnemyShip extends Ship {

    private boolean isOutOfBounds;

    public EnemyShip(double x,double y,int healthPoints, boolean isOutOfBounds) {
        super(healthPoints, 900, 200);
        this.isOutOfBounds = isOutOfBounds;
        this.setShipGeometry(makeShip());

    }

    @Override
    public ArrayList<Triangle> makeShip() {
        ArrayList<Triangle> temp = new ArrayList<>();
        for (int i=0;i<3;i++){
            for (int j=0;j<2;j++){
                temp.add(new Triangle(super.getX()+6*i,super.getY()+6*j,20,20,0,true,false,true, TriangleOrientation.UpperLeft));
            }
        }
        return temp;
    }

}
