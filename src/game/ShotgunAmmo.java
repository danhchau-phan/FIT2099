package game;

import edu.monash.fit2099.engine.Item;

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

    public static int getROUNDS() {
        return ROUNDS;
    }
}
