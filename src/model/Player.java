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

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
