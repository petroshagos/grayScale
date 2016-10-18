import controller.GameController;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.GameModel;
import view.GameGUI;
import view.HighScore;


/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class Main extends Application {

    private BorderPane borderPane;
    private AnimationTimer timer;
    private GameModel model;
    private GameGUI view;
    private GameController controller;
    private long pausCounter = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
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
            if (model.getNow() == 0) {
                model.setNow(nowNs);
            }
            if (!model.isPaused()) {
                model.move((nowNs-pausCounter) - model.getNow()); // elapsed time
                model.setNow(nowNs-pausCounter);
            }
            else {
                pausCounter = nowNs - model.getNow();
            }
            GraphicsContext gc = view.getCanvas().getGraphicsContext2D();
            gc.setFill(view.getThemeColor().getColor(1));
            gc.fillRect(0, 0, view.getCanvas().getWidth(), view.getCanvas().getHeight());
            view.paint(gc); //new paint
        }
    }
}
