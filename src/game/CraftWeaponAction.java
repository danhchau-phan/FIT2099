package game;

import edu.monash.fit2099.engine.*;

/**
 * Action to allow items to be crafted by the player.
 */
public class CraftWeaponAction extends Action{
    protected Item item;
    protected Actor target;

    /**
     * Constructor.
     *
     * @param target the actor performing the action
     */

    public CraftWeaponAction() {
    }

    /**
     * Checks if required item is available.
     * Then crafts suitable weapon if item is available.
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        String result = null;

        for (int i = 0; i < actor.getInventory().size(); i++){
            if (actor.getInventory().get(i).getDisplayChar() == DisplayChar.ZOMBIEARM.toChar()){
                Item newItem = actor.getInventory().get(i);
                actor.removeItemFromInventory(newItem);
                item = (new ZombieClub());
                actor.addItemToInventory(item);
                return "Player crafted Zombie Club";
            }


            else if (actor.getInventory().get(i).getDisplayChar() == DisplayChar.ZOMBIELEG.toChar()){
                Item newItem = actor.getInventory().get(i);
                actor.removeItemFromInventory(newItem);
                item = (new ZombieMaze());
                actor.addItemToInventory(item);
                return "Player crafted Zombie Maze";
            }

            else
                result = "Correct items not present";
        }
        



        return result;
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Player crafts a weapon"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " crafts" + " a weapon";
    }
}
