
import controller.GameController;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.*;
import java.util.LinkedList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.EnemyShip;
import model.GameModel;
import view.BottomHUD;
import view.GameGUI;
import view.HighScore;
import view.MenuBarTop;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class Main extends Application {

    private Canvas canvas;
    private BorderPane borderPane;
    private AnimationTimer timer;
    private GameModel model;
    private GameGUI view;
    private GameController controller;
    private MenuBarTop menuBar;
    private BottomHUD bottomHUD;
    private LocalTime time;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public MenuBarTop menuBarTop() {
        return menuBar;
    }

    public BottomHUD getBottomHUD() {
        return bottomHUD;
    }

    @Override
    public void start(Stage stage) throws Exception {
        model = new GameModel();
        view = new GameGUI(model);
        controller = view.getGameController();
        borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(view.getThemeColor().getColor(0),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        borderPane.setPadding(new Insets(0, 0, 0, 0));
        canvas = new Canvas(800, 400);
        borderPane.getChildren().add(canvas);
        HighScore highScore = new HighScore();
        highScore.setPadding(new Insets(10, 200, 50, 200));
        highScore.getVBox().setVisible(false);
        menuBar = new MenuBarTop(view, highScore.getVBox());
        bottomHUD = new BottomHUD(model, view.getThemeColor());
        bottomHUD.setPadding(new Insets(0, 0, 0, 0));
        bottomHUD.setMaxHeight(20);
        borderPane.setTop(menuBar.getMenuBar());
        borderPane.setCenter(highScore.getVBox());
        borderPane.getChildren().add(view.getText());
        borderPane.setBottom(bottomHUD.getGridPane());
        stage.setTitle("grayScale");
        Scene scene = new Scene(borderPane, 800, 400);
        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        timer = new SpaceTimer();
        timer.start();
        EnemySpawnThread esThread = new EnemySpawnThread(model.getEnemies(), view);
        esThread.start();

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent me) {
                model.getPlayer().addScore(100);
                bottomHUD.updateHUD(model);
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
            if (previousNs == 0) {
                previousNs = nowNs;
            }
            time = LocalTime.now();
            model.move(nowNs - previousNs); // elapsed time
            previousNs = nowNs;
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(view.getThemeColor().getColor(1));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            controller.updateView();
            view.paint(gc); //new paint
        }
    }

    class EnemySpawnThread extends Thread {

        LinkedList<EnemyShip> enemies;
        GameGUI view;
        int wave = 1;

        public EnemySpawnThread(LinkedList<EnemyShip> enemies, GameGUI view) {
            this.enemies = enemies;
            this.view = view;
        }

        @Override
        public void run() {
            Random rand = new Random();
            double yRange;
            int hpRange = rand.nextInt(5) + 4;
            LocalTime time = LocalTime.now();
            LocalTime comparisonTime = time.minusSeconds(5);
            while (true) { //timeOut.compareTo(LocalTime.now()) > 0
                time = LocalTime.now();
                if (time.compareTo(comparisonTime) > 0) {
                    comparisonTime = time.plusMinutes(1);
                    view.updateWaveText(wave);
                    System.out.println("wave: " + wave++);
                    try {
                        Thread.sleep(5000);
                        view.updateWaveText();
                    } catch (InterruptedException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                yRange = rand.nextInt(300) + 25;
                EnemyShip enemyShip = new EnemyShip(800, yRange, hpRange * wave, false);
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
