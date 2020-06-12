package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Actor;
import game.Farmer;

/**
 * This interface provides the ability to add methods to Actor, without
 * modifying code in the engine, or downcasting references in the game.
 */

public interface ActorInterface {
	/**
	 * Update population of the class actor belongs to
	 */
	default void updatePopulation() {
	};

	/**
	 * Set actor's zombie target during sniper shooting
	 * 
	 * @param actor
	 */
	default void setZombieTarget(Actor actor) {
	}

	/**
	 * Get actor's zombie target
	 * 
	 * @return Actor
	 */
	default Actor getZombieTarget() {
		return null;
	}

	/**
	 * Delete actor's zombie target
	 */
	default void deleteZombieTarget() {
	}

	/**
	 * Set actor's level of concentration
	 * 
	 * @param aim number of rounds spent for aiming
	 */
	default void setConcentration(int aim) {
	}

	/**
	 * Get actor's level of concentration
	 * 
	 * @return int
	 */
	default int getConcentration() {
		return 0;
	}
}
