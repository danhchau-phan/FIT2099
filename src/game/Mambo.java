package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;

public class Mambo extends ZombieActor {
	
	private Behaviour[] behaviours = {new WanderBehaviour()};
	private boolean appeared = false;
	private int turns;
	private final static double APPEAR_PROBABILITY = 0.05;
	private final static int VANISH_TURN = 31;
	private final static int CHANTING_INTERVAL = 10;
	private static int population;

	public Mambo(GameMap map) {
		super("Marie", DisplayChar.MAMBOMARIE.toChar(), 50, ZombieCapability.UNDEAD);
		map.addActor(this, new MamboLocation(map));
		Mambo.population += 1;
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		double rand = new Random().nextDouble();
		if (appeared) {
			turns += 1;
			if (turns % CHANTING_INTERVAL == 1)
				return new ChantAction();
			else if (turns == VANISH_TURN) {
				return new VanishAction();
			}
			else
				for (Behaviour behaviour : behaviours) {
					Action action = behaviour.getAction(this, map);
					if (action != null) 
						return action; 
				} 
		} else if (!appeared && rand < APPEAR_PROBABILITY) {
			appeared = true;
			turns = 1;
			return new AppearAtMapEdgeAction();
		}
		return new DoNothingAction();
	}

	@Override
	public void updatePopulation() {
		population -=1;
	}
	
}
