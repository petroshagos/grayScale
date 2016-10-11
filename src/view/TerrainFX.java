package view;

import javafx.scene.canvas.GraphicsContext;
import model.Terrain;

/**
 * Created by Petros on 2016-10-11.
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
        if (terrain.isFilled()) {
            gc.fillPolygon(terrain.getXPoints(), terrain.getYPoints(), terrain.getNrOfPoints());
        }
        else {
            gc.strokePolygon(terrain.getXPoints(), terrain.getYPoints(), terrain.getNrOfPoints());
        }
    }
}
