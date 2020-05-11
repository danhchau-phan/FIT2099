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
 * This Zombie is pretty boring.  It needs to be made more interesting.
 * 
 * @author ram
 *
 */
public class Zombie extends ZombieActor {
	private Behaviour[] behaviours = {
			new AttackBehaviour(ZombieCapability.ALIVE),
			new HuntBehaviour(Human.class, 10),
			new WanderBehaviour()
	};
	
	private GameMap map;
	
	private int numArms = 2;
	private int numLegs = 2;
	
	private static final Map<Integer, Double> PUNCH_TO_BITE_PROB = Map.of(2,0.8,1,0.4,0,0.0);

	public Zombie(String name) {
		super(name, 'Z', 100, ZombieCapability.UNDEAD);
	}

	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		
		double rand = (new Random()).nextDouble(); 
		return (rand < PUNCH_TO_BITE_PROB.get(numArms)) ? new IntrinsicPunch() : new IntrinsicBite(this);
	}

	public int getNumArms() {
		return this.numArms;
	}
	
	public int getNumLegs() {
		return this.numLegs;
	}
	
	public void loseArms(int num) {
		if (num > 0 && num <= this.numArms)
			this.numArms = this.numArms - num;
	}
	
	public void loseLegs(int num) {
		if (num > 0 && num <= this.numLegs)
			this.numLegs = this.numLegs - num;
	}
	
	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		list.clear();
		list.add(new LimbOffAttack(this));
		return list;
	}
	
	/**
	 * If a Zombie can attack, it will.  If not, it will chase any human within 10 spaces.  
	 * If no humans are close enough it will wander randomly.
	 * 
	 * @param actions list of possible Actions
	 * @param lastAction previous Action, if it was a multiturn action
	 * @param map the map where the current Zombie is
	 * @param display the Display where the Zombie's utterances will be displayed
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		this.map = map;
		// Every turn, each Zombie should have a 10% chance of saying �Braaaaains�
		double rand = (new Random()).nextDouble();
		if (rand < 0.1) {
			display.println("Braaaains");
		}
		
		// If there is a weapon at the Zombie�s location when its turn starts, the Zombie should pick it up.
		if (numArms > 0) {
			for (Item item : map.locationOf(this).getItems()) {
				if (!(item instanceof ZombieArm || item instanceof ZombieLeg))
					(new PickUpItemAction(item)).execute(this, map);
				break;
			}
		}
		
		for (Behaviour behaviour : behaviours) {
			// If it loses one leg, its movement speed is halved
			if (numLegs == 1 && !(lastAction instanceof DoNothingAction)) 
				break;
			// If it loses both legs, it cannot move at all
			if (numLegs == 0 && (behaviour instanceof HuntBehaviour || behaviour instanceof WanderBehaviour)) {
				continue;
			}
			Action action = behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();	
	}

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
}
