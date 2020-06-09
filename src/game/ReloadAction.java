package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

public class ReloadAction extends Action {

    private Item weapon;
    private List<Item> inventory;

    public ReloadAction(Item weapon, List<Item> inventory) {
        this.weapon = weapon;
        this.inventory = inventory;
    }


    @Override
    public String execute(Actor actor, GameMap map) {

        for (Item item : inventory){

            if ((item.getDisplayChar() == 's' || item.getDisplayChar() == 'x') && item.getRounds() == 0){
                return "No ammo found in inventory!";
            }

            if (item.getDisplayChar() == 's'){
                weapon.reload(item.getRounds());
                item.emptyClip();
                return weapon.toString() + " reloaded with 12 rounds";
            }
            else if(item.getDisplayChar() == 'x'){
                weapon.reload(item.getRounds());
                item.emptyClip();
                return weapon.toString() +  " reloaded with 12 rounds";
            }
        }

        return "No ammo found in inventory!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Reload " + weapon.toString();
    }
}
