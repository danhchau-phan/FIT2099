package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

public class SniperShootingAction extends AttackAction {

    private WeaponItem weapon;
    private static final double PROBABILITY = 0.75;
    private static final double PROBABILITYx2 = 0.90;
    private ArrayList<Actor> zombies = new ArrayList<>();
    private SniperSubMenu menu = new SniperSubMenu();
    Actor target;

    public SniperShootingAction(WeaponItem weapon) {
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Location location = map.locationOf(actor);
        int x = map.locationOf(actor).x();
        int middle = (map.getXRange().max() - map.getYRange().min()) / 2; 

        int r = map.getXRange().max();

        // Based on player's position, zombies capable of being shot are added to a list
        if (x <= middle){
            for (int i = 0; i <= middle; i++){
                for (int j = 0; j <= map.getYRange().max(); j++){
                    if (map.at(i,j).containsAnActor() && map.at(i,j).getActor().hasCapability(ZombieCapability.UNDEAD)){
                        zombies.add(map.at(i,j).getActor());
                    }
                }
            }
        }

        else if (x > middle){
            for (int i = middle; i <= map.getXRange().max(); i++){
                for (int j = 0; j <= map.getYRange().max(); j++){
                    if (map.at(i,j).containsAnActor()){
                        if (map.at(i,j).getActor().hasCapability(ZombieCapability.UNDEAD)){
                            zombies.add(map.at(i,j).getActor());
                        }
                    }
                }
            }
        }

        // Ask the player to select a target to concentrate on
        // If player has already concentrated, player cannot select another target
        if (actor.getZombieTarget() == null){
            actor.setZombieTarget(menu.showTargets(zombies));
            target = actor.getZombieTarget();
        }
        else {
            target = actor.getZombieTarget();
        }

        // Prompts the player his options executes the related action
        return fire(actor,map);

    }

    @Override
    public String menuDescription(Actor actor) {
        return "Fire Sniper Rifle";
    }

    public String fire(Actor actor, GameMap map){
        int aim = actor.getConcentration();
        int selection = menu.showMenu();
        int damage;
        String result = "";

        // Fire
        if (selection == 1) {
            if (aim == 1){
                damage = weapon.damage() * 2;
                if (Math.random() <= PROBABILITYx2){
                    target.hurt(damage);
                    if (!target.isConscious()){
                        actor.deleteZombieTarget();
                        return killTarget(target);
                    }
                    else {
                        result = target + " was " + weapon.verb() + " by a " + weapon.toString() + " for " + damage + " damage.";
                    }
                }
            }
            else if (aim >= 2){
                actor.deleteZombieTarget();
                result = killTarget(target);
            }
            else if (aim == 0){
                damage = weapon.damage();
                if (Math.random() <= PROBABILITY){
                    target.hurt(damage);
                    if (!target.isConscious()){
                        actor.deleteZombieTarget();
                        result = killTarget(target);
                    }
                    else {
                        result = target + " was " + weapon.verb() + " by a " + weapon.toString() + " for " + damage + " damage.";
                    }
                }
            }
        }

        // Aim
        if (selection == 2){
            aim += 1;
            actor.setConcentration(aim);

            if (actor.getConcentration() > 1){
                result = "Player continues to aim at " + target;
            }
            else {
                result = "Player is aiming at " + target;
            }
        }

        // Do nothing
        if (selection == 3){
            actor.deleteZombieTarget();
            actor.setConcentration(0);
            result = actor + " lost " + target;
        }

        return result;
    }
    
}
