package game;

import edu.monash.fit2099.engine.WeaponItem;


public class SniperRifle extends WeaponItem {

    private static final int DAMAGE = 60;
    private int clipSize = 0;

    /**
     * Default constructor for Sniper Rifle
     */
    public SniperRifle() {
        super("Sniper Rifle", DisplayChar.SNIPER.toChar(), DAMAGE, "SNIPED");
    }

    /**
     * Returns remaining ammo
     */
    @Override
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
     * Reloading adds ammo to the sniper rifle clip.
     * @param rounds number of ammo added
     */
    @Override
    public void reload(int rounds){
        clipSize += rounds;
    }

}
