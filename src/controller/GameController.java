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
        System.out.println(ke.getCode());
        model.getPlayer().addScore(100);
        view.updateHUD(model);
    }
}
