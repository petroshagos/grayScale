/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.HighScore.HighScoreList;
import model.Shape.Rectangle;
import model.Shape.Shape;
import model.Shape.Triangle;

import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author Petros Hagos & Dag Oldenburg
 */
public class GameModel {

    private Player player;
    private HighScoreList highScoreList;
    private Background background;
    private Ship ship;
    private LinkedList<EnemyShip> enemyShips = new LinkedList<>();
    private LinkedList<Projectile> projectiles = new LinkedList<>();
    private long now;
    private boolean isPaused,isGameOver;

    public GameModel() {
        this.player = new Player();
        this.background = new Background();
        this.ship = player.getCurrentShip();
        isPaused = false;
        isGameOver = false;
        try {
            highScoreList = new HighScoreList();
        } catch (IOException ie) {
            System.out.println("HighScoreList() IOException");
        }
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

    public void updateObjectsOnScreen() {
        if (projectiles.size()>0) {
            for (int i = 0; i < projectiles.size(); i++) {
                if (projectiles.get(i).isOutOfBounds()) {
                    projectiles.remove(i);
                    System.out.println(projectiles.size());
                }
            }
        }
        if (enemyShips.size()>0) {
            for (int i=0;i<enemyShips.size();i++) {
                if (enemyShips.get(i).getShipGeometry().get(0).getX()<-60)
                    enemyShips.get(i).setAlive(false);
                if (enemyShips.get(i).isOutOfBounds() && !enemyShips.get(i).isAlive()) {
                    enemyShips.remove(i);
                }
            }
        }

        for (int i=0;i<background.getTerrainList().size();i++) {
            if (background.getTerrainList().get(i).getLastPoint()[0]<0) {
                background.getTerrainList().remove(i);
                background.addTerrain();
            }
        }
    }

    public void handleCollisions() {
        for (Projectile p:projectiles) {
            for(Rectangle r: p.getProjectile()) {
                for (EnemyShip e: enemyShips) {
                    for (Triangle es: e.getShipGeometry()) {
                        if (r.collision(es) && r.isCollidable() && es.isCollidable()) {
                            r.setCollidable(false);
                            player.addScore(100);
                            p.setCollidable(false);
                            e.setCollidable(false);
                            e.setAlive(false);
                            p.explodeProjectile();
                        }
                    }
                }
            }
        }
        if (ship.isAlive()) {
            for (Shape s: ship.getShipGeometry()) {
                for (EnemyShip es: enemyShips) {
                    for (Shape r: es.getShipGeometry()) {
                        if (es.isAlive() && s.collision(r)) {
                            es.setCollidable(false);
                            es.setAlive(false);
                            if (ship.getHealthPoints()>25) {
                                ship.decreaseHealthPoints(25);
                            }
                            else {
                                ship.decreaseHealthPoints(ship.getHealthPoints());
                            }
                            player.addScore(100);
                        }
                    }
                }
            }
        }
    }

    public void updateActiveObjects() {
        if (ship.getHealthPoints()<= 0 && ship.isAlive()) {
            ship.setAlive(false);
            ship.setCollidable(false);
        }

        for (EnemyShip es: enemyShips) {
            if (!es.isAlive() && !es.isExploded()) {
                es.explodeShip();
                es.setExploded(true);
            }
        }
        if (!ship.isAlive() && !ship.isExploded()) {
            ship.explodeShip();
            ship.setExploded(true);
        }
    }

    public void updateGame() {
        if (!isGameOver) {
            if (!player.getCurrentShip().isAlive() && player.getNrOfLives()>0) {
                player.decreaseNrOfLives();
                player.makeNewPlayerShip(50,200,100);
                ship = player.getCurrentShip();
            }
            else if (!player.getCurrentShip().isAlive() && player.getNrOfLives()<=0) {
                isGameOver = true;
            }
        }
        if (isGameOver) {
            highScoreList.addPlayer(player);
            try {
                highScoreList.serialise();
            } catch (IOException ie) {
                System.out.println("HighScoreList() IOException");
            }
            player = new Player();
            ship = player.getCurrentShip();
            isGameOver = false;
        }
    }

    public void constrainPlayerShip() {
        for (Shape s: ship.getShipGeometry()) {
            s.constrain();
        }
    }

}
