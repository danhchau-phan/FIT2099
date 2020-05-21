package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * Action to allow player to harvest food from ripe crops.
 */

public class HarvestAction extends Action {

    protected Actor actor;
    protected Location location;

    /**
     * Default constructor
     *
     * @param actor actor performing the action
     * @param location location of ripe crop.
     */
    public HarvestAction(Actor actor, Location location) {
        this.actor = actor;
        this.location = location;
    }

    /**
     * Sets the ground of the ripe Crop to Dirt.
     * Adds Food item to Player's inventory.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return display message
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // Harvesting
        location.setGround(new Dirt());
        actor.addItemToInventory(new Food());

        return "Player harvested food!";
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @param actor The actor performing the action.
     * @return string definition of action performed.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " harvest food?";
    }
}
