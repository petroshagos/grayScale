package view.FX;

import model.Shape.Triangle;
import model.Ship;
import view.ThemeColor;

import java.util.ArrayList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class ShipFX {

    private ThemeColor themeColor;
    private Ship ship;
    private ArrayList<TriangleFX> shipGeometry = new ArrayList<>();

    public ShipFX(ThemeColor themeColor, Ship ship) {
        this.themeColor = themeColor;
        this.ship = ship;
        for (Triangle t: ship.getShipGeometry()) {
            shipGeometry.add(new TriangleFX(themeColor, t));
        }
        System.out.println(themeColor);
    }

    public ArrayList<TriangleFX> getShipGeometry() {
        return shipGeometry;
    }
}
