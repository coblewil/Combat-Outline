package game1.pkg1.support;

/**
 *
 * @author acob1
 */
public enum Move {
    NOTHING("Nothing", "...Utter futility. Akin to misrule.", 0, (short) 0, (short)0, (short) 0, Spire.NORMAL, (byte) 0), //SpecialEffect here?
    SINGE("Singe", null, 1, (short) 2, (short)0, (short) 0, Spire.FIRE, (byte) 1),
    SEAR("Sear", null, 2, (short) 5, (short) 1, (short) 1, Spire.FIRE, (byte) 2),
    DRIP("Drip", null, 3, (short) 2, (short)0, (short) 0, Spire.WATER, (byte) 1),
    DRAIN("Drain", null, 4, (short) 5, (short) 1, (short) 1, Spire.WATER, (byte) 2),
    GUST("Gust", null, 5, (short) 2, (short)0, (short) 0, Spire.WIND, (byte) 1),
    GALE("Gale", null, 6, (short) 5, (short) 1, (short) 1, Spire.WIND, (byte) 2),
    PEBBLE("Pebble", null, 7, (short) 2, (short)0, (short) 0, Spire.EARTH, (byte) 1),
    STONE("Stone", null, 8, (short) 5, (short) 1, (short) 1, Spire.EARTH, (byte) 2);

    private final String name; //move name
    private final String desc; //move description
    private final int moveCode; //move number/index
    private final short damage; //damage
    private final short exploits;
    private final short effectValue;
    private final Spire spire;
    private final byte animCode;

    Move(String name, String desc, int moveCode, short damage, short effect, short effectValue, Spire typeSpire, byte animCode) {
        this.name = name;
        this.desc = desc;
        this.moveCode = moveCode;
        this.damage = damage;
        this.exploits = effect;
        this.effectValue = effectValue;
        this.spire = typeSpire;
        this.animCode = animCode;
    }

    public int getAnimCode() {
        return animCode;
    }

    public static Move lookUpMove(int req) {
        Move result = null;
        for (Move x : values()) {
            if (req == x.moveCode) {
                result = x;
            }
        }
        return result;
    }

}
