package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.WeaponItem;

/**
 * A zombie arm dropped during intrinsic attacks. A primitive weapon
 *
 */
public class ZombieArm extends WeaponItem {

	public ZombieArm() {
		super("Zombie Arm", DisplayChar.ZOMBIEARM.toChar(), 10, "whacks");
		// TODO Auto-generated constructor stub
	}
	
	public List<Action> getAllowableActions() {
		return Arrays.asList(new CraftWeaponAction());
    	
    }
}
