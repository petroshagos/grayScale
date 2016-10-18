/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import model.Shape.Shape;

import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.GameGUI;

/**
 *
 * @author Petros Hagos & Dag Oldenburg
 */
public class GameModel {

    private Player player;
    private int wave = 0;
    private int numberOfAi = 10;
    int currentNumberOfAi = 0;
    private Background background;
    Random rand = new Random();
    private LinkedList<Ship> ships = new LinkedList<>();
    private LinkedList<EnemyShip> enemies = new LinkedList<>();
    private LinkedList<Projectile> projectiles = new LinkedList<>();
    private LocalTime comparisonTime;

    public GameModel() {
        this.player = new Player();
        this.background = new Background();
        for (Ship s : player.getShips()) {
            ships.add(s);
        }
        comparisonTime = LocalTime.now();
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
    public LinkedList<EnemyShip> getEnemies(){
        return enemies;
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
            for (Shape s : temp.getProjectile()) {
                s.setVelocity(40, 0);
            }
            projectiles.add(temp);
        }
        return projectiles.getLast();
    }

    public void move(long elapsedTimeNs) {
        /*for(Shape s: enemies.get().getShipGeometry()){
            s.move(elapsedTimeNs);
        }*/
        for (EnemyShip es : enemies) {
            for (Shape s : es.getShipGeometry()) {
                s.move(elapsedTimeNs);
            }
        }
        for (Shape s : background.getBgBack()) {
            s.move(elapsedTimeNs);
        }
        for (Shape s : background.getBgFront()) {
            s.move(elapsedTimeNs);
        }
        for (Shape s : background.getTerrainList()) {
            s.move(elapsedTimeNs);
        }

    }

}

