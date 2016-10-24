package model;

import model.Shape.Triangle;
import model.Shape.TriangleOrientation;

import java.util.ArrayList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class PlayerShip extends Ship {


    public PlayerShip() {
        super(-100, 200, 100);
        this.setShipGeometry(makeShip());
        super.setWeaponPosX(getShipGeometry().get(0).getX()+75);
        super.setWeaponPosY(getShipGeometry().get(0).getY()+25);
    }

    @Override
    public ArrayList<Triangle> makeShip() {
        double x = 50;
        double y = 200;
        double x2 = 15;
        double y2 = 15;
        ArrayList<Triangle> temp = new ArrayList<>();
        temp.add(new Triangle(x,y,x2,y2, 0, true, false, true, TriangleOrientation.UpperRight));
        temp.add(new Triangle(x,y+y2,x2,y2, 0, true, false, true, TriangleOrientation.UpperRight));
        temp.add(new Triangle(x,y+y2,x2,y2, 0, true, false, true, TriangleOrientation.LowerLeft));
        for (int i=1;i<4;i++){
            temp.add(new Triangle(x+(i*x2),y+y2,x2,y2, 0, true, false, true, TriangleOrientation.UpperRight));
            temp.add(new Triangle(x+(i*x2),y+y2,x2,y2, 0, true, false, true, TriangleOrientation.LowerLeft));
        }
        temp.add(new Triangle(x+(4*x2),y+y2,x2,y2, 0, true, false, true, TriangleOrientation.UpperRight));
        return temp;
    }

    public void updateWeaponPos() {
        setWeaponPosX(getShipGeometry().get(0).getX()+80);
        setWeaponPosY(getShipGeometry().get(0).getY()+25);
    }
}
