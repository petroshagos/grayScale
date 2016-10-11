package view.FX;

import model.Background;
import model.Shape.Rectangle;
import model.Shape.Terrain;
import view.ThemeColor;

import java.util.ArrayList;

/**
 * Created by Petros on 2016-10-09.
 */
public class BackgroundFX {

    private ThemeColor themeColor;
    private Background background;
    private ArrayList<ShapeFX> bgFront = new ArrayList<>();
    private ArrayList<ShapeFX> bgBack = new ArrayList<>();
    private ArrayList<ShapeFX> terrainList = new ArrayList<>();

    public BackgroundFX(ThemeColor themeColor, Background background) {
        this.themeColor = themeColor;
        this.background = background;
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
