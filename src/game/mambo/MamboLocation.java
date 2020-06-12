package game.mambo;


import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.Dirt;

class MamboLocation extends Location {
	
	private GameMap map;
	private int x;
	private int y;

	private final Ground ground = new Dirt();
	
	public MamboLocation(GameMap map) {
		super(map, map.getXRange().max()+1, 0);
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
