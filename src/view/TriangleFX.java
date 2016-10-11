package view;

import javafx.scene.canvas.GraphicsContext;
import model.Triangle;
import model.TriangleOrientation;

/**
 * Created by Petros on 2016-10-11.
 */
public class TriangleFX {

    private Triangle triangle;
    private ThemeColor themeColor;

    public TriangleFX(Triangle triangle, ThemeColor themeColor) {
        this.triangle = triangle;
        this.themeColor = themeColor;
    }

    public void setThemeColor(ThemeColor themeColor) {
        this.themeColor = themeColor;
    }

    public void paint(GraphicsContext gc) {
        gc.setFill(themeColor.getColor(triangle.getColor()));
        gc.setStroke(themeColor.getColor(triangle.getColor()));

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
