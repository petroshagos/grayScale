package view;

import javafx.scene.paint.Color;

/**
 * @author Petros Hagos & Dag Oldenburg.
 */
public enum ThemeColor {
    THEME_GRAY("GRAY", Color.rgb(50,50,50), Color.rgb(125,125,125), Color.rgb(200,200,200),
        Color.rgb(160,160,160), Color.rgb(255,255,255)),
    THEME_BLUE("BLUE", Color.rgb(0,26,68), Color.rgb(67, 94, 137), Color.rgb(96, 141, 214),
        Color.rgb(53, 104, 186), Color.rgb(255,255,255)),
    THEME_RED("RED", Color.rgb(73, 3, 11), Color.rgb(160, 85, 93), Color.rgb(234, 53, 75),
        Color.rgb(211, 91, 105), Color.rgb(255,255,255)),
    POWERUP("POWERUP", Color.rgb(244, 66, 217), Color.rgb(241, 244, 24), Color.rgb(24, 244, 138),
        Color.rgb(24, 244, 226), Color.rgb(35, 244, 24));
    


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

    public String getName() {
        return name;
    }
    private  final String name;
    private final Color color0;
    private final Color color1;
    private final Color color2;
    private final Color color3;
    private final Color color4;

    ThemeColor(String name, Color color0, Color color1, Color color2, Color color3, Color color4) {
        this.name = name;
        this.color0 = color0;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
    }
}

