package game.mambo;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

class AppearAction extends Action {
	@Override
	public String execute(Actor actor, GameMap map) {
		int xMax, xMin, yMax, yMin;
		xMax = map.getXRange().max();
		xMin = map.getXRange().min();
		yMax = map.getYRange().max();
		yMin = map.getYRange().min();
		int x, y;
		do {
			int edge = new Random().nextInt(1);
			if (edge == 0) {
				x = new Random().nextInt(1) * (xMax - xMin) + xMin;
				y = new Random().nextInt(yMax - yMin) + yMin;
			}
			else {
				x = new Random().nextInt(xMax - xMin) + xMin;
				y = new Random().nextInt(1) * (yMax - yMin) + yMin;
			}
		} while (map.at(x,y).containsAnActor());
		map.moveActor(actor, map.at(x,y));
		return actor + " appears at "+ x + "," + y;
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " appears.";
	}
}
