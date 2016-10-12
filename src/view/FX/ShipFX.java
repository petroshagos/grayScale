package view.FX;

import model.Ship;
import model.Shape.Triangle;
import view.ThemeColor;

import java.util.ArrayList;

/**
 * @author Petros Hagos & Dag Oldenburg.
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
