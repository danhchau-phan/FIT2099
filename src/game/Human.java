package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing an ordinary human.
 * 
 * 
 * @author ram
 *
 */
public class Human extends ZombieActor {
	private Behaviour behaviour = new WanderBehaviour();

	/**
	 * The default constructor creates default Humans
	 * 
	 * @param name the human's display name
	 */
	public Human(String name) {
		super(name, game.DisplayChar.HUMAN.toChar(), 50, ZombieCapability.ALIVE);
	}
	
	/**
	 * The protected constructor can be used to create subtypes
	 * of Human, such as the Player
	 * 
	 * @param name the human's display name
	 * @param displayChar character that will represent the Human in the map 
	 * @param hitPoints amount of damage that the Human can take before it dies
	 */
	protected Human(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints, ZombieCapability.ALIVE);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// FIXME humans are pretty dumb, maybe they should at least run away from zombies?

		// If humans are hurt and wander onto a location containing food. They automatically consume
		// it to heal.
		if (this.hasCapability(ZombieCapability.ALIVE)){
			for (int i = 0; i < map.locationOf(this).getItems().size(); i++){
				if (map.locationOf(this).getItems().get(i).getDisplayChar() == game.DisplayChar.FOOD.toChar()){
					if (this.hitPoints < this.maxHitPoints){
						Item food = map.locationOf(this).getItems().get(i);
						this.heal(food.getHealth());
						display.println(this + " has recovered some health");
						map.locationOf(this).removeItem(food);
					}
				}
			}
		}
		return behaviour.getAction(this, map);
	}

}
