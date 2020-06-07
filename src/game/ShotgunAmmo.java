package game;

/**
 * Shotgun Ammo can be used to refill and fire the shotgun.
 *
 */

public class ShotgunAmmo extends PortableItem {

    private static final int ROUNDS = 12;

    /**
     * Default constructor of Food
     */
    public ShotgunAmmo() {
        super("Shotgun Ammo", DisplayChar.SHOTGUNAMMO.toChar());
    }

    /**
     * Returns the refill amount of the ammo
     * @return number of rounds
     */
    public static int getROUNDS() {
        return ROUNDS;
    }
}
