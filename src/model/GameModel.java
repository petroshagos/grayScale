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
    private LinkedList<Ship> ships = new LinkedList<>();
    private LinkedList<Projectile> projectiles = new LinkedList<>();

    public GameModel() {
        this.player = new Player();
        this.background = new Background();
        for (Ship s: player.getShips()) {
            ships.add(s);
        }
    }

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

    public Player getPlayer() {
        return player;
    }

    public Projectile makeProjectile() {
        Ship tempShip = ships.getLast();
        if (tempShip instanceof PlayerShip) {
            Projectile temp = new Projectile(((PlayerShip) tempShip).getWeaponPosX(), ((PlayerShip) tempShip).getWeaponPosY());
            for (Shape s: temp.getProjectile()) {
                s.setVelocity(40,0);
            }
            projectiles.add(temp);
        }
        return projectiles.getLast();
    }

    public void move(long elapsedTimeNs) {

        for (Shape s: background.getBgBack()) {
            s.move(elapsedTimeNs);
        }
        for (Shape s: background.getBgFront()) {
            s.move(elapsedTimeNs);
        }
        for (Shape s: background.getTerrainList()) {
            s.move(elapsedTimeNs);
        }

    }
    
}
