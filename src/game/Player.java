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
	private Actor target = null;
	private int concentration = 0;
	private boolean playerDamage = false;

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

		// Checking if Food is available around Player
		List<Exit> exits = new ArrayList<Exit>(map.locationOf(this).getExits());
		Collections.shuffle(exits);
		
		if (gameResult() != null)
    		return new EndGame(gameResult());
        // Enables user to quit the game
        actions.add(new QuitGameAction());

        // If player takes damage, target is lost
        if (this.playerDamage){
        	deleteZombieTarget();
        	setConcentration(0);
        	playerDamage = false;
		}

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();
		return menu.showMenu(this, actions, display);
	}

	@Override
	public void setZombieTarget(Actor actor) {
		target = actor;
	}

	@Override
	public Actor getZombieTarget() {
		return target;
	}

	@Override
	public void deleteZombieTarget() {
		target = null;
	}

	@Override
	public void setConcentration(int aim) {
		concentration = aim;
	}

	@Override
	public int getConcentration() {
		return concentration;
	}

	@Override
	public void setPlayerDamage(boolean attack) {
		playerDamage = attack;
	}

	class QuitGameAction extends Action{

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
    
    class EndGame extends Action {
    	
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
