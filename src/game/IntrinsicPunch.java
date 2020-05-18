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
	 * Did the punch occur successfully?
	 * 
	 * @return true or false depending on hit probability
	 */
	public boolean isSuccessful() {
		double rand = (new Random()).nextDouble();
		return (rand > HIT_PROBABILITY);
	}
}
