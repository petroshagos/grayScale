package view;

import javafx.scene.control.TextInputDialog;
import model.Player;

import java.util.Optional;

public class StartScreen {

    public static void startScreen(Player player) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("grayScale");
        dialog.setHeaderText("Enter your name: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            player.setName(result.get());
        }
    }
}