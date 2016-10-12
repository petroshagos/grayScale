package model.HighScore;

import model.Player;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @author Petros Hagos & Dag Oldenburg
 */
public class HighScoreList {

    private LinkedList<Player> highScoreList;

    public HighScoreList() {
        this.highScoreList = new LinkedList<>();
    }

    public void addPlayer(Player p){
        this.highScoreList.add(p);
    }

    public LinkedList<Player> getHighScoreList() {
        LinkedList<Player> temp = new LinkedList<>();
        temp.addAll(this.highScoreList);
        Collections.sort(temp, new CompareScore());
        return temp;
    }

}
