package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Special Action for shooting zombies with a shotgun.
 */
public class ShotgunShootingAction extends Action {
    /**
     * weapon      : shotgun
     * location    : location of actor.
     * PROBABILITY : 75% chance of shotgun dealing damage.
     * menu        : Shotgun sub menu for actor to choose the direction to fire.
     */
    private Location location;
    private WeaponItem weapon;
    private static final double PROBABILITY = 0.75;
    private ShotgunSubMenu menu = new ShotgunSubMenu();

    private int width;
    private int height;
    private int[] xRange;
    private int[] yRange;

    /**
     * Default constructor for ShotgunShootingAction class
     * @param weapon weapon used
     */
    public ShotgunShootingAction(WeaponItem weapon) {
        this.weapon = weapon;
    }

    /**
     * Based on the directional input of the user, appropriate calculations are done to find the x and y coordinates
     * of the attack area of the shotgun. Then appropriate attack method for that direction is implemented to
     * hurt the actors in the area with a 75% chance of the attack being successful. If the actors is not conscious
     * the actor is removed from the map using killTarget() method. For every shot fired, player loses 1 ammo
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
    	location = map.locationOf(actor);
        int direction = menu.showMenu();
        width = map.getXRange().max();
        height = map.getYRange().max();
        int x = location.x();
        int y = location.y();

        weapon.fire();

        // For NORTH direction
        if (direction == 1){
            shootingXY(x,y,"n",map);
        }

        // For SOUTH direction
        if (direction == 5){
            shootingXY(x,y,"s",map);
        }

        // For EAST direction
        if (direction == 3){
            shootingXY(x,y,"e",map);
        }

        // For WEST direction
        if (direction == 7){
            shootingXY(x,y,"w",map);
        }

        // For NORTH EAST direction
        if (direction == 2){
            int[] xRange = new int[4];
            int[] yRange = new int[4];
            x -= 1;
            y += 1;

            // Calculating x range
            for (int i = 0; i < xRange.length; i++){
                x += 1;
                if (x >= 0 && x < 80) {
                    xRange[i] = x;
                }
            }

            // Calculating y range
            for (int i = 0; i < yRange.length; i++){
                y -= 1;
                if (y >= 0 && y < 25) {
                    yRange[i] = y;
                }
            }

            ArrayList<Actor> zombies = fireCardinalDirection(xRange, yRange, map); // Actors that were hurt

            if (zombies.size() != 0 || (zombies != null)){
                String output = "";

                for (Actor zombie : zombies){
                    output += System.lineSeparator() + zombie.toString() + " was shot by Shotgun for " + weapon.damage() + " damage";
                }

                for (Actor zombie : zombies){
                    if (!zombie.isConscious()){
                        output += killTarget(zombie, map);
                    }
                }

                return output;
            }
            else {
                return "Player missed";
            }
        }

        // For SOUTH EAST direction
        if (direction == 4){
            int[] xRange = new int[4];
            int[] yRange = new int[4];
            x -= 1;
            y -= 1;

            // Calculating x range
            for (int i = 0; i < xRange.length; i++){
                x += 1;
                if (x >= 0 && x < 80) {
                    xRange[i] = x;
                }
            }

            // Calculating y range
            for (int i = 0; i < yRange.length; i++){
                y += 1;
                if (y >= 0 && y < 25) {
                    yRange[i] = y;
                }
            }

            ArrayList<Actor> zombies = fireCardinalDirection(xRange, yRange, map); // Actors that were hurt

            if (zombies.size() != 0 || (zombies != null)){
                String output = "";

                for (Actor zombie : zombies){
                    output += System.lineSeparator() + zombie.toString() + " was shot by Shotgun for " + weapon.damage() + " damage";
                }

                for (Actor zombie : zombies){
                    if (!zombie.isConscious()){
                        output += killTarget(zombie, map);
                    }
                }

                return output;
            }
            else {
                return "Player missed";
            }
        }

        // For SOUTH WEST direction
        if (direction == 6){
            int[] xRange = new int[4];
            int[] yRange = new int[4];
            x += 1;
            y -= 1;

            // Calculating x range
            for (int i = 0; i < xRange.length; i++){
                x -= 1;
                if (x >= 0 && x < 80) {
                    xRange[i] = x;
                }
            }

            // Calculating y range
            for (int i = 0; i < yRange.length; i++){
                y += 1;
                if (y >= 0 && y < 25) {
                    yRange[i] = y;
                }
            }

            ArrayList<Actor> zombies = fireCardinalDirection(xRange, yRange, map); // Actors that were hurt

            if (zombies.size() != 0 || (zombies != null)){
                String output = "";

                for (Actor zombie : zombies){
                    output += System.lineSeparator() + zombie.toString() + " was shot by Shotgun for " + weapon.damage() + " damage";
                }

                for (Actor zombie : zombies){
                    if (!zombie.isConscious()){
                        output += killTarget(zombie, map);
                    }
                }

                return output;
            }
            else {
                return "Player missed";
            }
        }

        // For NORTH WEST direction
        if (direction == 8){
            int[] xRange = new int[4];
            int[] yRange = new int[4];
            x += 1;
            y += 1;

            // Calculating x range
            for (int i = 0; i < xRange.length; i++){
                x -= 1;
                if (x >= 0 && x < 80) {
                    xRange[i] = x;
                }
            }

            // Calculating y range
            for (int i = 0; i < yRange.length; i++){
                y -= 1;
                if (y >= 0 && y < 25) {
                    yRange[i] = y;
                }
            }

            ArrayList<Actor> zombies = fireCardinalDirection(xRange, yRange, map); // Actors that were hurt

            if (zombies.size() != 0 || (zombies != null)){
                String output = "";

                for (Actor zombie : zombies){
                    output += System.lineSeparator() + zombie.toString() + " was shot by Shotgun for " + weapon.damage() + " damage";
                }

                for (Actor zombie : zombies){
                    if (!zombie.isConscious()){
                        output += killTarget(zombie, map);
                    }
                }

                return output;
            }
            else {
                return "Player missed";
            }
        }
        return "Player missed";

    }

    private String shootingXY(int x, int y, String direction, GameMap map) {

            // Selecting length of arrays based on direction of attack.
            if (direction.equalsIgnoreCase("n") || direction.equalsIgnoreCase("s")){
                x -= 4;
                xRange = new int[7];
                yRange = new int[3];
            }
            else if (direction.equalsIgnoreCase("e") || direction.equalsIgnoreCase("w")){
                y -= 4;
                xRange = new int[3];
                yRange = new int[7];
            }

            // Calculating x range
            for (int i = 0; i < xRange.length; i++){
                if (direction.equalsIgnoreCase("w")){
                    x -= 1;
                }
                else {
                    x += 1;
                }

                if (x >= 0 && x < width) {
                    xRange[i] = x;
                }
            }

            // Calculating y range
            for (int i = 0; i < yRange.length; i++){
                if (direction.equalsIgnoreCase("n")){
                    y -= 1;
                }
                else {
                    y += 1;
                }
                if (y >= 0 && y < height) {
                    yRange[i] = y;
                }
            }

            ArrayList<Actor> zombies = fireXYDirection(xRange, yRange, map); // Actors that were hurt

            if (zombies.size() != 0 || (zombies != null)){
                String output = "";

                for (Actor zombie : zombies){
                    output += System.lineSeparator() + zombie.toString() + " was shot by Shotgun for " + weapon.damage() + " damage";
                }

                for (Actor zombie : zombies){
                    if (!zombie.isConscious()){
                        output += killTarget(zombie, map);
                    }
                }

                return output;
            }
            else {
                return "Player missed";
            }
    }
    @Override
    public String menuDescription(Actor actor) {
        return "Fire Shotgun";
    }

    /**
     * Used for north and south direction. Attack are is a triangle.
     * For a 75% chance of success, if there is a actor in the area of damage, damage is dealt.
     * @param x x coordinate
     * @param y y coordinate
     * @param map map where the actor is
     */
    public ArrayList<Actor> fireXYDirection(int[] x, int[] y, GameMap map){

        int start = 3;
        int end = 3;
        int raise = 1;
        int pointer;
        int range;
        ArrayList<Actor> hurtActors = new ArrayList<>();

        // checks if area of effect is in X direction or Y direction
        if (x.length == 3){
            range = x.length;
        }
        else {
            range = y.length;
        }

        for (int i = 0; i < range; i++){
            start -= raise;
            end += raise;

            while (start <= end){
                if (x.length == 3){
                    pointer = y[start];
//                    map.at(x[i], pointer).setGround(new Crop()); // Testing
                    if (map.at(x[i],pointer).containsAnActor()){
                        if (map.at(x[i],pointer).getActor().hasCapability(ZombieCapability.UNDEAD)){
                            Actor target = map.at(x[i],pointer).getActor();
                            if (Math.random() <= PROBABILITY){
                                target.hurt(weapon.damage());
                                hurtActors.add(target);
                            }
                        }
                    }
                }
                else {
                    pointer = x[start];
//                    map.at(pointer, y[i]).setGround(new Crop()); // Testing
                    if (map.at(pointer,y[i]).containsAnActor()){
                        if (map.at(pointer,y[i]).getActor().hasCapability(ZombieCapability.UNDEAD)){
                            Actor target = map.at(pointer,y[i]).getActor();
                            if (Math.random() <= PROBABILITY){
                                target.hurt(weapon.damage());
                                hurtActors.add(target);
                            }
                        }
                    }
                }
                start += 1;
            }
            raise += 1;
            start = 3;
            end = 3;
        }

        return hurtActors;
    }

