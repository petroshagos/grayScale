package model;

import model.Shape.Triangle;
import model.Shape.TriangleOrientation;

import java.util.ArrayList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class PlayerShip extends Ship {


    public PlayerShip() {
        super(50, 200, 100,1);
        this.setShipGeometry(makeShip(getX(), getY(), 15, 15));
        super.setWeaponPosX(getShipGeometry().get(0).getX()+75);
        super.setWeaponPosY(getShipGeometry().get(0).getY()+25);
    }

    public PlayerShip(double x, double y, int hP,int damage) {
        super(x,y,hP,damage);
        this.setShipGeometry(makeShip(x, y, 15, 15));
        super.setWeaponPosX(getShipGeometry().get(0).getX()+75);
        super.setWeaponPosY(getShipGeometry().get(0).getY()+25);
    }
    /**
     * Creates the player ship
     * @param x x value
     * @param y y value
     * @param width width
     * @param height height
     * @return 
     */
    @Override
    public ArrayList<Triangle> makeShip(double x, double y, double width, double height) {

        ArrayList<Triangle> temp = new ArrayList<>();
        temp.add(new Triangle(x,y,width,height, 0, true, false, true, TriangleOrientation.UpperRight));
        temp.add(new Triangle(x,y+height,width,height, 0, true, false, true, TriangleOrientation.UpperRight));
        temp.add(new Triangle(x,y+height,width,height, 0, true, false, true, TriangleOrientation.LowerLeft));
        for (int i=1;i<4;i++){
            temp.add(new Triangle(x+(i*width),y+height,width,height, 0, true, false, true, TriangleOrientation.UpperRight));
            temp.add(new Triangle(x+(i*width),y+height,width,height, 0, true, false, true, TriangleOrientation.LowerLeft));
        }
        temp.add(new Triangle(x+(4*width),y+height,width,height, 0, true, false, true, TriangleOrientation.UpperRight));
        return temp;
    }


}
