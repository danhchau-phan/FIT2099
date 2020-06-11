package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Special Action for shooting zombies with a shotgun.
 */
public class ShotgunShootingAction extends AttackAction {
	/**
	 * weapon      : shotgun
	 * PROBABILITY : 75% chance of shotgun dealing damage.
	 * menu 	   : Shotgun sub menu for actor to choose the direction to fire.
	 * width       : X end of the map
	 * height      : Y end of the map
	 * xRange      : X coordinates of the attack area
	 * yRange      : Y coordinates of the attack area zombies :
	 * Zombie      : actors that were hurt during firing
	 */
	private WeaponItem weapon;
	private static final double PROBABILITY = 1;
	private Menu menu = new ShotgunSubMenu();
	private int[] xRange;
	private int[] yRange;
	private ArrayList<Actor> zombies;

	private enum Direction {
		NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST
	}

	/**
	 * Default constructor for ShotgunShootingAction class.
	 * 
	 * @param weapon weapon used
	 */
	public ShotgunShootingAction(WeaponItem weapon) {
		this.weapon = weapon;
	}

	/**
	 * During execution, user is prompted directions to choose from to fire at. Once selected, FireAction is executed
	 * that calculates the area of damage and hurts or kills zombies in the area.
	 *
	 * @param actor The actor performing the action.
	 * @param map   The map the actor is on.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Location location = map.locationOf(actor);
		Actions actions = new Actions();

		int x = location.x();
		int y = location.y();

		weapon.fire();

		actions.add(Arrays.asList(new FireAction(weapon, Direction.NORTH, false),
				new FireAction(weapon, Direction.NORTHEAST, true), new FireAction(weapon, Direction.EAST, false),
				new FireAction(weapon, Direction.SOUTHEAST, true), new FireAction(weapon, Direction.SOUTH, false),
				new FireAction(weapon, Direction.SOUTHWEST, true), new FireAction(weapon, Direction.WEST, false),
				new FireAction(weapon, Direction.NORTHWEST, true)));

		return menu.showMenu(actor, actions, new Display()).execute(actor, map);
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Fire Shotgun";
	}

	/**
	 * Special action for shooting zombies in a specific direction.
	 */
	private class FireAction extends Action {
		private static final int RANGE = 3;
		private Direction direction;
		private boolean cardinal;
		private int width;
		private int height;
		private WeaponItem weapon;
		private GameMap map;

		/**
		 * Base constructor for FireAction.
		 *
		 * @param weapon weapon of attack
		 * @param direction direction of attack
		 * @param cardinal boolean check; true if cardinal, false if not.
		 */
		public FireAction(WeaponItem weapon, Direction direction, boolean cardinal) {
			this.weapon = weapon;
			this.direction = direction;
			this.cardinal = cardinal;
		}

		/**
		 * Checks if the direction selected by player is a cardinal direction or one of the four main directions to
		 * calculate the area of damage and execute relative shooting methods.
		 *
		 * @param actor The actor performing the action.
		 * @param gameMap map where actor is in
		 */
		@Override
		public String execute(Actor actor, GameMap gameMap) {
			this.map = gameMap;
			width = map.getXRange().max();
			height = map.getYRange().max();
			int x = map.locationOf(actor).x();
			int y = map.locationOf(actor).y();
			if (!cardinal) {
				return shootingXY(x, y, direction);
			}
			return shootingCardinal(x, y, direction);
		}

		@Override
		public String menuDescription(Actor actor) {
			return "";
		}

		/**
		 * Execute the firing action that actually kills or hurts zombie actors in the
		 * area of damage.
		 * 
		 * @param xRange    player's x coordinate
		 * @param yRange    player's y coordinate
		 * @param cardinal  if the direction is a cardinal direction, true, else false
		 */
		private String executeFiring(int[] xRange, int[] yRange, boolean cardinal) {

			// Actors that were hurt
			if (!cardinal) {
				zombies = fireXYDirection(xRange, yRange);
			} else {
				zombies = fireCardinalDirection(xRange, yRange);
			}

			if (zombies.size() != 0 || (zombies != null)) {
				String output = "";

				for (Actor zombie : zombies) {
					output += System.lineSeparator() + zombie.toString() + " was shot by Shotgun for " + weapon.damage()
							+ " damage";
				}

				for (Actor zombie : zombies) {
					if (!zombie.isConscious()) {
						output += killTarget(zombie, map);
					}
				}

				return output;
			}
			return "Player missed";
		}

