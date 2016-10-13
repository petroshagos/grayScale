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
        if (terrain.isFilled()) {
            for (int i = 0; i < terrain.getXPoints().length; i++) {
                System.out.println("xpoint: " + i + ": " + terrain.getXPoints()[i]);
            }
            for (int i = 0; i < terrain.getYPoints().length; i++) {
                System.out.println("ypoint: "+i+": "+terrain.getYPoints()[i]);   
            }
            System.out.println("nrofpoints: "+terrain.getNrOfPoints());
            gc.fillPolygon(terrain.getXPoints(), terrain.getYPoints(), terrain.getNrOfPoints());
        }
        else {
            gc.strokePolygon(terrain.getXPoints(), terrain.getYPoints(), terrain.getNrOfPoints());
        }
    }
}
