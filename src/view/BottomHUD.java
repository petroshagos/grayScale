package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import model.GameModel;
import model.Player;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class BottomHUD extends GridPane {

    private Label name = new Label();
    private Label lives = new Label();
    private Label health = new Label();
    private Label score = new Label();
    private GridPane g = new GridPane();
    private ThemeColor tc;
    private Player p;

    public BottomHUD(GameModel model, ThemeColor tc) {
        this.tc = tc;
        g.setAlignment(Pos.CENTER);
        p = model.getPlayer();
        name.setText("Player: " + p.getName());
        name.setTextFill(tc.getColor(4));
        //name.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
        name.setMaxWidth(Double.MAX_VALUE);
        name.setPrefHeight(20);
        name.setAlignment(Pos.CENTER);
        lives.setText("Lives: " + p.getNrOfLives());
        lives.setTextFill(tc.getColor(4));
        //lives.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
        lives.setMaxWidth(Double.MAX_VALUE);
        lives.setPrefHeight(20);
        lives.setAlignment(Pos.CENTER);
        health.setText("Health: " + p.getCurrentShip().getHealthPoints() + "%");
        health.setTextFill(tc.getColor(4));
        //health.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
        health.setMaxWidth(Double.MAX_VALUE);
        health.setPrefHeight(20);
        health.setAlignment(Pos.CENTER);
        score.setText("Score: " + p.getScore());
        score.setTextFill(tc.getColor(4));
        //score.setFont(Font.loadFont("file:resources/font/redensek.ttf", 22));
        score.setMaxWidth(Double.MAX_VALUE);
        score.setPrefHeight(20);
        score.setAlignment(Pos.TOP_CENTER);
        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setPercentWidth(25);
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setPercentWidth(25);
        ColumnConstraints cc3 = new ColumnConstraints();
        cc3.setPercentWidth(25);
        ColumnConstraints cc4 = new ColumnConstraints();
        cc4.setPercentWidth(25);
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
