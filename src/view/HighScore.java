package view;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.HighScore.HighScoreList;
import model.Player;

import java.io.IOException;

/**
 *
 * @author Petros Hagos & Dag Oldenburg
 */
public class HighScore extends VBox {

    TableView<Player> table = new TableView<Player>();
    HighScoreList highScoreList;
    VBox vbox = new VBox();

    public HighScore() throws IOException {
        this.highScoreList = new HighScoreList();
        final Label label = new Label("HighScore");
        label.setFont(new Font("Arial", 20));
        table.setEditable(false);
        TableColumn nameCol = new TableColumn("Player");
        nameCol.setPrefWidth(150);
        TableColumn scoreCol = new TableColumn("Score");
        scoreCol.setPrefWidth(150);
        table.getColumns().addAll(nameCol, scoreCol);
        vbox.setSpacing(5);
        vbox.getChildren().addAll(label, table);
    }

    public VBox getVBox() {
        return vbox;
    }

}

