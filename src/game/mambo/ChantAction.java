package game.mambo;

import java.util.Random;
import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Zombie;
/**
 * Action for chanting
 *
 */
class ChantAction extends Action{
	/**
	 * Number of zombies added at each chant
	 */
	private static final int NUM_ZOMBIES_ADDED = 5;
	
	public String execute(Actor actor, GameMap map) {
		
		for (int i = 0; i < NUM_ZOMBIES_ADDED; i++) {
			int x, y;
			int xMax, xMin, yMax, yMin;
			xMax = map.getXRange().max();
			xMin = map.getXRange().min();
			yMax = map.getYRange().max();
			yMin = map.getYRange().min();
			do {
				x = new Random().nextInt(xMax - xMin) + xMin;
				y = new Random().nextInt(yMax - yMin) + yMin;
			} while (map.at(x,y).containsAnActor());
			map.at(x,y).addActor(new Zombie("Chant"));
		}
		return String.format("%s chants. %s zombies are added.", actor, NUM_ZOMBIES_ADDED);
	};
	
	public String menuDescription(Actor actor) {
		return actor + " chants";
	};
}
