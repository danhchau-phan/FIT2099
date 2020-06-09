package game;

import edu.monash.fit2099.engine.WeaponItem;


public class SniperRifle extends WeaponItem {

    private static final int DAMAGE = 60;

    public SniperRifle() {
        super("Sniper Rifle", DisplayChar.SNIPER.toChar(), DAMAGE, "SNIPED");
    }

}
