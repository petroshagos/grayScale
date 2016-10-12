package view.FX;

import javafx.scene.canvas.GraphicsContext;
import model.Shape.Rectangle;
import view.ThemeColor;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class RectangleFX extends ShapeFX {

    private Rectangle rectangle;

    public RectangleFX(ThemeColor themeColor, Rectangle rectangle) {
        super(themeColor);
        this.rectangle = rectangle;
    }

    @Override
    public void paint(GraphicsContext gc) {
        gc.setFill(super.getThemeColor().getColor(rectangle.getColor()));
        gc.setStroke(super.getThemeColor().getColor(rectangle.getColor()));
        if (rectangle.isFilled()) {
            gc.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        }
        else {
            gc.strokeRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
        }
    }
}