package game;

public class SniperAmmo extends PortableItem{

    /**
     * Sniper Ammo can be used to refill and fire the sniper rifle.
     *
     */
    private static final int ROUNDS = 5;

    /**
     * Default constructor of Sniper ammo
     */
    public SniperAmmo() {
        super("Sniper Ammo", DisplayChar.SNIPERAMMO.toChar());
    }

    /**
     * Returns the refill amount of the ammo
     * @return number of rounds
     */
    public static int getROUNDS() {
        return ROUNDS;
    }
}
