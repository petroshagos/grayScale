package model;

import java.util.Comparator;

/**
 * Created by Petros on 2016-10-09.
 */
public class CompareScore implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return p2.getScore() - p1.getScore();
    }
}
