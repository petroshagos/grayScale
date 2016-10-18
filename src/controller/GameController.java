/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import model.GameModel;
import view.GameGUI;
import view.ThemeColor;

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
                view.setBorderPaneColor(tc);
            }
        }
    }
    public void changePlayerName(String name) {
        model.getPlayer().setName(name);
    }
    public void updateHUD() {

    }

    public void handleKeyPress(KeyEvent ke) {

        //model.getPlayer().addScore(100);
        //view.updateHUD(model);
        switch (ke.getCode()) {
            case UP: System.out.println(ke.getCode());break;
            case DOWN: System.out.println(ke.getCode());break;
            case RIGHT: System.out.println(ke.getCode());break;
            case LEFT: System.out.println(ke.getCode());break;
            case SPACE: System.out.println(ke.getCode());break;
            case SHIFT: System.out.println(ke.getCode());break;
            case P: handlePause(); break;
            case ESCAPE: System.out.println(ke.getCode());break;
            default:
                System.out.println("Wrong key");
                break;
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
}
