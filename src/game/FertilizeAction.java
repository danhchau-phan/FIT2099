package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;

public class FertilizeAction extends Action {

    private Ground ground;
    protected Actor actor;
    private Crop crop;

    public FertilizeAction(Ground ground, Actor actor) {
        this.ground = ground;
        this.actor = actor;
    }


    @Override
    public String execute(Actor actor, GameMap map) {

        ground = crop;

        int ripeAge = crop.getRipe();

        if (ripeAge <= 10){
            crop.setRipe(ripeAge + 10);

        }

        else {
            crop.setRipe(20);
        }

        return actor + "fertilized crop";
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
