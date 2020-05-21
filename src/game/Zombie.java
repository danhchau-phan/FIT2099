package game;

import java.util.Map;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.PickUpItemAction;
import edu.monash.fit2099.engine.Weapon;

/**
 * A Zombie.
 * 
 * This Zombie is pretty boring. It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	private Behaviour[] behaviours = { new AttackBehaviour(ZombieCapability.ALIVE), new HuntBehaviour(Human.class, 10),
			new WanderBehaviour() };
	/**
	 * The map the zombie is on.
	 */
	private GameMap map;
	/**
	 * Number of zombie's arms
	 */
	private int numArms = 2;
	/**
	 * Number of zombie's legs
	 */
	private int numLegs = 2;
	/**
	 * The punch-attack to bite-attack probability
	 */
	private static final Map<Integer, Double> PUNCH_TO_BITE_PROB = Map.of(2, 0.8, 1, 0.4, 0, 0.0);

	public Zombie(String name) {
		super(name, game.DisplayChar.ZOMBIE.toChar(), 100, ZombieCapability.UNDEAD);
	}

	/**
	 * Creates an intrinsic weapon subject to zombies number of arms remaining
	 * 
	 * @return IntrinsicWeapon (e.g: IntrinsicPunch, IntrinsicBite)
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {

		double rand = (new Random()).nextDouble();
		return (rand < PUNCH_TO_BITE_PROB.get(numArms)) ? new IntrinsicPunch() : new IntrinsicBite(this);
	}

	/**
	 * Set number of arms.
	 * 
	 * @param num number of lost arms
	 */
	public void loseArms(int num) throws IllegalArgumentException{
		if (num < 0 || num > this.numArms)
			throw new IllegalArgumentException("Lost arms is more than current arms");
		this.numArms = this.numArms - num;
	}

	/**
	 * Set number of legs.
	 * 
	 * @param num number of lost legs.
	 */
	public void loseLegs(int num) throws IllegalArgumentException {
		if (num < 0 || num > this.numLegs)
			throw new IllegalArgumentException("Lost legs is more than current legs");
		this.numLegs = this.numLegs - num;
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		list.clear();
		list.add(new AttackAction(this));
		return list;
	}

	/**
	 * If a Zombie can attack, it will. If not, it will chase any human within 10
	 * spaces. If no humans are close enough it will wander randomly.
	 * 
	 * Every turn, each Zombie has a 10% chance of saying “Braaaaains”. If there is
	 * a weapon at the Zombie’s location when its turn starts, the Zombie would pick
	 * it up.
	 * 
	 * If it loses one leg, its movement speed is halved. If it loses both legs, it
	 * cannot move at all.
	 * 
	 * @param actions    list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map        the map where the current Zombie is
	 * @param display    the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		this.map = map;
		double rand = (new Random()).nextDouble();
		if (rand < 0.1) {
			display.println("Braaaains");
		}

		if (numArms > 0) {
			for (Item item : map.locationOf(this).getItems()) {
				if (!(item instanceof ZombieArm || item instanceof ZombieLeg))
					(new PickUpItemAction(item)).execute(this, map);
					break;
			}
		}
		if (numLegs == 1 && !(lastAction instanceof DoNothingAction))
			return new DoNothingAction();
		for (Behaviour behaviour : behaviours) {
			if (numLegs == 0 && (behaviour instanceof HuntBehaviour || behaviour instanceof WanderBehaviour)) {
				continue;
			}
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

	/**
	 * Get the weapon the Zombie will use for attack.
	 * 
	 * If zombie has no weapon item in inventory, return intrinsic weapon (e.g:
	 * punch, bite) If zombie has weapon items but no arms left, or dropped the
	 * weapon with 1 arm left, also returns intrinsic weapon.
	 * 
	 * Otherwise, returns weapon item.
	 * 
	 * @return Weapon the Zombie's weapon item or intrinsic weapon
	 */
	@Override
	public Weapon getWeapon() {

		if (numArms == 0) {
			if (inventory.size() > 0)
				(new DropItemAction(inventory.get(0))).execute(this, map);
			return getIntrinsicWeapon();
		}

		if (numArms == 1) {
			double rand = (new Random()).nextDouble();
			if (inventory.size() > 0 && rand < 0.5) {
				(new DropItemAction(inventory.get(0))).execute(this, map);
				return getIntrinsicWeapon();
			}
		}
		return super.getWeapon();
	}
	/**
	 * Called when zombie's attacked.
	 *
	 * Knock off zombie's limb.
	 *
	 * @param points number of hitpoints to deduct.
	 */
	@Override
	public void hurt(int point) {
		super.hurt(point);
		Location location = map.locationOf(this);
		int rand = new Random().nextInt(3);
		try {
			if (rand <= 1) { 
				loseArms(1);
				location.addItem(new ZombieArm());
			} else {
				loseLegs(1);
				location.addItem(new ZombieLeg());
			}
		} catch (IllegalArgumentException e) {
		} 
	}
}
