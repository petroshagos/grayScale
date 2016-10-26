package view;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.HighScore.HighScoreList;
import javafx.collections.FXCollections;
import model.Player;

import java.io.IOException;

/**
 *
 * @author Petros Hagos & Dag Oldenburg
 */
public class HighScore extends VBox {

    private TableView<Player> table = new TableView<>();
    private final ObservableList<Player> data =
            FXCollections.observableArrayList(
            );
    private HighScoreList highScoreList;
    private VBox vbox = new VBox();

    public HighScore() throws IOException {
        this.highScoreList = new HighScoreList();
        for (int i=0; i<highScoreList.getHighScoreList().size();i++) {
            data.add(highScoreList.getHighScoreList().get(i));
        }

        final Label label = new Label("HighScore");
        label.setFont(new Font("Arial", 20));
        table.setEditable(false);
        TableColumn nameCol = new TableColumn("Player");
        nameCol.setPrefWidth(150);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("name"));
        TableColumn scoreCol = new TableColumn("Score");
        scoreCol.setPrefWidth(150);
        scoreCol.setCellValueFactory(
                new PropertyValueFactory<Player, String>("score"));
        table.setItems(data);
        table.getColumns().addAll(nameCol, scoreCol);
        vbox.setSpacing(5);
        vbox.getChildren().addAll(label, table);
    }

    public VBox getVBox() {
        return vbox;
    }

}

