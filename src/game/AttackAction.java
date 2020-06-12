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
	 * Default constructor
	 */
	public AttackAction() {}

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
		int damage = weapon.damage();
		if (damage == 0) {
			return actor + " misses " + target + ".";
		}
		
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		target.hurt(damage);
		
		try {
			result += removeTarget(map);
		} catch (Exception e) {}

		return result;
	}
	/**
	 * Remove target from map and create corpse if target is a human
	 * @param map The map the actor is on
	 * @return a description of what happened that is reported back to execute()
	 */
	private String removeTarget(GameMap map) throws Exception {
		if (target.isConscious()) {
			throw new Exception(this.target.toString() + " is still conscious. Cannot kill.");
		}
		
		if (target.hasCapability(ZombieCapability.ALIVE)) {
			Corpse corpse = new Corpse("dead" + target);
			map.locationOf(target).addItem(corpse);
		}
		
		return killTarget(target, map);
	}
	/**
	 * Kill target and remove target from map, drop inventory items of target to the ground
	 * @param target target of the attack
	 * @param map The map actor is on
	 * @return String
	 */
	protected String killTarget(Actor target, GameMap map){

        // Drops inventory items
        Actions dropActions = new Actions();
        for (Item item : target.getInventory())
            dropActions.add(item.getDropAction());
        for (Action drop : dropActions)
            drop.execute(target, map);

        map.removeActor(target);
        return System.lineSeparator() + target + " was killed.";
    }
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
