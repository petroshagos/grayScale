package model;

import model.Shape.Rectangle;
import model.Shape.Shape;
import model.Shape.Terrain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class Background {

    LinkedList<Terrain> terrainList = new LinkedList<>();
    LinkedList<Rectangle> bgFront = new LinkedList<>();
    LinkedList<Rectangle> bgBack = new LinkedList<>();

    public Background() {
        double[] tempX = makeTerrainPointsX(0);
        double[] tempY = makeTerrainPointsY(350, tempX);
        terrainList.add(new Terrain(0,350,0,0, 0, true, false, false, tempX, tempY));
        fill();
        bgFront = makeBG(0,0,3,2);
        bgBack = makeBG(0,0,2,3);
        setTerrainVelocity(-60,0);
        setBgBackVelocity(-6,0);
        setBgFrontVelocity(-9,0);
    }

    public double getLastTerrainPointX() {
        if (terrainList.size()>0) {
            Terrain temp = terrainList.get(terrainList.size() - 1);
            return temp.getLastPoint()[0];
        }
        return 0;
    }

    public double getLastTerrainPointY() {
        if (terrainList.size()>0) {
            Terrain temp = terrainList.get(terrainList.size() - 1);
            return temp.getLastPoint()[1];
        }
        return 350;
    }

    public LinkedList makeBG(double x, double y, double size, int color) {
        LinkedList<Shape> temp = new LinkedList<>();
        int i = 0;
        int x2;
        Random random = new Random();
        temp.add(new Rectangle(x,y,size,size, color, true, false, false));
        while (i<1000) {
            x2=(random.nextInt(10 - 6 + 1) + 6);
            y=(random.nextInt(400 - 1 + 1) + 1);
            temp.add(new Rectangle(x+x2,y,size,size, color, true, false, false));
            x+=x2;
            i++;
        }
        return temp;
    }

    public double[] makeTerrainPointsX(double x) {
        ArrayList<Double> coordinatesX = new ArrayList<>();
        Random random = new Random();
        double xMax = x;
        double randX;
        coordinatesX.add(x);
        while (xMax < (x + 500)) {
            randX = random.nextDouble()*(30-10)+10;
            coordinatesX.add(xMax+randX);
            xMax+=randX;
        }
        coordinatesX.add(coordinatesX.get(coordinatesX.size()-1));
        coordinatesX.add(x);
        double[] temp = new double[coordinatesX.size()];
        for (int i = 0;i<coordinatesX.size();i++) {
            temp[i]=coordinatesX.get(i);
        }
        coordinatesX.clear();
        return temp;
    }

    public double[] makeTerrainPointsY(double y, double[] xPoints) {
        ArrayList<Double> coordinatesY = new ArrayList<>();
        Random random = new Random();
        coordinatesY.add(y);
        for (int i = 0; i<xPoints.length-3;i++){
            coordinatesY.add(400-(random.nextDouble()*(70-30)+30));
        }
        coordinatesY.add(400.0);
        coordinatesY.add(400.0);
        double[] temp = new double[coordinatesY.size()];
        for (int i = 0;i<coordinatesY.size();i++) {
            temp[i]=coordinatesY.get(i);
        }
        coordinatesY.clear();
        return temp;
    }

    public LinkedList<Rectangle> getBgFront() {
        return bgFront;
    }

    public LinkedList<Rectangle> getBgBack() {
        return bgBack;
    }

    public void setBgBackVelocity(double dx, double dy) {
        for (Shape s: bgBack)
            s.setVelocity(dx, dy);
    }

    public void setBgFrontVelocity(double dx, double dy) {
        for (Shape s: bgFront)
            s.setVelocity(dx, dy);
    }

    public void setTerrainVelocity(double dx, double dy) {
        for (Shape s: terrainList)
            s.setVelocity(dx, dy);
    }

    public LinkedList<Terrain> getTerrainList() {
        return terrainList;
    }

    private void fill() {
        double[] tempX;
        double[] tempY;
        for ( int i = 0; i<10; i++ ) {
            tempX = makeTerrainPointsX(getLastTerrainPointX()-1);
            tempY = makeTerrainPointsY(getLastTerrainPointY(), tempX);
            terrainList.add(new Terrain(getLastTerrainPointX(), getLastTerrainPointY(),0,0,
                    0, true, false, false, tempX, tempY));

        }
        setTerrainVelocity(-60,0);
    }

}
