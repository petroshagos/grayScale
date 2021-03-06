package view.FX;

import model.Projectile;
import model.Shape.Rectangle;
import view.ThemeColor;

import java.util.ArrayList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class ProjectileFX {

    private ThemeColor themeColor;
    private Projectile projectile;
    private ArrayList<ShapeFX> projectileGeometry = new ArrayList<>();

    public ProjectileFX(ThemeColor themeColor, Projectile projectile) {
        this.themeColor = themeColor;
        this.projectile = projectile;
        for (Rectangle r: projectile.getProjectile()) {
            this.projectileGeometry.add(new RectangleFX(themeColor, r));
        }
    }

    public ArrayList<ShapeFX> getProjectileGeometry() {
        return projectileGeometry;
    }

    public void setThemeColor(ThemeColor themeColor) {
        this.themeColor = themeColor;
    }

    public ThemeColor getThemeColor() {
        return themeColor;
    }
}
