package view.FX;

import model.Shape.Shape;
import model.Ship;
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
        for (Shape s: ship.getShipGeometry()) {
            shipGeometry.add(new TriangleFX(themeColor, s));
        }
    }

    public ArrayList<ShapeFX> getShipGeometry() {
        return shipGeometry;
    }
}
