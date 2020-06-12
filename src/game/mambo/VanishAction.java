package game.mambo;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

class VanishAction extends Action {
	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(actor);
		actor.updatePopulation();
		Mambo mambo = new Mambo(map);
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " vanishes";
	}
}
