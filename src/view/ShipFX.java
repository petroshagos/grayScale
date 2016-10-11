package view;

import model.Ship;
import model.Triangle;

import java.util.ArrayList;

/**
 * Created by Petros on 2016-10-09.
 */
public class ShipFX {

    private ThemeColor themeColor;
    private Ship ship;
    private ArrayList<ShapeFX> shipGeometry = new ArrayList<>();

    public ShipFX(ThemeColor themeColor, Ship ship) {
        this.themeColor = themeColor;
        this.ship = ship;
        for (Triangle r: ship.getShipGeometry()) {
            shipGeometry.add(new TriangleFX(themeColor, r));
        }
    }

    public ArrayList<ShapeFX> getShipGeometry() {
        return shipGeometry;
    }
}
