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
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Random;

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
    private LinkedList<PowerUp> powerUps = new LinkedList<>();
    private long now;
    private boolean isPaused,isGameOver;

    public GameModel() {
        this.player = new Player();
        this.background = new Background();
        this.ship = player.getCurrentShip();
        isPaused = false;
        isGameOver = false;
        highScoreList = new HighScoreList();
        try {
            highScoreList.deserialise();
            System.out.println("Deserialized Success");
        } catch (Exception ie) {
            System.out.println("HighScoreList() IOException");
        }
    }

    public GameModel(Player player) {
        this.player = player;
        this.background = new Background();
        this.ship = player.getCurrentShip();
        this.projectiles = new LinkedList<>();
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public HighScoreList getHighScoreList() {
        return highScoreList;
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

    private void addPowerUp(double x, double y) {
        Random rand = new Random();
        PowerUp tempPwrUp = new PowerUp(x, y, rand.nextInt(3));
        tempPwrUp.getRect().setCollidable(true);
        powerUps.add(tempPwrUp);
    }

    public LinkedList<PowerUp> getPowerUps() {
        return powerUps;
    }

    public void makeProjectile(Ship tempShip, double xVel, boolean isPlayer) {
        tempShip.updateWeaponPos(isPlayer);
        Projectile temp = new Projectile(tempShip.getWeaponPosX(), tempShip.getWeaponPosY(), isPlayer, tempShip.getDamage());
        temp.setVelocity(xVel, 0);
        projectiles.add(temp);
        if (tempShip.getMultiShot()) {
            temp = new Projectile(tempShip.getWeaponPosX(), tempShip.getWeaponPosY(), isPlayer, tempShip.getDamage());
            temp.setVelocity(xVel, 50);
            projectiles.add(temp);
            temp = new Projectile(tempShip.getWeaponPosX(), tempShip.getWeaponPosY(), isPlayer, tempShip.getDamage());
            temp.setVelocity(xVel, -50);
            projectiles.add(temp);
        }
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

    public void aiShoot() {
        for (EnemyShip es : enemyShips) {
            if (LocalTime.now().isAfter(es.getLastShot().plusSeconds(2))) {
                makeProjectile(es, -250, false);
                es.setLastShot();
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

        for(PowerUp p : powerUps){
            if(p.getRect().isOutOfBounds()){
                powerUps.remove(p);
            }
        }
    }

    public void aiDeath(EnemyShip e) {
        Random rand = new Random();
        e.setCollidable(false);
        e.setAlive(false);
        player.addScore(100);
        if (rand.nextBoolean())
            addPowerUp(e.getX(), e.getY());
    }


    public void handleCollisions() {
        for (Projectile p : projectiles) {
            for (Rectangle r : p.getProjectile()) {
                if (p.isPlayer()) {
                    for (EnemyShip e : enemyShips) {
                        for (Triangle es : e.getShipGeometry()) {
                            if (r.collision(es) && r.isCollidable() && es.isCollidable()) {
                                r.setCollidable(false);
                                p.setCollidable(false);
                                p.explodeProjectile();
                                e.decreaseHealthPoints(player.getCurrentShip().getDamage());
                                if(e.getHealthPoints() <= 0){
                                    aiDeath(e);
                                }
                            }
                        }
                    }
                } else {
                    for (Triangle es : player.getCurrentShip().getShipGeometry()) {
                        if (r.collision(es) && r.isCollidable() && es.isCollidable()) {
                            r.setCollidable(false);
                            p.setCollidable(false);
                            p.explodeProjectile();
                            player.decreaseHealthPoints(p.getDamage());
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
                                ship.decreaseHealthPoints(100);
                            }
                            else {
                                ship.decreaseHealthPoints(ship.getHealthPoints());
                            }
                            player.addScore(100);
                        }
                    }
                }
                for (PowerUp p : powerUps) {
                    if (p.getRect().collision(s)) {
                        p.getPowerUp(ship, player);
                        powerUps.remove(p);
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
                player.makeNewPlayerShip(50,200,100,1);
                ship = player.getCurrentShip();
            }
            else if (!player.getCurrentShip().isAlive() && player.getNrOfLives()<=0) {
                isGameOver = true;
            }
        }
        if (isGameOver) {
            highScoreList.addPlayer(player);
            gameOver();
        }
    }

    public void gameOver() {
        try {
            highScoreList.serialise();
        } catch (IOException ie) {

        }
        String temp = player.getName();
        enemyShips.clear();
        projectiles.clear();
        this.player = new Player();
        player.setName(temp);
        this.background = new Background();
        this.ship = player.getCurrentShip();
        isPaused = false;
        isGameOver = false;
        highScoreList = new HighScoreList();
        try {
            highScoreList.deserialise();
            System.out.println("Deserialized Success");
        } catch (Exception ie) {
            System.out.println("HighScoreList() IOException");
        }

    }

    public void constrainPlayerShip() {
        for (Shape s: ship.getShipGeometry()) {
            s.constrain();
        }
    }

}
