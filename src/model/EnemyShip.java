package model;

import model.Shape.Shape;
import model.Shape.Triangle;
import model.Shape.TriangleOrientation;

import java.util.ArrayList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class EnemyShip extends Ship {

    private boolean isOutOfBounds;
    private double x;
    private double y;
    public EnemyShip(double x,double y,int healthPoints, boolean isOutOfBounds) {
        super(healthPoints, 900, 200);
        this.x = x;
        this.y = y;
        this.isOutOfBounds = isOutOfBounds;
        this.setShipGeometry(makeShip());
        super.setWeaponPosX(getShipGeometry().get(0).getX());
        super.setWeaponPosY(getShipGeometry().get(0).getY()+7);

    }

    @Override
    public ArrayList<Triangle> makeShip() {
        double x2 = 15;
        double y2 = 15;
        ArrayList<Triangle> temp = new ArrayList<>();
        for (int i=1;i<2;i++){
            temp.add(new Triangle(x+(i*x2),y+y2,x2,y2, 0, true, false, true, TriangleOrientation.UpperRight));
            temp.add(new Triangle(x+(i*x2),y+y2,x2,y2, 0, true, false, true, TriangleOrientation.LowerLeft));
        }
        for(Shape s : temp){
            s.setVelocity(-20.0, 0);
        }
        return temp;
    }

    public void updateWeaponPos() {
        setWeaponPosX(getShipGeometry().get(0).getX());
        setWeaponPosY(getShipGeometry().get(0).getY()+7);
    }

}