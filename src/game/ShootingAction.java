package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

public class ShootingAction extends Action {

    private Actor shooter;
    private Item weapon;


    public ShootingAction(Actor shooter, Item weapon) {
        this.shooter = shooter;
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
