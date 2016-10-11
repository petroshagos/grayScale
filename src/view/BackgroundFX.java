package view;

import model.Background;
import model.Rectangle;
import model.Terrain;

import java.util.ArrayList;

/**
 * Created by Petros on 2016-10-09.
 */
public class BackgroundFX {

    private ThemeColor themeColor;
    private ArrayList<ShapeFX> bgFront, bgBack, terrainList;

    public BackgroundFX(ThemeColor themeColor, Background background) {
        for (Rectangle r: background.getBgFront()) {
            this.bgFront.add(new RectangleFX(themeColor,r));
        }
        for (Rectangle r: background.getBgBack()) {
            this.bgBack.add(new RectangleFX(themeColor,r));
        }
        for (Terrain t: background.getTerrainList()) {
            this.bgFront.add(new TerrainFX(themeColor,t));
        }
    }

    public void setThemeColor(ThemeColor themeColor) {
        this.themeColor = themeColor;
    }

    public ArrayList<ShapeFX> getBgBack() {
        return bgBack;
    }

    public ArrayList<ShapeFX> getBgFront() {
        return bgFront;
    }

    public ArrayList<ShapeFX> getTerrainList() {
        return terrainList;
    }
}
