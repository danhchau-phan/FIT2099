package game.mambo;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.DisplayChar;
import game.WanderBehaviour;
import game.ZombieActor;
import game.ZombieCapability;

/**
 * Class representing a Mambo
 *
 */
public class Mambo extends ZombieActor {
	private Behaviour[] behaviours = {new MamboBehaviour(), new WanderBehaviour()};
	private static int population;
	
	/**
	 * Constructor
	 * @param map the map Mambo will spawn on
	 */
	public Mambo(GameMap map) {
		super("MamboMarie", DisplayChar.MAMBOMARIE.toChar(), 150, ZombieCapability.UNDEAD);
		map.addActor(this, new MamboLocation(map));
		Mambo.population += 1;
	}
	
	
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if (action != null) 
				return action; 
		} 
		
		return new DoNothingAction();
	}

	@Override
	public void updatePopulation() {
		population -=1;
	}
	/**
	 * Get Mambo's population
	 * @return int
	 */
	public static int getPopulation() {
		return population;
	}

}
