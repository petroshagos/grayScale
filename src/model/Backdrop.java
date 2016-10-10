package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by Petros on 2016-10-09.
 */
public class Backdrop {

    LinkedList<Shape> terrainList, bgFront,bgBack;

    public Backdrop() {
        double[] tempX = makeTerrainXPoints(0);
        double[] tempY = makeTerrainYPoints(50, tempX);
        terrainList.add(new Terrain(0,350, 0, true, false, false, tempX, tempY));
        bgFront = makeBG(0,0,3,2,-9);
        bgBack = makeBG(0,0,2,3,-6);
    }


    private void fill() {

    }

    public LinkedList makeBG(double x, double y, double size, int color, double velocity) {
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
        for (Shape t: temp) {
            t.setVelocity(-velocity,0);
        }
        return temp;
    }

    public double[] makeTerrainXPoints(double x) {
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

    public double[] makeTerrainYPoints(double y, double[] xPoints) {
        ArrayList<Double> coordinatesY = new ArrayList<>();
        Random random = new Random();
        coordinatesY.add(y);
        for (int i = 0; i<xPoints.length-1;i++){
            coordinatesY.add(400-(random.nextDouble()*(70-30)+30));
        }
        coordinatesY.add(400.0);
        coordinatesY.add(400.0);
        double[] temp = new double[coordinatesY.size()];
        for (int i = 0;i<coordinatesY.size();i++) {
            temp[i]=coordinatesY.get(i);
        }
        return temp;
    }

}
