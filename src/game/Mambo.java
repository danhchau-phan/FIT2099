package game;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.DoNothingAction;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.NumberRange;

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
				return new Mambo.ChantAction();
			else if (turns == VANISH_TURN) {
				updatePopulation();
				return new Mambo.VanishAction();
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
			return new Mambo.AppearAtMapEdgeAction();
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
	
	class AppearAtMapEdgeAction extends Action {

		@Override
		public String execute(Actor actor, GameMap map) {
			int xMax, xMin, yMax, yMin;
			xMax = map.getXRange().max();
			xMin = map.getXRange().min();
			yMax = map.getYRange().max();
			yMin = map.getYRange().min();
			int x, y;
			do {
				int edge = new Random().nextInt(1);
				if (edge == 0) {
					x = new Random().nextInt(1) * (xMax - xMin) + xMin;
					y = new Random().nextInt(yMax - yMin) + yMin;
				}
				else {
					x = new Random().nextInt(xMax - xMin) + xMin;
					y = new Random().nextInt(1) * (yMax - yMin) + yMin;
				}
			} while (map.at(x,y).containsAnActor());
			map.moveActor(actor, map.at(x,y));
			return actor + "appears at "+ x + "," + y;
		}

		@Override
		public String menuDescription(Actor actor) {
			return actor + "appears.";
		}

	}
	
	class ChantAction extends Action {
		private static final int NUM_ZOMBIES_ADDED = 5;
		
		public String execute(Actor actor, GameMap map) {
			NumberRange xRange = map.getXRange();
			NumberRange yRange = map.getYRange();
			for (int i = 0; i < NUM_ZOMBIES_ADDED; i++) {
				int x, y;
				do {
					x = (int) Math.floor(Math.random() * (xRange.max() - xRange.min()+2) + xRange.min());
					y = (int) Math.floor(Math.random() * (yRange.max() - yRange.min()+2) + yRange.min());
				} while (map.at(x,y).containsAnActor());
				map.at(x,y).addActor(new Zombie("Chant"));
			}
			return String.format("%s chants. %s zombies are added.", actor, NUM_ZOMBIES_ADDED);
		};
		
		public String menuDescription(Actor actor) {
			return actor + "chants";
		};
	}
	public class VanishAction extends Action {
		@Override
		public String execute(Actor actor, GameMap map) {
			map.removeActor(actor);
			return menuDescription(actor);
		}

		@Override
		public String menuDescription(Actor actor) {
			return actor + "vanishes";
		}
	}

}
