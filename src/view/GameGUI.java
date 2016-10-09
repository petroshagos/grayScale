
package view;

import model.GameModel;

/**
 *
 * @author Petros
 */
public class GameGUI {

    private ThemeColor themeColor;
    private GameModel model;

    public GameGUI(ThemeColor themeColor, GameModel model) {
        this.themeColor = themeColor;
        this.model = model;
    }

    public ThemeColor getThemeColor() {
        return themeColor;
    }

    public void setThemeColor(ThemeColor themeColor) {
        this.themeColor = themeColor;
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
