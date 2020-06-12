package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {

	/**
	 * Returns an actor's health
	 */
	default int getHealth() {
		return 0;
	}

	/**
	 * Reloads special weapons
	 */
	default void reload(int rounds){}

	/**
	 * Gets number of rounds left in a special weapon
	 */
	default int getRounds(){
		return 0;
	}

	/**
	 * Reduces a special weapon's clip size by one
	 */
	default void fire() {}
}
