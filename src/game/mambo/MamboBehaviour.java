package game.mambo;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.mambo.AppearAction;
import game.Behaviour;
import game.mambo.ChantAction;
/**
 * Behaviour of a Mambo
 *
 */
class MamboBehaviour implements Behaviour {
	/**
	 * Counter of turns
	 */
	private int turns = 0;
	/**
	 * Whether the Mambo has appeared on the map
	 */
	private boolean appeared = false;
	private final static double APPEAR_PROBABILITY = 0.05;
	private final static int VANISH_TURN = 31;
	private final static int CHANTING_INTERVAL = 10;
	
	@Override
	public Action getAction(Actor actor, GameMap map) {
		double rand = new Random().nextDouble();
		if (appeared) {
			turns += 1;
			if (turns % CHANTING_INTERVAL == 1)
				return new ChantAction();
			else if (turns == VANISH_TURN) {
				return new VanishAction();
			}
		} else if (!appeared && rand < APPEAR_PROBABILITY) {
			appeared = true;
			turns = 1;
			return new AppearAction();
		}
		return null;
	}

}
