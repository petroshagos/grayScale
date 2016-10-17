
package view;

import controller.GameController;
import javafx.scene.canvas.GraphicsContext;
import model.GameModel;
import view.FX.BackgroundFX;
import view.FX.ProjectileFX;
import view.FX.ShapeFX;
import view.FX.ShipFX;

import java.util.LinkedList;

/**
 *
 * @author Petros Hagos & Dag Oldenburg
 */
public class GameGUI {

    private ThemeColor themeColor;
    private GameModel gameModel;
    private GameController gameController;
    private BackgroundFX bgFX;
    private ShipFX shipFX;
    private LinkedList<ShipFX> enemiesFX = new LinkedList<>();
    private LinkedList<ProjectileFX> pFX = new LinkedList<>();
    private boolean timerIsOn;

   public GameGUI(GameModel model) {
       this.gameModel = model;
       this.gameController = new GameController(gameModel, this);
       this.themeColor = ThemeColor.THEME_GRAY;
       bgFX = new BackgroundFX(themeColor, gameModel.getBackground());
       shipFX = new ShipFX(themeColor, gameModel.getPlayer().getCurrentShip());
    }

    public ThemeColor getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(ThemeColor themeColor) {
        this.themeColor = themeColor;
    }

    public ShipFX getShipFX() {
        return shipFX;
    }

    public BackgroundFX getBgFX() {
        return bgFX;
    }

    public void paint(GraphicsContext gc) {
        for (ShapeFX s : bgFX.getBgBack()) {
            s.paint(gc);
        }

        for (ShapeFX s : bgFX.getBgFront()) {
            s.paint(gc);
        }

        for (ShapeFX s : shipFX.getShipGeometry()) {
            s.paint(gc);
        }
/*            for (ShapeFX s : pFX.getFirst().getProjectileGeometry()) {
                s.paint(gc);
            }*/
    }

    public GameController getGameController() {
        return gameController;
    }
}
