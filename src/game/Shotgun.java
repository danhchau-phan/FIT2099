package game;

import edu.monash.fit2099.engine.WeaponItem;

public class Shotgun extends WeaponItem {

    private static final int DAMAGE = 50;
    private int clipSize = 0;

    /**
     * Default constructor for shotgun
     */
    public Shotgun() {
        super("Shotgun", DisplayChar.SHOTGUN.toChar(), DAMAGE, "SHOT");
    }

    /**
     * Returns remaining ammo
     */
    public int getClipSize() {
        return clipSize;
    }

    /**
     * Reduces the clip size by 1 every time the shotgun is fired
     */
    public void fire() {
        clipSize -= 1;
        clipSize = Math.max(clipSize, 0);
    }

    /**
     * Reloading adds ammo to the shotgun clip.
     * @param rounds number of ammo added
     */
    public void reload(int rounds){
        clipSize += rounds;
    }

}
