package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class EatAction extends Action {

    protected Actor actor;
    protected Food food;

    public EatAction(Actor actor, Food food) {
        this.actor = actor;
        this.food = food;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        int health = food.getHealth();

        if (actor.isConscious()){
            actor.heal(health);
        }
        return actor + " recovered 10 hitpoints";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "heal " + actor;
    }
}
