package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.NumberRange;

public class ChangeMapAction extends Action {
	private GameMap destination;
	private GameMap start;
	
	public ChangeMapAction(GameMap start, GameMap destination) {
		this.start = start;
		this.destination = destination;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		
		NumberRange xRange = destination.getXRange();
		NumberRange yRange = destination.getYRange();
		int x, y;
		do {
			x = (int) Math.floor(Math.random() * (xRange.max() - xRange.min()-2) + xRange.min());
			y = (int) Math.floor(Math.random() * (yRange.max() - yRange.min()-2) + yRange.min());
		} while (destination.at(x,y).containsAnActor());
		map.moveActor(actor, destination.at(x,y));
		destination.at(x,y).addItem(new Vehicle(destination, start));
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " changes map";
	}
	
}
