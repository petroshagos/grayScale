package model.HighScore;

import model.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Petros Hagos & Dag Oldenburg
 */
public class HighScoreList {

    private ArrayList<Player> highScoreList;

    public HighScoreList() {
        this.highScoreList = new ArrayList<>();
        /*try {
            deserialise();
        } catch (ClassNotFoundException e) {
            //TODO: alerts
            System.out.println("HighScoreList() ClassNotFoundException");
            exit(0);
        } catch (FileNotFoundException fe) {
            //TODO: alerts
            System.out.println("HighScoreList() FileNotFoundException");
            exit(0);
        } try {
            serialise();
        } catch (IOException ie){
            System.out.println("HighScoreList() IOException");
        }*/
    }

    public void addPlayer(Player p) {
        this.highScoreList.add(p);
    }

    public ArrayList<Player> getHighScoreList() {
        ArrayList<Player> temp = new ArrayList<>();
        temp.addAll(this.highScoreList);
        Collections.sort(temp, new CompareScore());
        return temp;
    }

    public void serialise() throws IOException {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(
                    new FileOutputStream("highscore.ser"));
            out.writeObject(highScoreList);
            System.out.println("Serialisation Complete");
        } catch (IOException ie) {

        }
        finally {
            try {
                if(out != null)	out.close();
            } catch(Exception e) {}
        }
    }

    public void deserialise() throws ClassNotFoundException, FileNotFoundException {

        ObjectInputStream in = null;

        try {
            in = new ObjectInputStream(new FileInputStream("highscore.ser"));
            highScoreList = (ArrayList<Player>) in.readObject();
            System.out.println("Deserialisation complete");
        } catch (IOException ex) {
            for (int i=0;i<5;i++) {
                Player p = new Player();
                p.setName("");
                highScoreList.add(p);
            }

        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ie) {
            }
        }
    }

    @Override
    public String toString() {
        String temp = "";
        for (Player p: highScoreList) {
            temp += p.getName();
        }
        return temp;
    }

}
