package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class ShotgunShootingAction extends Action {

    private int damage;
    private ShotgunSubMenu menu = new ShotgunSubMenu();


    public ShotgunShootingAction(int damage) {
        this.damage = damage;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        int direction = menu.showMenu();
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Fire Shotgun";

    }
}
