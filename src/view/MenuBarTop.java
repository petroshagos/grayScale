package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import view.FX.BackgroundFX;
import view.FX.ShapeFX;
import view.FX.ShipFX;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class MenuBarTop extends MenuBar {
    private HBox topBar = new HBox();
    private MenuBar menuBar = new MenuBar();
    private BackgroundFX bgFX;
    private ThemeColor themeColor;
    private Canvas canvas;
    private GameGUI view;
    private ShipFX shipFX;

    public MenuBarTop(GameGUI view) {
        this.view = view;
        this.bgFX = view.getBgFX();
        this.themeColor = view.getThemeColor();
        this.shipFX = view.getShipFX();
        this.themeColor = view.getThemeColor();
        Menu fileMenu = new Menu("Menu");
        MenuItem newGame = new MenuItem("New Game");
        fileMenu.getItems().add(newGame);
        MenuItem pause = new MenuItem("Pause/Play");
        fileMenu.getItems().add(pause);
        MenuItem highScore = new MenuItem("High score");
        fileMenu.getItems().add(highScore);
        Menu color = new Menu("Theme");
        fileMenu.getItems().add(color);
        for (ThemeColor tc : ThemeColor.values()) {
            color.getItems().add(new MenuItem(tc.getName()));
        }
        MenuItem quit = new MenuItem("Quit");
        fileMenu.getItems().add(quit);
        menuBar.getMenus().addAll(fileMenu);
        topBar.getChildren().add(menuBar);

        EventHandler colorHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuItem source = (MenuItem) event.getSource();
                for (ThemeColor tc : ThemeColor.values()) {
                    if (source.getText().equals(tc.getName())) {
                        view.setThemeColor(tc);
                        changeColor(view.getThemeColor(), bgFX);
                    }
                }
            }
        };
        for (MenuItem m : color.getItems()) {
            m.setOnAction(colorHandler);
        }

        EventHandler gameStateHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuItem source = (MenuItem) event.getSource();
                System.out.println(source.getText());
                boolean paused = false;
                if (source.getText().equals("QUIT")) {
                    System.exit(0);
                } else if (source.getText().equals("PAUSE/UNPAUSE")) {
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
        for (ShapeFX s : view.getShipFX().getShipGeometry()) {
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
