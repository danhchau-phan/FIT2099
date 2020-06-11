package game;

import java.util.HashMap;
import java.util.Map;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Menu;

public class ShotgunSubMenu extends Menu {
	public static final int REDIX = 10;
	private static final Map<Character, String> DIRECTIONS = Map.of(
			'1', "North",
			'2', "North East",
			'3', "East",
			'4', "South East",
			'5', "South",
			'6', "South West",
			'7', "West",
			'8', "North West");

	/**
	 * Display a menu to the user and have them select an option.
	 *
	 * @param actor the Actor representing the player
	 * @param actions the Actions that the user can choose from
	 * @param display the I/O object that will display the map
	 * @return returns the Action associated to the selection made.
	 */
    public Action showMenu(Actor actor, Actions actions, Display display){
		display.println("Select direction to fire\n");

        HashMap<Character, Action> keyToActionMap = new HashMap<Character, Action>();
		for (int i = 0; i < DIRECTIONS.size(); i++) {
			char c = Character.forDigit(i+1, REDIX);
			keyToActionMap.put(c, actions.get(i));
			display.println(c + ": " + DIRECTIONS.get(c));
		}

        char choice;
        do {
        	choice = display.readChar();
        } while (!keyToActionMap.containsKey(choice));

        return keyToActionMap.get(choice);
    }
}
