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
    private Ship ship;
    private LinkedList<EnemyShip> enemyShips = new LinkedList<>();
    private LinkedList<Projectile> projectiles = new LinkedList<>();
    private long now;
    private boolean isPaused;

    public GameModel() {
        this.player = new Player();
        this.background = new Background();
        this.ship = player.getCurrentShip();
        isPaused = false;
    }

    public GameModel(Player player) {
        this.player = player;
        this.background = new Background();
        this.ship = player.getCurrentShip();
        this.projectiles = new LinkedList<>();
    }

    public Background getBackground() {
        return background;
    }

    public LinkedList<EnemyShip> getEnemyShips() {
        return enemyShips;
    }

    public Ship getPlayerShip() {
        return ship;
    }

    public LinkedList<Projectile> getProjectiles() {
        return projectiles;
    }

    public Player getPlayer() {
        return player;
    }

    public void makeProjectile() {
        PlayerShip tempShip = (PlayerShip) ship;
        tempShip.updateWeaponPos();
        System.out.println(tempShip.getWeaponPosX());
        System.out.println(tempShip.getWeaponPosY());
        Projectile temp = new Projectile(tempShip.getWeaponPosX(), tempShip.getWeaponPosY());
        projectiles.add(temp);
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
        for (Shape s: ship.getShipGeometry()) {
            s.move(elapsedTimeNs);
        }
        for (Projectile p: projectiles) {
            for (Shape s: p.getProjectile()) {
                s.move(elapsedTimeNs);
            }
        }
        for (Ship e: enemyShips) {
            for (Shape s: e.getShipGeometry()) {
                s.move(elapsedTimeNs);
            }
        }
    }

    public void updateWeaponPos() {
        ship.updateShipWeaponPos(ship.getX(),ship.getY());
        for (EnemyShip e: enemyShips) {
            e.updateWeaponPos();
        }
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

}
