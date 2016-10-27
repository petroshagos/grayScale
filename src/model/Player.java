package model;

import model.Shape.Shape;

import java.io.Serializable;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class Player implements Serializable {
    private String name;
    private int score;
    private int nrOfLives;
    private PlayerShip ship;

    public Player() {
        this.name = "Player";
        this.score = 0;
        this.nrOfLives = 3;
        this.ship = new PlayerShip(50,200,100,1);
    }

    public Player(String name, int score, int lives, PlayerShip ship) {
        this.name = name;
        this.score = score;
        this.nrOfLives = lives;
        this.ship = ship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public int getNrOfLives() {
        return this.nrOfLives;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public PlayerShip getCurrentShip() {
        return ship;
    }

    public void decreaseHealthPoints(int i) {
        ship.decreaseHealthPoints(i);
    }

    public void setShipVelocity(double dx, double dy) {
        for (Shape s: ship.getShipGeometry()) {
            s.setVelocity(dx,dy);
        }
    }

    public void makeNewPlayerShip(double x, double y, int hP, int damage) {
        this.ship = new PlayerShip(x, y, hP, damage);
    }

    public void decreaseNrOfLives() {
        this.nrOfLives -=1 ;
    }

    private void setScore(int score) {
        this.score = score;
    }

    private void setNrOfLives(int nrOfLives) {
        this.nrOfLives = nrOfLives;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
