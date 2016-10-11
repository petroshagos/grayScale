package view;

import model.Projectile;
import model.Rectangle;

import java.util.ArrayList;

/**
 * Created by Petros on 2016-10-09.
 */
public class ProjectileFX {

    private ThemeColor themeColor;
    private ArrayList<ShapeFX> projectile;

    public ProjectileFX(ThemeColor themeColor, Projectile projectile) {
        for (Rectangle r: projectile.getProjectile())
            this.projectile.add(new RectangleFX(themeColor, r));
    }

    public ArrayList<ShapeFX> getProjectile() {
        return projectile;
    }

    public void setThemeColor(ThemeColor themeColor) {
        this.themeColor = themeColor;
    }

    public ThemeColor getThemeColor() {
        return themeColor;
    }
}
