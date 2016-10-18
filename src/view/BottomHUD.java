package view;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.Text;
import model.GameModel;
import model.Player;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class BottomHUD extends GridPane {

    private Text name = new Text();
    private Text lives = new Text();
    private Text health = new Text();
    private Text score = new Text();
    private GridPane g = new GridPane();
    private ThemeColor tc;
    private Player p;

    public BottomHUD(GameModel model, ThemeColor tc) {
        this.tc = tc;
        g.setAlignment(Pos.CENTER);
        p = model.getPlayer();
        name.setText("Player: " + p.getName());
        name.setFill(tc.getColor(4));
        name.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
        name.setFontSmoothingType(FontSmoothingType.LCD);
        lives.setText("Lives: " + p.getNrOfLives());
        lives.setFill(tc.getColor(4));
        lives.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
        health.setText("Health: " + p.getCurrentShip().getHealthPoints() + "%");
        health.setFill(tc.getColor(4));
        health.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
        score.setText("Score: " + p.getScore());
        score.setFill(tc.getColor(4));
        score.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
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
        g.getColumnConstraints().addAll(cc1, cc2, cc3, cc4);
        g.add(name, 0, 0);
        g.add(lives, 1, 0);
        g.add(health, 2, 0);
        g.add(score, 3, 0);
    }

    public void updateLives(Player player){
        lives.setText("Lives: " + player.getNrOfLives());
    }
    public void updateHealth(Player player){
        health.setText("Health: " + player.getCurrentShip().getHealthPoints());
    }
    public void updateScore(Player player){
        score.setText("Score: " + player.getScore());
    }

    public GridPane getGridPane(){
        return g;
    }

    public void updateHUD(GameModel model) {
        name.setText("Player: " + model.getPlayer().getName());
        lives.setText("Lives: " + model.getPlayer().getNrOfLives());
        health.setText("Health: " + model.getPlayer().getCurrentShip().getHealthPoints() + "%");
        score.setText("Score: " + model.getPlayer().getScore());
    }

}
