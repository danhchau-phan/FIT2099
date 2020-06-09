package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Action to allow Player to heal.
 */
public class EatAction extends Action {

    protected Item food;

    /**
     * Default constructor for EatAction
     * @param actor actor
     * @param food food
     */
    public EatAction(Item food) {
        this.food = food;
    }

    /**
     * Checks if the actor is alive, then uses Actor's heal method to recover lost health
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String displaying actor has healed
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        int health = food.getHealth();

        if (actor.isConscious()){
            actor.heal(health);
        }
        actor.removeItemFromInventory(food);
        return actor + " recovered 10 hitpoints";
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "heal " + actor;
    }
}
