package game;

import java.util.Random;

import edu.monash.fit2099.engine.IntrinsicWeapon;
import edu.monash.fit2099.engine.Actor;

public class IntrinsicBite extends IntrinsicWeapon implements Chanceable {
	private final static double HIT_PROBABILITY = 0.5;
	public final static int DAMAGE = 15;
	public final static int HEAL_POINT = 5;
	private Zombie zombie;
	
	public IntrinsicBite(Zombie zombie) {
		super(DAMAGE, "bites");
		this.zombie = zombie;
	}
	
	public boolean isSuccessful() {
		double rand = (new Random()).nextDouble();
		if (rand < HIT_PROBABILITY) {
			heal();
			return true;
		}
		return false;
	}
	
	// A successful bite attack restores 5 health points to the Zombie
	public void heal() {
		zombie.heal(HEAL_POINT);
	}

}
