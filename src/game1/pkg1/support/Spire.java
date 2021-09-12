package game1.pkg1.support;

import java.awt.Color;

/**
 *
 * @author acob1
 */
public enum Spire {
    NORMAL("Normal", 0, null, null, null, null),
    FIRE("Fire", 1, new Color(254, 68, 0), null, null, Color.RED),
    WATER("Water", 2, Color.BLUE, null, null, null),
    WIND("Wind", 3, new Color(220, 220, 220), null, null, new Color(151, 251, 151)),
    EARTH("Earth", 4, new Color(128, 80, 28), null, null, null),
    SPECTRAL("Spectral", 5, new Color(175, 238, 238), null, null, null),
    PLANT("Plant", 6, new Color(0, 127, 0), null, null, Color.GREEN),
    LIGHTNING("Lightning", 7, new Color(255, 255, 0), null, null, null), //or yellow
    STORM("Storm", 8, new Color(127, 0, 127), null, null, null),
    ARCANE("Arcane", 9, new Color(254, 139, 0), null, null, null),
    CELESTIAL("Celestial", 10, new Color(0, 0, 72), null, null, null),
    DARK("Dark", 11, Color.BLACK, null, null, null),
    LIGHT("Light", 12, Color.WHITE, null, null, null), //or white
    FUNDAMENTAL("Fundamental", 13, Color.GRAY, null, null, null);
    private final String name;
    private final int typeCode;
    private final Color color1;
    private final Color color2;
    private final Color color3;
    private final Color altColor1;
    private final Color[] altColor2Array = {null, null, new Color(238, 155, 255), null, new Color(177, 255, 255), new Color(0, 213, 0), null, new Color(255, 120, 0), new Color(236, 204, 47), null, null, null, new Color(127, 127, 127)};

    Spire(String name, int typeCode, Color color1, Color color2, Color color3, Color altColor1) {
        this.name = name;
        this.typeCode = typeCode;
        this.color1 = color1;
        this.color2 = color2;
        this.color3 = color3;
        this.altColor1 = altColor1;
    }

    public String getName() {
        return name;
    }

    public int getTypeCode() {
        return typeCode;
    }

    public Color getColor1() {
        return color1;
    }

    public Color getColor2() {
        return color2;
    }

    public Color getColor3() {
        return color3;
    }

    public Color getAltColor1() {
        return altColor1;
    }

    public Color[] getAltColor2Array() {
        return altColor2Array;
    }
}
