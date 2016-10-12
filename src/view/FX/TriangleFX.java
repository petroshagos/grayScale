package view.FX;

import javafx.scene.canvas.GraphicsContext;
import model.Shape.Triangle;
import model.Shape.TriangleOrientation;
import view.ThemeColor;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public class TriangleFX extends ShapeFX{

    private Triangle triangle;

    public TriangleFX(ThemeColor themeColor, Triangle triangle) {
        super(themeColor);
        this.triangle = triangle;
    }

    @Override
    public void paint(GraphicsContext gc) {
        gc.setFill(super.getThemeColor().getColor(triangle.getColor()));
        gc.setStroke(super.getThemeColor().getColor(triangle.getColor()));

        if (triangle.getOrientation() == TriangleOrientation.UpperLeft) {
            if (triangle.isFilled()) {
                gc.fillPolygon(new double[]{triangle.getX(), triangle.getX()+triangle.getWidth(), triangle.getX()+triangle.getWidth()},
                        new double[]{triangle.getY(), triangle.getY(), triangle.getY()+triangle.getHeight()}, 3);
            }
            else {
                gc.strokePolygon(new double[]{triangle.getX(), triangle.getX()+triangle.getWidth(), triangle.getX()+triangle.getWidth()},
                        new double[]{triangle.getY(), triangle.getY(), triangle.getY()+triangle.getHeight()}, 3);
            }
        }

        if (triangle.getOrientation() == TriangleOrientation.UpperRight) {
            if (triangle.isFilled()) {
                gc.fillPolygon(new double[]{triangle.getX(), triangle.getX()+triangle.getWidth(), triangle.getX()},
                    new double[]{triangle.getY(), triangle.getY()+triangle.getHeight(), triangle.getY()+triangle.getHeight()}, 3);
            }
            else {
                gc.strokePolygon(new double[]{triangle.getX(), triangle.getX()+triangle.getWidth(), triangle.getX()},
                        new double[]{triangle.getY(), triangle.getY()+triangle.getHeight(), triangle.getY()+triangle.getHeight()}, 3);
            }
        }

        if (triangle.getOrientation() == TriangleOrientation.LowerLeft) {
            if (triangle.isFilled()) {
                gc.fillPolygon(new double[]{triangle.getX(), triangle.getX()+triangle.getWidth(), triangle.getX()+triangle.getWidth()},
                        new double[]{triangle.getY()+triangle.getHeight(), triangle.getY()+triangle.getHeight(), triangle.getY()}, 3);
            }
            else {
                gc.strokePolygon(new double[]{triangle.getX(), triangle.getX()+triangle.getWidth(), triangle.getX()+triangle.getWidth()},
                        new double[]{triangle.getY()+triangle.getHeight(), triangle.getY()+triangle.getHeight(), triangle.getY()}, 3);
            }
        }

        if (triangle.getOrientation() == TriangleOrientation.LowerRight) {
            if (triangle.isFilled()) {
                gc.fillPolygon(new double[]{triangle.getX(), triangle.getX(), triangle.getX()+triangle.getWidth()},
                        new double[]{triangle.getY(), triangle.getY()+triangle.getHeight(), triangle.getY()+triangle.getHeight()}, 3);
            }
            else {
                gc.strokePolygon(new double[]{triangle.getX(), triangle.getX(), triangle.getX()+triangle.getWidth()},
                        new double[]{triangle.getY(), triangle.getY()+triangle.getHeight(), triangle.getY()+triangle.getHeight()}, 3);
            }
        }
    }
}
