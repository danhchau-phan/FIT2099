package game;

import java.util.Random;

import edu.monash.fit2099.engine.IntrinsicWeapon;

public class IntrinsicPunch extends IntrinsicWeapon {
	private final static double HIT_PROBABILITY = 0.5;
	public final static int DAMAGE = 15;

	public IntrinsicPunch() {
		super(DAMAGE, "punches");
	}
	
	public boolean isSuccessful() {
		double rand = (new Random()).nextDouble();
		return (rand > HIT_PROBABILITY);
	}
}
