package game.shotgun;

import game.DisplayChar;
import game.PortableItem;

/**
 * Shotgun Ammo can be used to refill and fire the shotgun.
 *
 */

public class ShotgunAmmo extends PortableItem {

    private int rounds = 12;

    /**
     * Default constructor of Shotgun ammo
     */
    public ShotgunAmmo() {
        super("Shotgun Ammo", DisplayChar.SHOTGUNAMMO.toChar());
    }

    /**
     * Returns the refill amount of the ammo
     * @return number of rounds
     */
    @Override
    public int getRounds() {
        return rounds;
    }
    
    
}