    /**
     * Used for cardinal directions (North west, North east etc...). Attack area is a square.
     * For a 75% chance of success, if there is a actor in the area of damage, damage is dealt.
     * @param x x coordinate of player
     * @param y y coordinate of player
     * @param map map where actor is
     */
    public ArrayList<Actor> fireCardinalDirection(int[] x, int[] y, GameMap map){
        ArrayList<Actor> hurtActors = new ArrayList<>();

        for (int xValue : x){
            for (int yValue : y){
//                map.at(xValue,yValue).setGround(new Crop()); / Testing
                if (map.at(xValue,yValue).containsAnActor()){
                    if (map.at(xValue,yValue).getActor().hasCapability(ZombieCapability.UNDEAD)){
                        Actor target = map.at(xValue,yValue).getActor();
                        if (Math.random() <= PROBABILITY){
                            target.hurt(weapon.damage());
                            hurtActors.add(target);
                        }
                    }
                }
            }
        }

        return hurtActors;
    }
    /**
     * Method to kill a zombie, remove it from its location and drop an items in its inventory.
     * @param target zombie to be killed
     * @param map map the actor is on
     * @return a string output
     */
    public String killTarget(Actor target, GameMap map){

        // Drops inventory items
        Actions dropActions = new Actions();
        for (Item item : target.getInventory())
            dropActions.add(item.getDropAction());
        for (Action drop : dropActions)
            drop.execute(target, map);

        map.removeActor(target);
        return System.lineSeparator() + target + " is killed.";
    }
}
