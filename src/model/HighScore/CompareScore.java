package model.HighScore;

import model.Player;

import java.util.Comparator;

/**
 * @author Petros Hagos & Dag Oldenburg
 */
public class CompareScore implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return p1.getScore() - p2.getScore();
    }
}
