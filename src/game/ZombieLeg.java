package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * A zombie leg dropped during intrinsic attacks. A primitive weapon
 *
 */

public class ZombieLeg extends WeaponItem {

    public ZombieLeg() {
        super("Zombie leg", DisplayChar.ZOMBIELEG.toChar(), 10, "whacks");
        // TODO Auto-generated constructor stub
    }
    
    public List<Action> getAllowableActions() {
		return Arrays.asList(new CraftWeaponAction());
    	
    }
}
