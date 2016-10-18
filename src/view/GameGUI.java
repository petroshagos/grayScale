package view;

import controller.GameController;
import javafx.scene.canvas.GraphicsContext;
import model.GameModel;
import view.FX.BackgroundFX;
import view.FX.ProjectileFX;
import view.FX.ShapeFX;
import view.FX.ShipFX;

import java.util.LinkedList;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Ship;

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
    private Text t = new Text();

    public GameGUI(GameModel model) {
        this.gameModel = model;
        this.gameController = new GameController(gameModel, this);
        this.themeColor = ThemeColor.THEME_GRAY;
        bgFX = new BackgroundFX(themeColor, gameModel.getBackground());
        shipFX = new ShipFX(themeColor, gameModel.getPlayer().getCurrentShip());
        t.setX(340);
        t.setY(220);
        t.setFont(new Font(40));
        t.setFill(Color.WHITE);
    }
    public void updateWaveText(int wave){
        t.setText("Wave: " + wave);
    }
    public void updateWaveText(){
        t.setText("");
    }
    public Text getText(){
        return t;
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

    public void addShipFx(Ship ship) {
        enemiesFX.add(new ShipFX(themeColor, ship));
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
        for (ShipFX s : enemiesFX) {
            for (ShapeFX sh : s.getShipGeometry()) {
                sh.paint(gc);
            }
        }
        /*            for (ShapeFX s : pFX.getFirst().getProjectileGeometry()) {
                s.paint(gc);
            }*/
    }

    public GameController getGameController() {
        return gameController;
    }
}
