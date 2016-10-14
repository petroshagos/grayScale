package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.MenuBar;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class BottomHUD {

    private Text name = new Text();
    private Text lives = new Text();
    private Text health = new Text();
    private Text score = new Text();
    private Group g = new Group();
    
    public void start(Stage primaryStage,Player player) {
        name.setText("Player: " + player.getName());
        lives.setText("Lives: " + player.getNrOfLives());
        health.setText("Health: " + player.getCurrentShip().getHealthPoints() + "%");
        score.setText("Score: " + player.getScore());
        name.setX(0);
        lives.setX(200);
        health.setX(400);
        score.setX(600);
        g.getChildren().add(name);
        g.getChildren().add(lives);
        g.getChildren().add(health);
        g.getChildren().add(score);
        
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
    
    public Group getGroup(){
        return g;
    }

}
