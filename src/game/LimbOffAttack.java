package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
/**
 * Special attack action that can knock off zombie's limbs 
 *
 */
public class LimbOffAttack extends AttackAction {
	public final static double PROBABILITY = 0.25;
	/**
	 * Constructor.
	 * 
	 * @param target the actor to be attacked
	 */
	public LimbOffAttack(Zombie target) {
		super(target);
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		
		if (weapon instanceof Chanceable && !((Chanceable) weapon).isSuccessful()) {
			return actor + " misses " + target + ".";
		}
		
		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		if (this.isSuccessful()) {
			Location location = map.locationOf(target);
			loseLimbs((Zombie) target, location);
		}
		
		if (!target.isConscious()) {
			result += super.killTarget(map);
		}
		
		return result;
	}
	/**
	 * Makes zombie's limb to fall off
	 * @param zombie the target
	 * @param location the location of the attack
	 */
	private void loseLimbs(Zombie zombie, Location location) {
		if (zombie.getNumArms() > 0) { 
			zombie.loseArms(1);
			location.addItem(new ZombieArm());
		} else if (zombie.getNumLegs() > 0) {
			location.addItem(new ZombieLeg());
		}
	}

	/**
	 * Checks if attack is successful
	 * @return true of false depend on success probability
	 */
	private boolean isSuccessful() {
		double rand = (new Random()).nextDouble();
		return (rand < PROBABILITY);
	}
	

}
