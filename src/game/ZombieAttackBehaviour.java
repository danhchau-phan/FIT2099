package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DropItemAction;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class ZombieAttackBehaviour extends AttackBehaviour {

	public ZombieAttackBehaviour(ZombieCapability attackableTeam) {
		super(attackableTeam);
	}
	
	/**
	 * Returns an AttackAction that attacks an adjacent attackable Actor.
	 * If a Zombie loses one arm, its probability of punching (rather than biting) is halved and it has a
	 * 50% chance of dropping any weapon it is holding. If it loses both arms, it definitely drops any
	 * weapon it was holding.
	 * 
	 * Actors are attackable if their ZombieCapability matches the 
	 * "undeadness status" set 
	 */
	public Action getAction(Zombie zombie, GameMap map) {
		double rand = (new Random()).nextDouble();
		if (zombie.getInventory().size() > 0 && zombie.getNumArms() == 0) 
			return new DropItemAction(zombie.getInventory().get(0));
		if (zombie.getInventory().size() > 0 && zombie.getNumArms() == 1 && rand < 0.5) {
			return new DropItemAction(zombie.getInventory().get(0));
		}
		// Is there an attackable Actor next to me?
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(zombie).getExits());
		Collections.shuffle(exits);
		
		for (Exit e: exits) {
			if (!(e.getDestination().containsAnActor()))
				continue;
			if (e.getDestination().getActor().hasCapability(attackableTeam)) {
				return new AttackAction(e.getDestination().getActor());
			}
		}
		return null;
	}
	
}
