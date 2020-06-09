package game;

import edu.monash.fit2099.engine.WeaponItem;

/**
 * A zombie Maze crafted using a zombie leg. An advance weapon.
 *
 */

public class ZombieMaze extends WeaponItem {

    public ZombieMaze() {
        super("Zombie Maze", DisplayChar.ZOMBIEMAZE.toChar(), 30, "sliced");
    }
}
