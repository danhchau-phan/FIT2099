package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A zombie Club crafted using a zombie arm. An advance weapon.
 *
 */

public class ZombieClub extends WeaponItem {

    public ZombieClub() {
        super("Zombie Club", DisplayChar.ZOMBIECLUB.toChar(), 20, "whacks");
    }

}
