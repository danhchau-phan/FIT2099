package game.mambo;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * Class representing location of a mambo. MamboLocation does not have exit and cannot be entered by other Actors
 *
 */
class MamboLocation extends Location {
	
	private GameMap map;
	private int x;
	private int y;

	private Ground ground;
	
	public MamboLocation(GameMap map, int x, int y) {
		super(map, x, y);
		this.x = x;
		this.y = y;
		this.map = map;
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	public void addItem(Item item) {
	}

	public void removeItem(Item item) {
	}
	
	public void addActor(Actor actor) {
	}

	public void addExit(Exit exit) {
	}

	public void removeExit(Exit exit) {
	}
}
