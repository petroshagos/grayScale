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
    private long now;
    private boolean isPaused;

    public GameModel() {
        this.player = new Player();
        this.background = new Background();
        for (Ship s: player.getShips()) {
            ships.add(s);
        }
        isPaused = false;
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

    public void makeProjectile() {
        PlayerShip tempShip = (PlayerShip) ships.getFirst();
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
        for (Shape s: player.getCurrentShip().getShipGeometry()) {
            s.move(elapsedTimeNs);
        }
        for (Projectile p: projectiles) {
            for (Shape s: p.getProjectile()) {
                s.move(elapsedTimeNs);
            }
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
