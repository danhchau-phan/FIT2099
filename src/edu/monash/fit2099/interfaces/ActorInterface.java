package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Actor;
import game.Farmer;

/**
 * This interface provides the ability to add methods to Actor, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface ActorInterface {
	default void updatePopulation() {};

	default void setZombieTarget(Actor actor){}


	default Actor getZombieTarget() {
		return null;
	}

	default void deleteZombieTarget() {}

	default void setConcentration(int aim){}

	default int getConcentration(){
		return 0;
	}
}
