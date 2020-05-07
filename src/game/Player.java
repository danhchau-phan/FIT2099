package game;

import edu.monash.fit2099.engine.*;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	private Behaviour[] behaviours = {
			new AttackBehaviour(ZombieCapability.ALIVE)
	};

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {


        // If players has either a zombie arm or leg in their inventory, craft its related weapon
        for (int i = 0; i < this.getInventory().size(); i++){
            if (this.getInventory().get(i).getDisplayChar() == '1'){
                Item thisItem = this.getInventory().get(i);
                this.removeItemFromInventory(thisItem);
                this.addItemToInventory((new ZombieClub()));
                display.println("Player crafted a zombie club");

            }

            if (this.getInventory().get(i).getDisplayChar() == '7'){
                Item thisItem = this.getInventory().get(i);
                this.removeItemFromInventory(thisItem);
                this.addItemToInventory((new ZombieMaze()));
                display.println("Player crafted a zombie maze");
            }
        }



		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}
}
