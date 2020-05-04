package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;

public class LimbOffAttack extends AttackAction implements Chanceable{
	public final static double PROBABILITY = 0.25;
	
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
		// Any attack on a Zombie that causes damage has a chance to knock at least one of its limbs off 
		if (this.isSuccessful()) {
			// Lost limbs drop to the ground at the Zombie’s location
			Location location = map.locationOf(target);
			loseLimbs((Zombie) target, location);
		}
		
		if (!target.isConscious()) {
			Item corpse = new PortableItem("dead " + target, '%');
			map.locationOf(target).addItem(corpse);
			
			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}
		
		return result;
	}
	
	private void loseLimbs(Zombie zombie, Location location) {
		if (zombie.getNumArms() > 0) { 
			zombie.loseArms(1);
			location.addItem(new ZombieArm());
		} else if (zombie.getNumLegs() > 0) {
			location.addItem(new ZombieLeg());
		}
	}

	@Override
	public boolean isSuccessful() {
		double rand = (new Random()).nextDouble();
		return (rand < PROBABILITY);
	}
	

}
