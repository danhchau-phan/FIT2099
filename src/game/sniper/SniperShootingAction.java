package game.sniper;

import edu.monash.fit2099.engine.*;
import game.AttackAction;
import game.ZombieCapability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SniperShootingAction extends AttackAction {

	private WeaponItem weapon;
	private static final double PROBABILITY = 0.75;
	private static final double PROBABILITYx2 = 0.90;
	private ArrayList<Actor> zombies = new ArrayList<>();
	private Menu menu;
	private int aim;

	public SniperShootingAction(WeaponItem weapon, Menu menu) {
		this.weapon = weapon;
		this.menu = menu;
	}

	/**
	 * Checks if the player is on the right or left side of the map. Based on
	 * players location, player is shown the list of zombies in the player's side of
	 * the map. If the player has previously selected a zombie, the player will be
	 * instead prompted a list of options to carry out on the target i.e Fire, Aim
	 * or Retreat.
	 *
	 * @param actor player
	 * @param map   map player is on
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		int x = map.locationOf(actor).x();
		int middle = (map.getXRange().max() - map.getYRange().min()) / 2;

		// Based on player's position, zombies capable of being shot are added to a list
		if (x <= middle) {
			zombies = getZombies(0, middle, map.getYRange().max(), map);
		} else if (x > middle) {
			zombies = getZombies(middle, map.getXRange().max(), map.getYRange().max(), map);
		}

		// Prompts the player his options executes the related action
		Actions actions = new Actions();
		actions.add(Arrays.asList(new FireSniperAction(), new AimAction(), new RetreatAction()));
		return menu.showMenu(actor, actions, new Display()).execute(actor, map);

	}

	/**
	 * Based on the player's position on the map, zombies on the player's side is
	 * collected and stored in a list.
	 *
	 * @param iRange x coordinate/coordinates
	 * @param jRange y coordinates
	 * @param map    map actor is on
	 * @return list of zombies
	 */
	private ArrayList<Actor> getZombies(int iPointer, int iRange, int jRange, GameMap map) {
		ArrayList<Actor> zombies = new ArrayList<>();

		for (int i = iPointer; i <= iRange; i++) {
			for (int j = 0; j <= jRange; j++) {
				if (map.at(i, j).containsAnActor() && map.at(i, j).getActor().hasCapability(ZombieCapability.UNDEAD)) {
					zombies.add(map.at(i, j).getActor());
				}
			}
		}

		return zombies;
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Fire Sniper Rifle";
	}

	/**
	 * Special action that shoots a selected zombie
	 */
	private class FireSniperAction extends Action {

		Actor target;

		/**
		 * Default constructor
		 */
		FireSniperAction() {
		}
		
		public List<Actor> getTargets() {
			return zombies;
		}
		public void setTarget(Actor target) {
			this.target = target;
		}

		/**
		 * If player's aim is 0, weapon's set damage is dealt on the target. If player's
		 * aim is 1, x2 damage is dealt on the target. If player's aim is 2 or more, max
		 * damage is dealt on the target, i.e instant kill.
		 *
		 * @param actor The actor performing the action.
		 * @param map   The map the actor is on.
		 */
		@Override
		public String execute(Actor actor, GameMap map) {
			String result = "";
			int damage;
			if (aim == 1) {
				damage = weapon.damage() * 2;
				result = inflictDamage(actor, map, damage, PROBABILITYx2);
			} else if (aim >= 2) {
				result = killTarget(target, map);
				actor.deleteZombieTarget();
			} else if (aim == 0) {
				damage = weapon.damage();
				result = inflictDamage(actor, map, damage, PROBABILITY);
			}
			aim = 0;
			return result;
		}

		private String inflictDamage(Actor actor, GameMap map, int damage, double prob) {
			String result = "";
			if (Math.random() <= prob) {
				target.hurt(damage);
				if (!target.isConscious()) {
					actor.deleteZombieTarget();
					result = killTarget(target, map);
				} else {
					result = target + " was " + weapon.verb() + " by a " + weapon.toString() + " for " + damage + " damage.";
				}
			}
			return result;
		}
		
		@Override
		public String menuDescription(Actor actor) {
			return "Fire";
		}
	}

	/**
	 * Special action for aiming.
	 */
	private class AimAction extends Action {

		Actor target;

		/**
		 * Default constructor.
		 */
		AimAction() {
		}
		public List<Actor> getTargets() {
			return zombies;
		}
		public void setTarget(Actor target) {
			this.target = target;
		}

		/**
		 * Aim increments per turn spent by the actor aiming.
		 *
		 * @param actor The actor performing the action.
		 * @param map   The map the actor is on.
		 */
		@Override
		public String execute(Actor actor, GameMap map) {
			String result = "";
			aim += 1;
			actor.setConcentration(aim);

			if (actor.getConcentration() > 1) {
				result = "Player continues to aim at " + target;
			} else {
				result = "Player is aiming at " + target;
			}
			return result;
		}

		@Override
		public String menuDescription(Actor actor) {
			return "Aim";
		}
	}

	/**
	 * Special action to skip the turn
	 */
	private class RetreatAction extends Action {

		Actor target;

		/**
		 * Default constructor for RetreatAction
		 */
		RetreatAction() {
		}
		public List<Actor> getTargets() {
			return zombies;
		}
		public void setTarget(Actor target) {
			this.target = target;
			
		}

		/**
		 * If player retreats, he loses his concentration, i,e player's aim becomes 0
		 * and player loses target.
		 *
		 * @param actor The actor performing the action.
		 * @param map   The map the actor is on.
		 */
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

	/**
	 * Repeats shooting action, if the player decide to aim. Breaks loop if player
	 * fires or retreat.
	 */
	public Action getNextAction() {
		if (aim != 0)
			return this;
		return null;
	}
}
