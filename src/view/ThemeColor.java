package view;

import javafx.scene.paint.Color;

/**
 * Created by Petros on 2016-10-09.
 */
public enum ThemeColor {
    THEME_GRAY(Color.rgb(50,50,50), Color.rgb(125,125,125), Color.rgb(200,200,200),
        Color.rgb(160,160,160), Color.rgb(255,255,255)),
    THEME_BLUE(Color.rgb(0,26,68), Color.rgb(67, 94, 137), Color.rgb(106, 154, 232),
        Color.rgb(53, 104, 186), Color.rgb(255,255,255)),
    THEME_RED(Color.rgb(73, 3, 11), Color.rgb(160, 85, 93), Color.rgb(234, 53, 75),
        Color.rgb(211, 91, 105), Color.rgb(255,255,255));


    public Color getColor(int i) {
        switch (i) {
            case 0: return color0;
            case 1: return color1;
            case 2: return color2;
            case 3: return color3;
            case 4: return color4;
            default: return Color.rgb(0,0,0);
        }
    }
    public Color getColor2() {
        return color2;
    }
    public Color getColor3() {
        return color3;
    }
    public Color getColor4() {
        return color4;
    }
    public Color getColor5() {
        return color1;
    }


    private final Color color0;
    private final Color color1;
    private final Color color2;
    private final Color color3;
    private final Color color4;

    ThemeColor(Color color0, Color color1, Color color2, Color color3, Color color4) {
        this.color0 = color0;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
    }
}

