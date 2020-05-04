package game;

import edu.monash.fit2099.engine.*;


public class CraftWeapon extends Action{

    protected Item item;

    public CraftWeapon(Item item) {
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        if (actor.getInventory().get(0).getDisplayChar() == '1'){
            Item newItem = actor.getInventory().get(0);
            (new DropItemAction(newItem)).execute(actor,map);
            item = (new ZombieClub());
            actor.addItemToInventory(item);
        }


        if (actor.getInventory().get(0).getDisplayChar() == '7'){
            actor.removeItemFromInventory(actor.getInventory().get(0));
            item = (new ZombieMaze());
            actor.addItemToInventory(item);
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " craft " + item + "?";
    }
}
