package view.FX;

import javafx.scene.canvas.GraphicsContext;
import model.Shape.Terrain;
import view.ThemeColor;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class TerrainFX extends ShapeFX {

    private Terrain terrain;

    public TerrainFX(ThemeColor themeColor, Terrain terrain) {
        super(themeColor);
        this.terrain = terrain;
    }

    @Override
    public void paint(GraphicsContext gc) {
        gc.setFill(super.getThemeColor().getColor(terrain.getColor()));
        gc.setStroke(super.getThemeColor().getColor(terrain.getColor()));
        System.out.println("hej");
        if (terrain.isFilled()) {
            gc.fillPolygon(terrain.getXPoints(), terrain.getYPoints(), terrain.getNrOfPoints());
        }
        else {
            gc.strokePolygon(terrain.getXPoints(), terrain.getYPoints(), terrain.getNrOfPoints());
        }
    }
}
