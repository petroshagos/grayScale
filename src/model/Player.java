package model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by Petros on 2016-10-09.
 */
public class Player implements Serializable{
    private String name;
    private int score;
    private int healthPoints;
    private int nrOfLives;
    private LinkedList<Ship> ships;

    public Player() {
        this.name = "Player";
        this.score = 0;
        this.healthPoints = 1;
        this.nrOfLives = 3;
    }

    public Player(String name, int score, int hP, int lives, LinkedList<Ship> ships) {
        this.name = name;
        this.score = score;
        this.healthPoints = hP;
        this.nrOfLives = lives;
        this.ships = ships;
    }

    public String getName() {
        return name;
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

    public void addScore(int score) {
        this.score += score;
    }

    public void decreaseHealthPoints(int i) {
        this.healthPoints -= i;
    }

    public void decreaseNrOfLives() {
        this.nrOfLives -=1 ;
    }

    private void setScore(int score) {
        this.score = score;
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

    @Override
    public String toString() {
        return this.name;
    }

}
