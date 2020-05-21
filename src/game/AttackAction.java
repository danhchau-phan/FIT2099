package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target) {
		this.target = target;
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
		
		try {
			result += terminateTarget(map);
		} catch (Exception e) {}

		return result;
	}
	/**
	 * Remove target from map, drop inventory items and create corpse if target is a human
	 * @param map The map the actor is on
	 * @return a description of what happened that is reported back to execute()
	 */
	protected String terminateTarget(GameMap map) throws Exception {
		if (target.isConscious()) {
			throw new Exception(this.target.toString() + " is still conscious. Cannot terminate.");
		}
		Actions dropActions = new Actions();
		for (Item item : target.getInventory())
			dropActions.add(item.getDropAction());
		for (Action drop : dropActions)		
			drop.execute(target, map); 
		
		if (target.hasCapability(ZombieCapability.ALIVE)) {
			Corpse corpse = new Corpse("dead" + target);
			map.locationOf(target).addItem(corpse);
		}

		map.removeActor(target);
		
		return System.lineSeparator() + target + " is killed.";
	}
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
