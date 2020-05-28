package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class Vehicle extends Item {
	private GameMap destination;
	private GameMap start;
	
	public Vehicle(GameMap start, GameMap destination) {
		super("vehicle", DisplayChar.VEHICLE.toChar(), false);
		this.start = start;
		this.destination = destination;
	}
	
	@Override
	public List<Action> getAllowableActions() {
		return Arrays.asList(new ChangeMapAction(start, destination));
	}
}
