package game;

import edu.monash.fit2099.engine.WeaponItem;

public class Shotgun extends WeaponItem {

    public static final int DAMAGE = 50;

    public Shotgun() {
        super("Shotgun", DisplayChar.SHOTGUN.toChar(), DAMAGE, "SHOT");
    }
}
