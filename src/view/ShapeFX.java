package view;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Petros on 2016-10-11.
 */
public abstract class ShapeFX {

    private ThemeColor themeColor;

    protected ShapeFX(ThemeColor themeColor){
        this.themeColor = themeColor;
    }

    public ThemeColor getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(ThemeColor themeColor) {
        this.themeColor = themeColor;
    }

    abstract void paint(GraphicsContext gc);

}
