package game;

import java.util.Random;

import edu.monash.fit2099.engine.IntrinsicWeapon;

/**
 * Class that represent an intrinsic punch of an unarmed actor
 */
public class IntrinsicPunch extends IntrinsicWeapon {
	private final static double HIT_PROBABILITY = 0.5;
	public final static int DAMAGE = 15;

	public IntrinsicPunch() {
		super(DAMAGE, "punches");
	}
	/**
	 * Did the bite occur successfully?
	 * 
	 * @return damage or 0 depending on hit probability
	 */
	
	public int damage() {
		double rand = (new Random()).nextDouble();
		if (rand < HIT_PROBABILITY) {
			return super.damage();
		}
		return 0;
	}
}
