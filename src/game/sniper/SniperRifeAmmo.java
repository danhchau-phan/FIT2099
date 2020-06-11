package game.sniper;

import game.DisplayChar;
import game.PortableItem;

public class SniperRifeAmmo extends PortableItem{

    /**
     * Sniper Ammo can be used to refill and fire the sniper rifle.
     *
     */
    private int rounds = 5;

    /**
     * Default constructor of Sniper ammo
     */
    public SniperRifeAmmo() {
        super("Sniper Ammo", DisplayChar.SNIPERAMMO.toChar());
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
