/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Shape.Shape;

import java.util.LinkedList;

/**
 *
 * @author Petros Hagos & Dag Oldenburg
 */
public class GameModel {

    private Player player;
    private Background background;
    private LinkedList<Ship> ships;
    private LinkedList<Projectile> projectiles;

    public GameModel(Player player) {
        this.player = player;
        this.background = new Background();
        this.ships = new LinkedList<>();
        this.projectiles = new LinkedList<>();
    }

    public Background getBackground() {
        return background;
    }

    public LinkedList<Ship> getShips() {
        return ships;
    }

    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void move(long elapsedTimeNs) {

        for (Shape s: background.getBgBack()) {
            s.move(elapsedTimeNs);
        }
        for (Shape s: background.getBgFront()) {
            s.move(elapsedTimeNs);
        }
/*        for (Shape s: background.getTerrainList()) {
            s.move(elapsedTimeNs);
        }*/

    }
    
}
