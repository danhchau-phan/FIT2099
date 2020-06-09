package game;

/**
 * Shotgun Ammo can be used to refill and fire the shotgun.
 *
 */

public class ShotgunAmmo extends PortableItem {

    private int rounds = 2;

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

    /**
     * When the ammo is used, this method sets the rounds to 0. Prevents clip being reused.
     */
    @Override
    public void emptyClip() {
        rounds = 0;
    }
}
