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
		for (int x = xRange.min(); x < xRange.max(); x++)
			for (int y = yRange.min(); y < yRange.max(); y++)
				if (townMap.at(x,y).canActorEnter(actor)) {
					map.moveActor(actor, townMap.at(x,y));
					break;
				}
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " moves to Town";
	}
	
}
