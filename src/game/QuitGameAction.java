package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class QuitGameAction extends Action{

	@Override
	public String execute(Actor actor, GameMap map) {
		map.removeActor(actor);
		return actor + " quits game";
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Quit game";
	}


}
