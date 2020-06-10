package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.DisplayChar;
import game.Mambo;
import game.mambo.MamboBehaviour;
import game.MamboLocation;
import game.WanderBehaviour;
import game.ZombieActor;
import game.ZombieCapability;

public class Mambo extends ZombieActor {

	private Behaviour[] behaviours = {new MamboBehaviour(), new WanderBehaviour()};
	private static int population;

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
	
	public static int getPopulation() {
		return population;
	}

}
