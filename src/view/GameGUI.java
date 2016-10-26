
package view;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Background;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;
import model.Shape.Terrain;
import view.FX.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;

/**
 *
 * @author Petros Hagos & Dag Oldenburg
 */
public class GameGUI {

    private ThemeColor themeColor;
    private GameModel model;
    private GameController controller;
    private BackgroundFX bgFX;
    private ShipFX shipFX;
    private LinkedList<ShipFX> enemiesFX = new LinkedList<>();
    private LinkedList<ProjectileFX> pFX = new LinkedList<>();
    private LinkedList<PowerUpFX> powerUps = new LinkedList<>();
    private boolean timerIsOn;
    private BorderPane borderPane;
    private MenuBar menuBar;
    private CanvasView cv;
    private GridPane gridPane;
    private Scene highScoreWindow;


   public GameGUI(GameModel model) {
       this.model = model;
       this.controller = new GameController(this.model, this);
       this.themeColor = ThemeColor.THEME_GRAY;
       bgFX = new BackgroundFX(themeColor, this.model.getBackground());
       shipFX = new ShipFX(themeColor, this.model.getPlayer().getCurrentShip());

       cv = new CanvasView(themeColor);
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

    public CanvasView getCanvasView() {
        return cv;
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

        for (ProjectileFX p: pFX) {
            for (ShapeFX s: p.getProjectileGeometry()) {
                s.paint(gc);
            }
        }

        for (ShipFX e: enemiesFX) {
            for (ShapeFX s: e.getShipGeometry()) {
                s.paint(gc);
            }
        }

        for (PowerUpFX p : powerUps) {
            p.getRectFX().paint(gc);
        }

        for (ShapeFX s: bgFX.getTerrainList()) {
            s.paint(gc);
        }
    }

    public GameController getGameController() {
        return controller;
    }

    public Canvas getCanvas() {
        return cv.getCanvasView();
    }

    public BorderPane makeBorderPane(GameModel model) throws IOException{
        borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(themeColor.getColor(0),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        borderPane.setTop(makeMenuBar());
        borderPane.setCenter(cv.getCanvasView());
        borderPane.setBottom(makeHUD(model));
        return borderPane;
    }

    public void setBorderPaneColor(ThemeColor tc) {
        borderPane.setBackground(new Background(new BackgroundFill(tc.getColor(0),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
    }

    public MenuBar makeMenuBar() throws IOException{
        menuBar = new MenuBar();
        Menu fileMenu = new Menu("Menu");
        MenuItem newGame = new MenuItem("New Game");
        fileMenu.getItems().add(newGame);
        MenuItem pause = new MenuItem("Pause/Play (P)");
        pause.setOnAction(pauseHandler);
        fileMenu.getItems().add(pause);
        MenuItem highScore = new MenuItem("High score");
        highScore.setOnAction(highScoreHandler);
        fileMenu.getItems().add(highScore);
        Menu color = new Menu("Theme");
        fileMenu.getItems().add(color);
        for (ThemeColor tc : ThemeColor.values()) {
            color.getItems().add(new MenuItem(tc.getName()));
        }
        MenuItem quit = new MenuItem("Quit (ESC)");
        fileMenu.getItems().add(quit);
        menuBar.getMenus().addAll(fileMenu);

        for (MenuItem m : color.getItems()) {
            m.setOnAction(colorHandler);
        }
        return menuBar;
    }

    public GridPane makeHUD(GameModel model) {
        gridPane = new GridPane();
        Text name = new Text("Player: " + model.getPlayer().getName());
        name.setFill(themeColor.getColor(4));
        name.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
        name.setFontSmoothingType(FontSmoothingType.LCD);
        Text lives = new Text("Lives: " + model.getPlayer().getNrOfLives());
        lives.setFill(themeColor.getColor(4));
        lives.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
        lives.setFontSmoothingType(FontSmoothingType.LCD);
        Text health = new Text("Health: " + model.getPlayer().getCurrentShip().getHealthPoints() + "%");
        health.setFill(themeColor.getColor(4));
        health.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
        health.setFontSmoothingType(FontSmoothingType.LCD);
        Text score = new Text("Score: " + model.getPlayer().getScore());
        score.setFill(themeColor.getColor(4));
        score.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
        score.setFontSmoothingType(FontSmoothingType.LCD);
        Text wave = new Text("WAVE: " + model.getPlayer().getScore());
        wave.setFill(themeColor.getColor(4));
        wave.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
        wave.setFontSmoothingType(FontSmoothingType.LCD);
        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setPercentWidth(20);
        cc1.setHalignment(HPos.CENTER);
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setPercentWidth(20);
        cc2.setHalignment(HPos.CENTER);
        ColumnConstraints cc3 = new ColumnConstraints();
        cc3.setPercentWidth(20);
        cc3.setHalignment(HPos.CENTER);
        ColumnConstraints cc4 = new ColumnConstraints();
        cc4.setPercentWidth(20);
        cc4.setHalignment(HPos.CENTER);
        ColumnConstraints cc5 = new ColumnConstraints();
        cc5.setPercentWidth(20);
        cc5.setHalignment(HPos.CENTER);
        gridPane.getColumnConstraints().addAll(cc1, cc2, cc3, cc4, cc5);
        gridPane.add(name, 0, 0);
        gridPane.add(lives, 1, 0);
        gridPane.add(health, 2, 0);
        gridPane.add(score, 3, 0);
        gridPane.add(wave, 4, 0);
        return gridPane;
    }

    EventHandler colorHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.handleMenuTheme(event);
        }
    };

    EventHandler pauseHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            controller.handlePause();
        }
    };

    EventHandler highScoreHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
                controller.handleHighScore();
            } catch (IOException ie) {
                System.out.println("HighScoreList() IOException");
            }

        }
    };

    public void changeColor(ThemeColor themeColor) {
        for (ShapeFX s : bgFX.getBgBack()) {
            s.setThemeColor(themeColor);
        }
        for (ShapeFX s : bgFX.getBgFront()) {
            s.setThemeColor(themeColor);
        }
        for (ShapeFX s : bgFX.getTerrainList()) {
            s.setThemeColor(themeColor);
        }
        for (ShapeFX s: shipFX.getShipGeometry()) {
            s.setThemeColor(themeColor);
        }
        for (ShipFX e: enemiesFX) {
            for (ShapeFX s: e.getShipGeometry()) {
                s.setThemeColor(themeColor);
            }
        }
        setBorderPaneColor(themeColor);
    }

    public void startScreen() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("grayScale");
        dialog.setHeaderText("Enter your name: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            controller.changePlayerName(result.get());
        }
    }

    public void showHighScore() throws IOException {
        Stage newStage = new Stage();
        HighScore highScore = new HighScore();
        highScoreWindow = new Scene(highScore.getVBox(), 300, 400);
        newStage.setScene(highScoreWindow);
        newStage.setResizable(false);
        newStage.show();
    }

    public void showGameOver() {
        Stage newStage = new Stage();
        GridPane gridPane = new GridPane();
        Text gameOver = new Text("Game Over");
        gameOver.setFill(themeColor.getColor(0));
        gameOver.setFont(Font.loadFont("file:resources/font/redensek.ttf", 36));
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100);
        cc.setHalignment(HPos.CENTER);
        gameOver.setFontSmoothingType(FontSmoothingType.LCD);
        gridPane.getColumnConstraints().addAll(cc);
        gridPane.add(gameOver,0,0);
        Scene gameOverScene = new Scene(gridPane, 200, 40);
        newStage.setScene(gameOverScene);
        newStage.setResizable(false);
        newStage.show();
    }

    public void updateHUD(GameModel model) {
        borderPane.setBottom(makeHUD(model));
    }

    public void updateProjectiles(GameModel model) {
        pFX.clear();
        for (Projectile p: model.getProjectiles()) {
            pFX.add(new ProjectileFX(themeColor, p));
        }
    }

    public void updateEnemies(GameModel model) {
        enemiesFX.clear();
        for (EnemyShip e: model.getEnemyShips()) {
            enemiesFX.add(new ShipFX(themeColor, e));
        }
    }

    public void updatePowerUps(GameModel model) {
        powerUps.clear();
        for (PowerUp p : model.getPowerUps()) {
            powerUps.add(new PowerUpFX(p));
        }
    }

    public void updateBackground(GameModel model) {
        bgFX.getTerrainList().clear();
        for (Terrain t: model.getBackground().getTerrainList()) {
            bgFX.getTerrainList().add(new TerrainFX(themeColor, t));
        }
    }

    public void updateObjectsOnScreen(GameModel model) {
        shipFX = new ShipFX(themeColor, model.getPlayer().getCurrentShip());
    }

    public void addShipFx(Ship ship) {
        enemiesFX.add(new ShipFX(themeColor, ship));
    }

}
