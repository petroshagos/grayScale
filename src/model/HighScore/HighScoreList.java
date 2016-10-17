package model.HighScore;

import model.Player;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

import static java.lang.System.exit;

/**
 * @author Petros Hagos & Dag Oldenburg
 */
public class HighScoreList implements Serializable {

    private LinkedList<Player> highScoreList;

    public HighScoreList() throws FileNotFoundException, IOException {
        this.highScoreList = new LinkedList<>();
        try {
            serialise();
        }catch (IOException ie){
        }   System.out.println("HighScoreList() IOException");
        try {
            deserialise();
        } catch (ClassNotFoundException e) {
            //TODO: alerts
            System.out.println("HighScoreList() ClassNotFoundException");
            exit(0);
        } catch (FileNotFoundException fe) {
            //TODO: alerts
            System.out.println("HighScoreList() FileNotFoundException");
            exit(0);
        }
    }

    public void addPlayer(Player p) {
        this.highScoreList.add(p);
    }

    public LinkedList<Player> getHighScoreList() {
        LinkedList<Player> temp = new LinkedList<>();
        temp.addAll(this.highScoreList);
        Collections.sort(temp, new CompareScore());
        return temp;
    }

    private void serialise() throws IOException {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try {
            fout = new FileOutputStream("highScore.ser");
            oos = new ObjectOutputStream(fout);
            oos.writeObject(highScoreList);
            System.out.println("Serialisation Complete");

        } finally {
            try {
                if (fout != null) {
                    fout.close();
                }
            } catch (IOException ie) {
            }
        }
    }

    public void deserialise() throws ClassNotFoundException, FileNotFoundException {

        ObjectInputStream ois = null;
        FileInputStream fin = null;
        try {
            fin = new FileInputStream("highScore.ser");
            ois = new ObjectInputStream(fin);
            LinkedList<Player> highScoreList = (LinkedList<Player>) ois.readObject();
            System.out.println("Deserialisation complete");
            for (Player p : highScoreList) {
                System.out.println(p.toString());
            }

        } catch (IOException ex) {
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ie) {
            }
        }
    }

}
