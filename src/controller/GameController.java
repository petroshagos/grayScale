/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.GameModel;
import model.PowerUp;
import view.GameGUI;

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

    public void updateView() {
        for (PowerUp p: model.getPowerUps()) {
            view.addPowerUpFx(p);
        }
        
    }
}
