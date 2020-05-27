package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.NumberRange;

public class MoveToTownAction extends Action {
	private TownMap townMap;
	
	public MoveToTownAction(TownMap townMap) {
		this.townMap = townMap;
	}
	@Override
	public String execute(Actor actor, GameMap map) {
		
		NumberRange xRange = townMap.getXRange();
		NumberRange yRange = townMap.getYRange();
		int x, y;
		do {
			x = (int) Math.floor(Math.random() * (xRange.max() - xRange.min()+2) + xRange.min());
			y = (int) Math.floor(Math.random() * (yRange.max() - yRange.min()+2) + yRange.min());
			System.out.println(x+"     "+y);
		} while (townMap.at(x,y).containsAnActor());
		map.moveActor(actor, townMap.at(x,y));
		
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " moves to Town";
	}
	
}
