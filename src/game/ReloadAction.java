package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

/**
 * Special action for reloading weapons that use ammo
 */
public class ReloadAction extends Action {

    private Item weapon;
    private List<Item> inventory;

    /**
     * Default constructor for ReloadAction
     * @param weapon weapon to be reloaded
     * @param inventory actor's inventory to find ammo
     */
    public ReloadAction(Item weapon) {
        this.weapon = weapon;
    }

    /**
     * Checks if the actors inventory has ammo that can be used to reload the required weapon. If there is, the refill
     * amount would be added to the weapon's ammo clip. Once reloaded, the clip is dropped to the ground automatically
     * and is removed from the map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
    	inventory = actor.getInventory();
    	
        for (Item item : inventory){

            if ((item.getDisplayChar() == 's' || item.getDisplayChar() == 'x') && item.getRounds() == 0){
                return "No ammo found in inventory!";
            }

            if (item.getDisplayChar() == 's'){
                weapon.reload(item.getRounds());
                item.getDropAction().execute(actor, map);
                map.at(map.locationOf(actor).x(),map.locationOf(actor).y()).removeItem(item);
                return weapon.toString() + " reloaded with 12 rounds";
            }
            else if(item.getDisplayChar() == 'x'){
                weapon.reload(item.getRounds());
                item.getDropAction().execute(actor, map);
                map.at(map.locationOf(actor).x(),map.locationOf(actor).y()).removeItem(item);
                return weapon.toString() +  " reloaded with 5 rounds";
            }
        }

        return "No ammo found in inventory!";
    }

    /**
     * Describe the action in a format suitable for displaying in the menu
     * @param actor The actor performing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reload " + weapon.toString();
    }
}
