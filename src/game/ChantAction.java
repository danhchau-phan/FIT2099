package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.NumberRange;

public class ChantAction extends Action {
	private static final int NUM_ZOMBIES_ADDED = 5;
	
	public String execute(Actor actor, GameMap map) {
		NumberRange xRange = map.getXRange();
		NumberRange yRange = map.getYRange();
		for (int i = 0; i < NUM_ZOMBIES_ADDED; i++) {
			int x, y;
			do {
				x = (int) Math.floor(Math.random() * (xRange.max() - xRange.min()+2) + xRange.min());
				y = (int) Math.floor(Math.random() * (yRange.max() - yRange.min()+2) + yRange.min());
			} while (map.at(x,y).containsAnActor());
			map.at(x,y).addActor(new Zombie("Chant"));
		}
		return String.format("%s chants. %s zombies are added.", actor, NUM_ZOMBIES_ADDED);
	};
	
	public String menuDescription(Actor actor) {
		return actor + "chants";
	};
}
