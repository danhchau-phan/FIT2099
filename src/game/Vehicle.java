package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

public class Vehicle extends Item {
	private TownMap townMap;
	
	public Vehicle(TownMap townMap) {
		super("vehicle", DisplayChar.VEHICLE.toChar(), false);
		this.townMap = townMap;
	}
	
	@Override
	public List<Action> getAllowableActions() {
		return Arrays.asList(new MoveToTownAction(townMap));
	}
}
