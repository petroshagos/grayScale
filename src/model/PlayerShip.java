package model;

import model.Shape.Triangle;
import model.Shape.TriangleOrientation;

import java.util.ArrayList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class PlayerShip extends Ship {

    public PlayerShip() {
        super();
        this.setShipGeometry(makeShip());
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
        temp.add(new Triangle(x,y+y2,x2,y2, 0, true, false, true, TriangleOrientation.UpperLeft));
        for (int i=1;i<4;i++){
            temp.add(new Triangle(x+(i*x2)-1,y+y2,x2,y2, 0, true, false, true, TriangleOrientation.UpperRight));
            temp.add(new Triangle(x+(i*x2)-1,y+y2,x2,y2, 0, true, false, true, TriangleOrientation.UpperLeft));
        }
        temp.add(new Triangle(x+(4*x2)-1,y+y2,x2,y2, 0, true, false, true, TriangleOrientation.UpperRight));
        return temp;
    }
}
