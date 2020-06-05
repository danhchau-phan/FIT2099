package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing the Player.
 */
public class Player extends Human {

	private Menu menu = new Menu();
	private Behaviour[] behaviours = {new AttackBehaviour(ZombieCapability.ALIVE)};
	private boolean firstTurn;


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
	
	private enum Result {
    	WIN ("Player wins"),
    	LOSE ("Player loses");
    	
    	private final String result;
    	
    	private Result(String res) {
    		this.result = res;
    	}
    	
    	public String toString() {
    		return this.result;
    	}
    }
	
	private Result gameResult() {
		if (Human.getPopulation() == 1)
			return Result.LOSE;
		if (Zombie.getPopulation() == 0 && Mambo.getPopulation() == 0)
			return Result.WIN;
		return null;
	}
	
    @Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
    	if (gameResult() != null)
    		return new EndGame(gameResult());
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		if (this.getInventory().size() != 0){
            actions.add(new CraftWeaponAction(this));
        }

		// Checking if Food is available around Player
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(this).getExits());
		Collections.shuffle(exits);

		for (Exit e: exits){
			if (e.getDestination().getGround().getDisplayChar() == DisplayChar.RIPECROP.toChar()){
				Location location = e.getDestination();
				actions.add(new HarvestAction(this,location));
			}
		}

		// If health is low and player has food in their inventory
		if (this.hitPoints < this.maxHitPoints){
			for (int i = 0; i < this.getInventory().size(); i++){
				if (this.getInventory().get(i).getDisplayChar() == DisplayChar.FOOD.toChar()){
					Item food = this.getInventory().get(i);
					actions.add(new EatAction(this,food));
				}
			}
		}
		actions.add(new QuitGameAction());
		return menu.showMenu(this, actions, display);
	}
    
    private class QuitGameAction extends Action{

    	@Override
    	public String execute(Actor actor, GameMap map) {
    		map.removeActor(actor);
    		return actor + " quits game";
    	}

    	@Override
    	public String menuDescription(Actor actor) {
    		// TODO Auto-generated method stub
    		return "Quit game";
    	}
    }
    
    private class EndGame extends Action {
    	
    	Result result;
    	public EndGame(Result result) {
    		super();
    		this.result = result;
    	}
    	
    	@Override
    	public String execute(Actor actor, GameMap map) {
    		map.removeActor(actor);
    		return this.result.toString();
    	}
    	
		@Override
		public String menuDescription(Actor actor) {
			return null;
		}
    	
    }
}
