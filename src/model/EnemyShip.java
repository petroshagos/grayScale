package model;

import model.Shape.Triangle;
import model.Shape.TriangleOrientation;

import java.util.ArrayList;
import model.Shape.Shape;

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

    }

    @Override
    public ArrayList<Triangle> makeShip() {
        /*ArrayList<Triangle> temp = new ArrayList<>();
        for (int i=0;i<3;i++){
            for (int j=0;j<2;j++){
                temp.add(new Triangle(super.getX()+6*i,super.getY()+6*j,20,20,0,true,false,true, TriangleOrientation.UpperLeft));
            }
        }
        return temp;
    }*/
        double x2 = 15;
        double y2 = 15;
        ArrayList<Triangle> temp = new ArrayList<>();
        for (int i=1;i<2;i++){
            temp.add(new Triangle(x+(i*x2),y+y2,x2,y2, 0, true, false, true, TriangleOrientation.UpperRight));
            temp.add(new Triangle(x+(i*x2),y+y2,x2,y2, 0, true, false, true, TriangleOrientation.LowerLeft));
            
        }
        for(Shape s : temp){
            s.setVelocity(-40.0, 0);
        }
        return temp;
    }
}
