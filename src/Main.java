import controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
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
        HighScore highScore = new HighScore();
        highScore.setPadding(new Insets(10, 200, 50, 200));
        highScore.getVBox().setVisible(true);
        view.startScreen();
        controller = view.getGameController();
        borderPane = new BorderPane();
        borderPane = view.makeBorderPane(model);
        stage.setTitle("grayScale");
        Scene scene = new Scene(borderPane, 800, 440);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        timer = new SpaceTimer();
        timer.start();

        scene.addEventHandler(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent ke) {
                        controller.handleKeyPress(ke);
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

            model.move(nowNs - previousNs); // elapsed time
            previousNs = nowNs;
            GraphicsContext gc = view.getCanvas().getGraphicsContext2D();
            gc.setFill(view.getThemeColor().getColor(1));
            gc.fillRect(0, 0, view.getCanvas().getWidth(), view.getCanvas().getHeight());
            //controller.updateView();
            view.paint(gc); //new paint
        }
    }
}
