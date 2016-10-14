package view.FX;

import javafx.scene.canvas.GraphicsContext;
import view.ThemeColor;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public abstract class ShapeFX {

    private ThemeColor themeColor;

    protected ShapeFX(ThemeColor themeColor){
        this.themeColor = themeColor;
    }

    public ThemeColor getThemeColor() {
        return this.themeColor;
    }

    public void setThemeColor(ThemeColor themeColor) {
        this.themeColor = themeColor;
    }

    abstract public void paint(GraphicsContext gc);

}
