package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.Printable;

public class MamboLocation extends Location {
	
	private GameMap map;
	private int x;
	private int y;

	private final List<Item> items = Collections.emptyList(); 
	private final Ground ground = new Dirt();
	private final List<Exit> exits = Collections.emptyList();
	
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
