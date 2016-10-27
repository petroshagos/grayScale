import controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.EnemyShip;
import model.GameModel;
import view.GameGUI;

import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Random;


/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class Main extends Application {

    private BorderPane borderPane;
    private AnimationTimer timer;
    private GameModel model;
    private GameGUI view;
    private GameController controller;
    private long pauseCounter = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        model = new GameModel();
        view = new GameGUI(model);
        controller = view.getGameController();
        view.startScreen();
        borderPane = view.makeBorderPane(model);
        stage.setTitle("grayScale");
        Scene scene = new Scene(borderPane, 800, 440);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        timer = new SpaceTimer();
        timer.start();
        EnemySpawnThread esThread = new EnemySpawnThread(model.getEnemyShips(), view);
        esThread.start();

        scene.addEventHandler(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent ke) {
                        controller.handleKeyPress(ke);
                    }
                }
            );
        scene.addEventHandler(KeyEvent.KEY_RELEASED,
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent ke) {
                        controller.handleKeyRelease(ke);
                    }
                }
        );
    }

    private class SpaceTimer extends AnimationTimer {

        private long previousNs = 0;

        /**
         * This method deals with drawing the world. You do not have to change
         * this code, except for handling "game over" (see comment below).
         *
         * @param nowNs
         */
        @Override
        public void handle(long nowNs) {
            if (model.getNow() == 0) {
                model.setNow(nowNs);
            }
            if (!model.isPaused()) {
                model.move((nowNs- pauseCounter) - model.getNow()); // elapsed time
                model.setNow(nowNs- pauseCounter);
            }
            else {
                pauseCounter = nowNs - model.getNow();
            }
            GraphicsContext gc = view.getCanvas().getGraphicsContext2D();
            gc.setFill(view.getThemeColor().getColor(1));
            gc.fillRect(0, 0, view.getCanvas().getWidth(), view.getCanvas().getHeight());
            model.handleCollisions();
            model.updateObjectsOnScreen();
            model.constrainPlayerShip();
            model.updateActiveObjects();
            model.updateGame();
            model.aiShoot();
            view.updateHUD(model);
            view.updateProjectiles(model);
            view.updateEnemies(model);
            view.updateBackground(model);
            view.updateObjectsOnScreen(model);
            view.updatePowerUps(model);
            view.paint(gc); //new paint

        }
    }

    class EnemySpawnThread extends Thread {

        LinkedList<EnemyShip> enemies;
        GameGUI view;
        int wave = 0;

        public EnemySpawnThread(LinkedList<EnemyShip> enemies, GameGUI view) {
            this.enemies = enemies;
            this.view = view;
        }

        public int getWave() {
            return wave;
        }

        @Override
        public void run() {
            Random rand = new Random();
            double yRange;
            int hpRange = rand.nextInt(4) + 2;
            LocalTime time = LocalTime.now();
            LocalTime comparisonTime = time.minusSeconds(5);
            while (true) { //timeOut.compareTo(LocalTime.now()) > 0
                if (!model.isPaused()) {
                    if (model.isGameOver()) {
                        wave = 0;
                    }
                    time = LocalTime.now();
                    if (time.compareTo(comparisonTime) > 0) {
                        comparisonTime = time.plusMinutes(1);
                        //view.updateWaveText(wave);
                        System.out.println("wave: " + ++wave);
                        try {
                            Thread.sleep(5000);
                            //view.updateWaveText();
                        } catch (InterruptedException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    yRange = rand.nextInt(300) + 25;
                    EnemyShip enemyShip = new EnemyShip(850, yRange, hpRange * wave, false,1*wave);
                    enemies.add(enemyShip);
                    view.addShipFx(enemyShip);
                    try {
                        Thread.sleep(rand.nextInt(10000 / wave) + 2000 / wave);
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            }
        }
    }
}
