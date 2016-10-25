/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Shape.Rectangle;
import model.Shape.Shape;
import model.Shape.Triangle;

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

        /*for (Ship s: enemyShips) {
            for (Shape sg: s.getShipGeometry()) {
                if (sg.isOutOfBounds()) {
                    enemyShips.remove(s);
                }
            }
        }
        for (Shape s: getPlayer().getCurrentShip().getShipGeometry()) {
            if (s.isOutOfBounds()) {
                getPlayer().getCurrentShip().getShipGeometry().remove(s);
            }
        }
        for (Terrain t:background.getTerrainList()) {

            if (t.getLastPoint()[0] < 0) {
                background.getTerrainList().remove(t);
            }

        }*/
    }

    public void handleCollisions() {
        for (Projectile p:projectiles) {
            for(Rectangle r: p.getProjectile()) {
                for (EnemyShip e: enemyShips) {
                    for (Triangle es: e.getShipGeometry()) {
                        if (r.collision(es)) {
                            r.setCollidable(false);
                            player.addScore(100);
                            p.setCollidable(false);
                            e.setAlive(false);
                            e.explodeShip();
                            p.explodeProjectile();
                        }
                    }
                }
            }
        }
    }

    public void constrainPlayerShip() {
        for (Shape s: player.getCurrentShip().getShipGeometry()) {
            s.constrain();
        }
    }

}
