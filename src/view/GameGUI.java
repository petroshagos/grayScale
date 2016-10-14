
package view;

import controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import model.GameModel;
import model.Projectile;
import model.Ship;
import view.FX.BackgroundFX;
import view.FX.ShapeFX;
import view.FX.ShipFX;

import java.util.LinkedList;

/**
 *
 * @author Petros Hagos & Dag Oldenburg
 */
public class GameGUI extends Application {

    private ThemeColor themeColor;
    private GameModel gameModel;
    private GameController gameController;
    private BackgroundFX bgFX;
    private LinkedList<ShipFX> shipFX = new LinkedList<>();
    private LinkedList<Projectile> pFX;
    private Canvas canvas;
    private AnimationTimer timer;
    private boolean timerIsOn;

   /*public GameGUI(GameModel model) {
        this.gameModel = model;
        this.gameController = new GameController(gameModel, this);
        this.themeColor = ThemeColor.THEME_GRAY;
        }
        this.bgFX = new BackgroundFX(themeColor, model.getBackground());
        for (Ship s: model.getShips()) {
            this.shipFX.add(new ShipFX(themeColor, s));
        }
    }*/

    protected class SpaceTimer extends AnimationTimer {

        private long previousNs = 0;

        /**
         * This method deals with drawing the world. You do not have to change
         * this code, except for handling "game over" (see comment below).
         *
         * @param nowNs
         */
        @Override
        public void handle(long nowNs) {
            if (previousNs == 0) {
                previousNs = nowNs;
            }

            // move the objects in the world
            gameModel.move(nowNs - previousNs); // elapsed time
            // save the new timestamp, for the next cycle
            previousNs = nowNs;

            GraphicsContext gc = canvas.getGraphicsContext2D();

            // paint the background
            gc.setFill(themeColor.getColor(1));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

            // paint the shapes
            for (ShapeFX s : bgFX.getBgBack()) {
                s.paint(gc);
            }

            for (ShapeFX s : bgFX.getBgFront()) {
                s.paint(gc);
            }
        }
    }

    // This code initializes the graphics. You do not have to (should not)
    // change this code.
    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 800, 400);
        this.gameModel = new GameModel();
        for (Ship s: gameModel.getPlayer().getShips()) {
            this.shipFX.add(new ShipFX(themeColor, s));
        }
        this.themeColor = ThemeColor.THEME_GRAY;
        this.bgFX = new BackgroundFX(themeColor, gameModel.getBackground());

        canvas = new Canvas();
        // automatically resize the canvas when the stage/scene is resized
        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());
        root.getChildren().add(canvas);
        //MenuBarTop menuBar = new MenuBarTop(bgFX,themeColor,this);
        stage.setTitle("grayScale");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        //menuBar.start(stage);
        //root.getChildren().add(menuBar.getHBox());
        stage.show();
            timer = new SpaceTimer();
            timer.start();
            timerIsOn = true;

            /*canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                    new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent me) {
                            if (timerIsOn) {
                                gameModel.getBackground().setBgBackVelocity(0,0);
                                gameModel.getBackground().setBgFrontVelocity(0,0);
                                timer.stop();
                                timerIsOn = !timerIsOn;
                            }
                            else {
                                gameModel.getBackground().setBgBackVelocity(-6,0);
                                gameModel.getBackground().setBgFrontVelocity(-9,0);
                                timer.start();
                                timerIsOn = !timerIsOn;
                            }
                        }
                    }
            );*/


    }

    public ThemeColor getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(ThemeColor themeColor) {
        this.themeColor = themeColor;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
