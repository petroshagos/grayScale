package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import model.GameModel;
import model.Shape.Direction;
import view.GameGUI;
import view.ThemeColor;

import java.io.IOException;

/**
 *
 * @author Petros Hagos & Dag Oldenburg
 */
public class GameController {

    private GameModel model;
    private GameGUI view;

    public GameController(GameModel model, GameGUI view) {
        this.model = model;
        this.view = view;
    }

    public void handleMenuTheme(ActionEvent event) {
        MenuItem source = (MenuItem) event.getSource();
        for (ThemeColor tc : ThemeColor.values()) {
            if (source.getText().equals(tc.getName())) {
                view.setThemeColor(tc);
                view.changeColor(view.getThemeColor());
            }
        }
    }

    public void handleHighScore() {
        try {
            model.getHighScoreList().serialise();
        } catch (IOException ie) {
        }
        view.showHighscore();
    }

    public void handleNewGame(ActionEvent event) {
        model.setGameOver(true);
        try {
            model.getHighScoreList().serialise();
        } catch (IOException ie) {
        }
        handleGameOver();

    }

    public void handleQuit() {
        model.getHighScoreList().addPlayer(model.getPlayer());
        try {
            model.getHighScoreList().serialise();
        } catch (IOException ie) {

        }
        System.exit(0);
    }

    public void changePlayerName(String name) {
        model.getPlayer().setName(name);
    }

    public void handleKeyPress(KeyEvent ke) {
        switch (ke.getCode()) {
            case UP:
                if (model.getPlayer().getCurrentShip().getShipConstraint(Direction.UP)) {
                    model.getPlayer().decreaseHealthPoints(10);
                    model.getPlayer().getCurrentShip().moveShip(0,90);
                }
                model.getPlayer().getCurrentShip().setShipVelocity(model.getPlayer().getCurrentShip().getShipGeometry().get(0).getDx(),-200);break;
            case DOWN:
                if (model.getPlayer().getCurrentShip().getShipConstraint(Direction.DOWN)) {
                    model.getPlayer().decreaseHealthPoints(10);
                    model.getPlayer().getCurrentShip().moveShip(0,-90);
                }
                model.getPlayer().getCurrentShip().setShipVelocity(model.getPlayer().getCurrentShip().getShipGeometry().get(0).getDx(),200);break;
            case RIGHT:
                if (model.getPlayer().getCurrentShip().getShipConstraint(Direction.RIGHT)) {
                    model.getPlayer().decreaseHealthPoints(10);
                    model.getPlayer().getCurrentShip().moveShip(-90,0);
                }
                model.getPlayer().getCurrentShip().setShipVelocity(200,model.getPlayer().getCurrentShip().getShipGeometry().get(0).getDy());break;
            case LEFT:
                if (model.getPlayer().getCurrentShip().getShipConstraint(Direction.LEFT)) {
                    model.getPlayer().decreaseHealthPoints(10);
                    model.getPlayer().getCurrentShip().moveShip(90,0);
                }
                model.getPlayer().getCurrentShip().setShipVelocity(-200,model.getPlayer().getCurrentShip().getShipGeometry().get(0).getDy());break;
            case SPACE: break;
            case SHIFT: System.out.println(ke.getCode());break;
            case P: handlePause(); break;
            case ESCAPE:
                model.getHighScoreList().addPlayer(model.getPlayer());
                try {
                    model.getHighScoreList().serialise();
                } catch (IOException ie) {

                }
                System.exit(0);break;
            case R:
                System.out.println(model.getProjectiles().size());
                System.out.println(model.getEnemyShips().size());
                System.out.println(model.getPlayer().getCurrentShip().getX());
                System.out.println(model.getPlayer().getCurrentShip().isAlive());
                System.out.println(model.getPlayer().getCurrentShip().isExploded());
                break;
            case Y:
                model.getHighScoreList().addPlayer(model.getPlayer());
                System.out.println(model.getHighScoreList().toString()); break;
            case U:
                try {
                    model.getHighScoreList().serialise();
                    System.out.println("Serialized Success");
                } catch (IOException ie) {
                    System.out.println("Serialization Failed");

                }break;
            case I:
                System.out.println(model.getHighScoreList().toString());


            default:
                System.out.println("Wrong key");
                break;
        }
    }

    public void handleKeyRelease(KeyEvent ke) {
        switch (ke.getCode()) {
            case UP: model.getPlayer().setShipVelocity(model.getPlayer().getCurrentShip().getShipGeometry().get(0).getDx(),0);break;
            case DOWN: model.getPlayer().setShipVelocity(model.getPlayer().getCurrentShip().getShipGeometry().get(0).getDx(),0);break;
            case RIGHT: model.getPlayer().setShipVelocity(0,model.getPlayer().getCurrentShip().getShipGeometry().get(0).getDy());break;
            case LEFT: model.getPlayer().setShipVelocity(0,model.getPlayer().getCurrentShip().getShipGeometry().get(0).getDy());break;
            case SPACE: model.makeProjectile(model.getPlayerShip(),250,true);break;
            case SHIFT: System.out.println(ke.getCode());break;
            case P: break;
            case ESCAPE: System.out.println(ke.getCode());break;
            default: break;
        }
    }

    public void handlePause() {
        if (!model.isPaused()) {
            model.setPaused(true);
        }
        else {
            model.setPaused(false);
        }
    }

    public void handleGameOver() {
        if (model.isGameOver()) {
            model.gameOver();
            view.startScreen();
        }

    }

}
