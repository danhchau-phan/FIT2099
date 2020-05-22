package game;

import java.util.Random;

import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Actor;

/**
 * Class that represent an intrinsic bite of an unarmed actor
 */
public class IntrinsicBite extends IntrinsicWeapon {
	private final static double HIT_PROBABILITY = 0.5;
	public final static int DAMAGE = 15;
	public final static int HEAL_POINT = 5;
	private Zombie zombie;

	public IntrinsicBite(Zombie zombie) {
		super(DAMAGE, "bites");
		this.zombie = zombie;
	}

	/**
	 * Did the bite occur successfully?
	 * 
	 * @return damage or 0 depending on hit probability
	 */
	
	public int damage() {
		double rand = (new Random()).nextDouble();
		if (rand < HIT_PROBABILITY) {
			heal();
			return super.damage();
		}
		return 0;
	}
	/**
	 * Restore health point to the Actor if the bite is happened
	 */
	private void heal() {
		zombie.heal(HEAL_POINT);
	}

}
