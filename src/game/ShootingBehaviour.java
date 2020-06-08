package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class ShootingBehaviour implements Behaviour {
    protected ZombieCapability attackableTeam;


    public ShootingBehaviour(ZombieCapability attackableTeam) {
        this.attackableTeam = attackableTeam;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // Checking if actor has a shotgun or sniper rifle in their inventory
        for (int i = 0; i < actor.getInventory().size(); i++){
            if (actor.getInventory().get(i).getDisplayChar() == DisplayChar.SHOTGUN.toChar()){
                return null; //Shooting Action
            }

            else if (actor.getInventory().get(i).getDisplayChar() == DisplayChar.SNIPER.toChar()){
                return null; // ShootingAction
            }

            else {
                return null;
            }
        }
        return null;
    }
}
