package view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.FX.BackgroundFX;
import view.FX.ShapeFX;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class MenuBarTop extends Application {
    private HBox topBar = new HBox();
    
    private BorderPane border = new BorderPane();
    MenuBar menuBar = new MenuBar();
    private BackgroundFX bgFX;
    private ThemeColor themeColor;
    private Canvas canvas;
    private GameGUI game;

    public MenuBarTop(BackgroundFX bgFX, ThemeColor themeColor, GameGUI game) {
        this.bgFX = bgFX;
        this.themeColor = themeColor;
        this.game = game;
    }

    @Override
    public void start(Stage primaryStage) {
        Menu fileMenu = new Menu("Menu");
        MenuItem newGame = new MenuItem("NEW GAME");
        fileMenu.getItems().add(newGame);
        MenuItem pause = new MenuItem("PAUSE/UNPAUSE");
        fileMenu.getItems().add(pause);
        MenuItem highScore = new MenuItem("HIGHSCORE");
        fileMenu.getItems().add(highScore);
        Menu color = new Menu("THEME");
        fileMenu.getItems().add(color);
        MenuItem gray = new MenuItem("GRAY");
        color.getItems().add(gray);
        MenuItem green = new MenuItem("GREEN");
        color.getItems().add(green);
        MenuItem red = new MenuItem("RED");
        color.getItems().add(red);
        MenuItem blue = new MenuItem("BLUE");
        color.getItems().add(blue);
        MenuItem quit = new MenuItem("QUIT");
        fileMenu.getItems().add(quit);
        menuBar.getMenus().addAll(fileMenu);
        topBar.getChildren().add(menuBar);

        EventHandler colorHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuItem source = (MenuItem) event.getSource();
                System.out.println(source.getText());
                if (source.getText().equals("GRAY")) {
                    game.setThemeColor(ThemeColor.THEME_GRAY);
                    changeColor(game.getThemeColor(), bgFX);
                } else if (source.getText().equals("RED")) {
                    game.setThemeColor(ThemeColor.THEME_RED);
                    changeColor(game.getThemeColor(), bgFX);
                } else if (source.getText().equals("BLUE")) {
                    game.setThemeColor(ThemeColor.THEME_BLUE);
                    changeColor(game.getThemeColor(), bgFX);
                }
            }
        };
        gray.setOnAction(colorHandler);
        red.setOnAction(colorHandler);
        green.setOnAction(colorHandler);
        blue.setOnAction(colorHandler);

        EventHandler gameStateHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuItem source = (MenuItem) event.getSource();
                System.out.println(source.getText());
                boolean paused = false;
                if (source.getText().equals("QUIT")) {
                    System.exit(0);
                }
                else if (source.getText().equals("PAUSE/UNPAUSE")) {
                    /*Thread pauseThread = new Pause();
                    pauseThread.start();
                    try {
                        pauseThread.join();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }*/
                }
            }
        };
        quit.setOnAction(gameStateHandler);
        //pause.setOnAction(gameStateHandler);
    }

    public void changeColor(ThemeColor themeColor, BackgroundFX bgFx) {
        for (ShapeFX s : bgFX.getBgBack()) {
            s.setThemeColor(themeColor);
        }
        for (ShapeFX s : bgFX.getBgFront()) {
            s.setThemeColor(themeColor);
        }
        for (ShapeFX s : bgFX.getTerrainList()) {
            s.setThemeColor(themeColor);
        }
    }

        class Pause extends Thread {
        boolean paused = true;

        public void run() {
            EventHandler pauseHandler = new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    MenuItem source = (MenuItem) event.getSource();
                    System.out.println(source.getText());
                    System.out.println("skriv");
                    if (source.getText().equals("PAUSE/UNPAUSE")) {
                        paused = false;
                    }
                }
            };
            /*while (paused) {
                try {
                    System.out.println("hej");
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }*/
            return;
        }
    }
        
    public HBox getHBox() {
        return topBar;
    }
    public MenuBar getMenuBar() {
        return menuBar;
    }

}
