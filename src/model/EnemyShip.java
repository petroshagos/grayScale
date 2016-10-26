package model;

import java.time.LocalTime;
import model.Shape.Shape;
import model.Shape.Triangle;
import model.Shape.TriangleOrientation;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class EnemyShip extends Ship {
    private LocalTime time = LocalTime.now();
    private boolean isOutOfBounds;
    public EnemyShip(double x,double y,int healthPoints, boolean isOutOfBounds,int damage) {
        super(x, y, healthPoints,damage);
        this.isOutOfBounds = isOutOfBounds;
        this.setShipGeometry(makeShip(x,y,15,15));
        super.setWeaponPosX(getShipGeometry().get(0).getX());
        super.setWeaponPosY(getShipGeometry().get(0).getY()-7);
        
        super.setWeaponPosX(getShipGeometry().get(0).getX()+75);
        super.setWeaponPosY(getShipGeometry().get(0).getY()-25);
    }

    @Override
    public ArrayList<Triangle> makeShip(double x, double y, double width, double height) {
        ArrayList<Triangle> temp = new ArrayList<>();
        temp.add(new Triangle(x,y,width,height, 2, true, false, true, TriangleOrientation.UpperRight));
        temp.add(new Triangle(x,y,width,height, 0, true, false, true, TriangleOrientation.LowerLeft));
        temp.add(new Triangle(x,y+height,width,height, 2, true, false, true, TriangleOrientation.LowerRight));
        temp.add(new Triangle(x+width,y,width,height, 0, true, false, true, TriangleOrientation.UpperRight));
        temp.add(new Triangle(x+width,y,width,height, 0, true, false, true, TriangleOrientation.LowerLeft));
        temp.add(new Triangle(x+width*2,y,width,height, 0, true, false, true, TriangleOrientation.UpperRight));
        temp.add(new Triangle(x+width*2,y,width,height, 0, true, false, true, TriangleOrientation.LowerLeft));
        temp.add(new Triangle(x+width*2,y-height,width,height, 0, true, false, true, TriangleOrientation.UpperLeft));
        for(Shape s : temp){
            s.setVelocity(-80.0, 0);
        }
        return temp;
    }
    
    public LocalTime getLastShot(){
        return time;
    }
    public void setLastShot(){
        time = LocalTime.now();
    }

}