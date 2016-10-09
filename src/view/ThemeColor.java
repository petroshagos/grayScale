package view;

/**
 * Created by Petros on 2016-10-09.
 */
public enum ThemeColor {
    THEME_GRAY(new int[] {50,50,50}, new int[] {125,125,125}, new int[] {200,200,200}, new int[] {160,160,160}, new int[] {255,255,255}),
    THEME_BLUE(new int[] {0,26,68}, new int[] {67, 94, 137}, new int[] {106, 154, 232}, new int[] {53, 104, 186}, new int[] {255,255,255}),
    THEME_RED(new int[] {73, 3, 11}, new int[] {160, 85, 93}, new int[] {234, 53, 75}, new int[] {211, 91, 105}, new int[] {255,255,255});


    public int[] getColor1() {
        return color1;
    }
    public int[] getColor2() {
        return color2;
    }
    public int[] getColor3() {
        return color3;
    }
    public int[] getColor4() {
        return color4;
    }
    public int[] getColor5() {
        return color5;
    }


    private final int[] color1;
    private final int[] color2;
    private final int[] color3;
    private final int[] color4;
    private final int[] color5;

    private ThemeColor(int[] color1, int[] color2, int[] color3, int[] color4, int[] color5) {
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.color4 = color4;
        this.color5 = color5;
    }
}