		/**
		 * Method to execute firing action towards the four main directions; north,
		 * south, east and west. x and y positions are determined based on input
		 * direction. Using these positions, x and y coordinate ranges are obtained and
		 * passed into the executeFiring() method.
		 * 
		 * @param x         player's x coordinate
		 * @param y         player's y coordinate
		 * @param direction direction fired
		 */
		private String shootingXY(int x, int y, Direction direction) {
			// Selecting length of arrays based on direction of attack.
			
			if (direction == Direction.NORTH || direction == Direction.SOUTH) {
				x -= RANGE + 1;
				xRange = new int[RANGE * 2 + 1];
				yRange = new int[RANGE];
			} else if (direction == Direction.EAST || direction == Direction.WEST) {
				y -= RANGE + 1;
				xRange = new int[RANGE];
				yRange = new int[RANGE * 2 + 1];
			}

			// Calculating x range
			for (int i = 0; i < xRange.length; i++) {
				if (direction == Direction.WEST) {
					x -= 1;
				} else {
					x += 1;
				}

				if (x >= 0 && x <= width) {
					xRange[i] = x;
				}
			}

			// Calculating y range
			for (int i = 0; i < yRange.length; i++) {
				if (direction == Direction.NORTH) {
					y -= 1;
				} else {
					y += 1;
				}
				if (y >= 0 && y <= height) {
					yRange[i] = y;
				}
			}
			// Fire Shotgun!
			return executeFiring(xRange, yRange, false);
		}

		/**
		 * Method to execute firing action towards the cardinal positions; north east,
		 * south east, north west and south west. x and y positions are determined based
		 * on input direction. Using these positions, x and y coordinate ranges are
		 * obtained and passed into the executeFiring() method.
		 * 
		 * @param x         x coordinate of the player
		 * @param y         y coordinate of the player
		 * @param direction direction fired
		 */
		private String shootingCardinal(int x, int y, Direction direction) {
			xRange = new int[RANGE + 1];
			yRange = new int[RANGE + 1];

			// Setting x position
			if (direction == Direction.NORTHEAST || direction == Direction.SOUTHEAST) {
				x -= 1;
			} else {
				x += 1;
			}

			// Setting y position
			if (direction == Direction.NORTHEAST || direction == Direction.NORTHWEST) {
				y += 1;
			} else {
				y -= 1;
			}

			// Calculating x range
			for (int i = 0; i < xRange.length; i++) {
				if (direction == Direction.NORTHEAST || direction == Direction.SOUTHEAST) {
					x += 1;
				} else {
					x -= 1;
				}

				if (x >= 0 && x <= width) {
					xRange[i] = x;
				}
			}

			// Calculating y range
			for (int i = 0; i < yRange.length; i++) {
				if (direction == Direction.NORTHEAST || direction == Direction.NORTHWEST) {
					y -= 1;
				} else {
					y += 1;
				}
				if (y >= 0 && y <= height) {
					yRange[i] = y;
				}
			}
			// Fire Shotgun!
			return executeFiring(xRange, yRange, true);
		}

		/**
		 * Used for north and south direction. Attack are is a triangle. For a 75%
		 * chance of success, if there is a zombie in the area of damage, damage is
		 * dealt.
		 * 
		 * @param x   x coordinate
		 * @param y   y coordinate
		 */
		private ArrayList<Actor> fireXYDirection(int[] x, int[] y) {

			int start = RANGE;
			int end = RANGE;
			int raise = 1;
			int range;
			ArrayList<Actor> hurtActors = new ArrayList<>();

			// checks if area of effect is in X direction or Y direction
			if (x.length == RANGE) {
				range = x.length;
			} else {
				range = y.length;
			}

			for (int i = 0; i < range; i++) {
				start -= raise;
				end += raise;

				while (start <= end) {
					if (x.length == RANGE) {
						addTarget(hurtActors, weapon, x[i], y[start]);
					} else {
						addTarget(hurtActors, weapon, x[start], y[i]);
					}
					start += 1;
				}
				raise += 1;
				start = RANGE;
				end = RANGE;
			}

			return hurtActors;
		}

		/**
		 * Used for cardinal directions (North west, North east etc...). Attack area is
		 * a square. For a 75% chance of success, if there is a zombie in the area of
		 * damage, damage is dealt.
		 * 
		 * @param x   x coordinate of player
		 * @param y   y coordinate of player
		 */
		private ArrayList<Actor> fireCardinalDirection(int[] x, int[] y) {
			ArrayList<Actor> hurtActors = new ArrayList<>();

			for (int xValue : x)
				for (int yValue : y)
					addTarget(hurtActors, weapon, xValue, yValue);

			return hurtActors;
		}

		private void addTarget(ArrayList<Actor> hurtActors, WeaponItem weapon, int x, int y) {
			if (map.at(x, y).containsAnActor()) {
				if (map.at(x, y).getActor().hasCapability(ZombieCapability.UNDEAD)) {
					Actor target = map.at(x, y).getActor();
					if (Math.random() <= PROBABILITY) {
						target.hurt(weapon.damage());
						hurtActors.add(target);
					}
				}
			}
		}
	}
}
