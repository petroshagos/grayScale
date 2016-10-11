package model;

import model.Shape.Rectangle;
import model.Shape.Shape;

import java.util.ArrayList;

/**
 * Created by Petros on 2016-10-09.
 */
public class EnemyShip extends Ship {

    private boolean isOutOfBounds;

    public EnemyShip(double x,double y,int healthPoints, boolean isOutOfBounds) {
        super(healthPoints, 900, 200);
        this.isOutOfBounds = isOutOfBounds;
        this.setShipGeometry(makeShip());

    }

    @Override
    public ArrayList<Shape> makeShip() {
        ArrayList<Shape> temp = new ArrayList<>();
        for (int i=0;i<3;i++){
            for (int j=0;j<2;j++){
                temp.add(new Rectangle(super.getX()+6*i,super.getY()+6*j,20,20,0,true,false,true));
            }
        }
        return temp;
    }

}
