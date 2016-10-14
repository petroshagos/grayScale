package model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class Player implements Serializable{
    private String name;
    private int score;
    private int nrOfLives;
    private LinkedList<Ship> ships = new LinkedList<>();

    public Player() {
        this.name = "Player";
        this.score = 0;
        this.nrOfLives = 3;
        this.ships.add(new PlayerShip());
    }

    public Player(String name, int score, int hP, int lives, LinkedList<Ship> ships) {
        this.name = name;
        this.score = score;
        this.nrOfLives = lives;
        this.ships = ships;
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

    public LinkedList<Ship> getShips() {
        return this.ships;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void decreaseHealthPoints(int i) {
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

    private void setShips(LinkedList<Ship> ships) {
        this.ships = ships;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
