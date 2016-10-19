
package view;

import controller.GameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import model.GameModel;
import model.Projectile;
import model.Ship;
import view.FX.BackgroundFX;
import view.FX.ProjectileFX;
import view.FX.ShapeFX;
import view.FX.ShipFX;

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
    private boolean timerIsOn;
    private BorderPane borderPane;
    private MenuBar menuBar;
    private Canvas canvas;
    private GridPane gridPane;
    private Text t = new Text();

   public GameGUI(GameModel model) {
       this.model = model;
       this.controller = new GameController(this.model, this);
       this.themeColor = ThemeColor.THEME_GRAY;
       bgFX = new BackgroundFX(themeColor, this.model.getBackground());
       shipFX = new ShipFX(themeColor, this.model.getPlayer().getCurrentShip());
       t.setX(340);
       t.setY(220);
       t.setFont(new Font(40));
       t.setFill(themeColor.getColor(6));
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

    public void paint(GraphicsContext gc) {
        for (ShapeFX s : bgFX.getBgBack()) {
            s.paint(gc);
        }

        for (ShapeFX s : bgFX.getBgFront()) {
            s.paint(gc);
        }

        for (ShapeFX s: bgFX.getTerrainList()) {
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
    }

    public GameController getGameController() {
        return controller;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public BorderPane makeBorderPane(GameModel model) {
        borderPane = new BorderPane();
        borderPane.setBackground(new Background(new BackgroundFill(themeColor.getColor(0),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        borderPane.setTop(makeMenuBar());
        borderPane.setCenter(makeCanvas());
        borderPane.setBottom(makeHUD(model));
        return borderPane;
    }

    public void setBorderPaneColor(ThemeColor tc) {
        borderPane.setBackground(new Background(new BackgroundFill(tc.getColor(0),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
    }

    public MenuBar makeMenuBar() {
        menuBar = new MenuBar();
        Menu fileMenu = new Menu("Menu");
        MenuItem newGame = new MenuItem("New Game");
        fileMenu.getItems().add(newGame);
        MenuItem pause = new MenuItem("Pause/Play");
        pause.setOnAction(pauseHandler);
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

        for (MenuItem m : color.getItems()) {
            m.setOnAction(colorHandler);
        }
        return menuBar;
    }

    public Canvas makeCanvas() {
        canvas = new Canvas(808,400);
        return canvas;
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
        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setPercentWidth(25);
        cc1.setHalignment(HPos.CENTER);
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setPercentWidth(25);
        cc2.setHalignment(HPos.CENTER);
        ColumnConstraints cc3 = new ColumnConstraints();
        cc3.setPercentWidth(25);
        cc3.setHalignment(HPos.CENTER);
        ColumnConstraints cc4 = new ColumnConstraints();
        cc4.setPercentWidth(25);
        cc4.setHalignment(HPos.CENTER);
        gridPane.getColumnConstraints().addAll(cc1, cc2, cc3, cc4);
        gridPane.add(name, 0, 0);
        gridPane.add(lives, 1, 0);
        gridPane.add(health, 2, 0);
        gridPane.add(score, 3, 0);
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

    public void updateHUD(GameModel model) {
        borderPane.setBottom(makeHUD(model));
    }

    public void updateProjectiles(GameModel model) {
        pFX.clear();
        for (Projectile p: model.getProjectiles()) {
            pFX.add(new ProjectileFX(themeColor, p));
        }
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

    public void addShipFx(Ship ship) {
        enemiesFX.add(new ShipFX(themeColor, ship));
    }



}
