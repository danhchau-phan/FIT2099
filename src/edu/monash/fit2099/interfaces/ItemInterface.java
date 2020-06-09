package edu.monash.fit2099.interfaces;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */
public interface ItemInterface {

	default int getHealth() {
		return 0;
	}

	default int getClipSize(){
		return 0;
	}

	default void reload(int rounds){}

	default int getRounds(){
		return 0;
	}
}
