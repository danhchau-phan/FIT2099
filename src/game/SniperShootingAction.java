package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Arrays;

public class SniperShootingAction extends AttackAction {

    private WeaponItem weapon;
    private static final double PROBABILITY = 0.75;
    private static final double PROBABILITYx2 = 0.90;
    private ArrayList<Actor> zombies = new ArrayList<>();
    private Menu menu;
    private Actor target;
    private int aim;

    public SniperShootingAction(WeaponItem weapon) {
        this.weapon = weapon;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        int x = map.locationOf(actor).x();
        int middle = (map.getXRange().max() - map.getYRange().min()) / 2; 

        // Based on player's position, zombies capable of being shot are added to a list
        if (x <= middle){
        	zombies = getZombies(middle, map.getYRange().max(), map);
        }
        else if (x > middle){
        	zombies = getZombies(map.getXRange().max(), map.getYRange().max(), map);
        }

        // Ask the player to select a target to concentrate on
        // If player has already concentrated, player cannot select another target
        if (actor.getZombieTarget() == null){
        	menu = new SniperSubMenu(zombies, actor);
        }
        else {
            menu = new SniperSubMenu(actor.getZombieTarget());
        }
        
        // Prompts the player his options executes the related action
        Actions actions = new Actions();
        actions.add(Arrays.asList(new FireAction(), new AimAction(), new RetreatAction()));
        return menu.showMenu(actor, actions, new Display()).execute(actor, map);

    }
    
    private ArrayList<Actor> getZombies(int iRange, int jRange, GameMap map) {
    	ArrayList<Actor> zombies = new ArrayList<>();
    	for (int i = 0; i <= iRange; i++){
            for (int j = 0; j <= jRange; j++){
                if (map.at(i,j).containsAnActor() && map.at(i,j).getActor().hasCapability(ZombieCapability.UNDEAD)){
                    zombies.add(map.at(i,j).getActor());
                }
            }
        }
    	return zombies;
    }
    @Override
    public String menuDescription(Actor actor) {
        return "Fire Sniper Rifle";
    }

    private class FireAction extends Action {
    	
    	Actor target;
    	FireAction() {}
    	
    	public void setTarget(Actor target) {
    		this.target = target;
    	}
		@Override
		public String execute(Actor actor, GameMap map) {
			String result = ""; 
			int damage;
			if (aim == 1){
                damage = weapon.damage() * 2;
                if (Math.random() <= PROBABILITYx2){
                    target.hurt(damage);
                    if (!target.isConscious()){
                        actor.deleteZombieTarget();
                        result = killTarget(target, map);
                    } else {
                        result = target + " was " + weapon.verb() + " by a " + weapon.toString() + " for " + damage + " damage.";
                    }
                }
            }
            else if (aim >= 2){
                actor.deleteZombieTarget();
                result = killTarget(target, map);
            }
            else if (aim == 0){
                damage = weapon.damage();
                if (Math.random() <= PROBABILITY){
                    target.hurt(damage);
                    if (!target.isConscious()){
                        actor.deleteZombieTarget();
                        result = killTarget(target, map);
                    }
                    else {
                        result = target + " was " + weapon.verb() + " by a " + weapon.toString() + " for " + damage + " damage.";
                    }
                }
            }
            aim = 0;
			return result;
		}

		@Override
		public String menuDescription(Actor actor) {
			return "Fire";
		}
    }
    
    private class AimAction extends Action {
    	
    	Actor target;
    	AimAction() {}
    	
    	public void setTarget(Actor target) {
    		this.target = target;
    	}
		@Override
		public String execute(Actor actor, GameMap map) {
			String result = "";
            aim += 1;
            actor.setConcentration(aim);

            if (actor.getConcentration() > 1){
                result = "Player continues to aim at " + target;
            }
            else {
                result = "Player is aiming at " + target;
            }
			return result;
		}

		@Override
		public String menuDescription(Actor actor) {
			return "Aim";
		}
    }
    
    private class RetreatAction extends Action {
    	
    	Actor target;
    	RetreatAction() {}
    	
    	public void setTarget(Actor target) {
    		this.target = target;
    	}
		@Override
		public String execute(Actor actor, GameMap map) {
			actor.deleteZombieTarget();
            actor.setConcentration(0);
            aim = 0;
			return actor + " lost " + target;
		}

		@Override
		public String menuDescription(Actor actor) {
			return "Retreat";
		}
    	
    }
    
    public Action getNextAction() {
    	if (aim != 0)
    		return this;
    	return null;
	}
}
