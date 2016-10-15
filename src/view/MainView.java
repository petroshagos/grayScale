package view;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import model.GameModel;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class MainView extends Application {

    private Canvas canvas;
    AnimationTimer timer;
    GameModel model;
    GameGUI view;


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

        Group root = new Group();
        Scene scene = new Scene(root, 800, 400);
        canvas = new Canvas();
        // automatically resize the canvas when the stage/scene is resized
        canvas.widthProperty().bind(scene.widthProperty());
        canvas.heightProperty().bind(scene.heightProperty());
        root.getChildren().add(canvas);
        MenuBarTop menuBar = new MenuBarTop(view);
        stage.setTitle("grayScale");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
        menuBar.start(stage);
        root.getChildren().add(menuBar.getHBox());
        stage.show();
        timer = new SpaceTimer();
        timer.start();

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
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(view.getThemeColor().getColor(1));
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
            view.paint(gc); //new paint
        }
    }
}
