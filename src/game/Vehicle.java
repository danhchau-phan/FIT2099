package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class that represents a vehicle
 *
 */
public class Vehicle extends Item {
	/**
	 * Destination of vehicle
	 */
	private GameMap destination;
	/**
	 * Departure of vehicle
	 */
	private GameMap start;

	/**
	 * Constructor
	 * 
	 * @param start       Departure map
	 * @param destination Destination map
	 */
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
