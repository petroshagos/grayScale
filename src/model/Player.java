package model;

import java.util.LinkedList;

/**
 * Created by Petros on 2016-10-09.
 */
public class Player {

    private int score;
    private int healthPoints;
    private int nrOfLives;
    private LinkedList<Ship> ships;

    public Player(int score, int hP, int lives, LinkedList<Ship> ships) {
        this.score = score;
        this.healthPoints = hP;
        this.nrOfLives = lives;
        this.ships = ships;
    }

    public int getScore() {
        return this.score;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public int getNrOfLives() {
        return this.nrOfLives;
    }

    public LinkedList<Ship> getShips() {
        return this.ships;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    private void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    private void setNrOfLives(int nrOfLives) {
        this.nrOfLives = nrOfLives;
    }

    private void setShips(LinkedList<Ship> ships) {
        this.ships = ships;
    }

    public void removeHealthPoints(int healthPoints) {
        this.healthPoints -= healthPoints;
    }
}
