package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Petros on 2016-10-26.
 */
public class CanvasView extends Canvas {

    private Canvas canvas;
    private Text t = new Text();


    public CanvasView(ThemeColor themeColor) {
        canvas = new Canvas(808,400);
        t.setX(340);
        t.setY(220);
        t.setFont(new Font(40));
        t.setFill(themeColor.getColor(6));
    }

    public Canvas getCanvasView() {
        return canvas;
    }

    public void setCanvasView(Canvas canvas) {
        this.canvas = canvas;
    }

    public void updateWaveText(int wave){
        t.setText("Wave: " + wave);
    }
    public void updateWaveText(){
        t.setText("");
    }
    public Text getText(){
        return t;
    }
}
