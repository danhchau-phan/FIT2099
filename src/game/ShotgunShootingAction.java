package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

/**
 * Special Action for shooting zombies with a shotgun.
 */
public class ShotgunShootingAction extends Action {
    /**
     * weapon      : shotgun
     * PROBABILITY : 75% chance of shotgun dealing damage.
     * menu        : Shotgun sub menu for actor to choose the direction to fire.
     * width       : X end of the map
     * height      : Y end of the map
     * xRange      : X coordinates of the attack area
     * yRange      : Y coordinates of the attack area
     * zombies     : Zombie actors that were hurt during firing
     */
    private WeaponItem weapon;
    private static final double PROBABILITY = 0.75;
    private ShotgunSubMenu menu = new ShotgunSubMenu();
    private int width;
    private int height;
    private int[] xRange;
    private int[] yRange;
    private ArrayList<Actor> zombies;


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
     * hurt the actors(zombies) in the area with a 75% chance of the attack being successful. If the actors is not conscious
     * the actor is removed from the map using killTarget() method. For every shot fired, player loses 1 ammo
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
    	Location location = map.locationOf(actor);
        int direction = menu.showMenu();
        width = map.getXRange().max();
        height = map.getYRange().max();
        int x = location.x();
        int y = location.y();

        weapon.fire();

        // For NORTH direction
        if (direction == 1){
            return shootingXY(x,y,"n",map);
        }

        // For SOUTH direction
        if (direction == 5){
            return shootingXY(x,y,"s",map);
        }

        // For EAST direction
        if (direction == 3){
            return shootingXY(x,y,"e",map);
        }

        // For WEST direction
        if (direction == 7){
            return shootingXY(x,y,"w",map);
        }

        // For NORTH EAST direction
        if (direction == 2){
            return shootingCardinal(x,y,"ne",map);
        }

        // For SOUTH EAST direction
        if (direction == 4){
            return shootingCardinal(x,y,"se",map);
        }

        // For SOUTH WEST direction
        if (direction == 6){
            return shootingCardinal(x,y,"sw",map);
        }

        // For NORTH WEST direction
        if (direction == 8){
            return shootingCardinal(x,y,"nw",map);
        }
        return "Player missed";

    }

    /**
     * Execute the firing action that actually kills or hurts zombie actors in the area of damage.
     * @param xRange player's x coordinate
     * @param yRange player's y coordinate
     * @param map map actor is in
     * @param direction 0 = XY direction, 1 = CardinalDirection
     */
    private String executeFiring(int[] xRange, int[] yRange, GameMap map, int direction){

        // Actors that were hurt
        if (direction == 0){
            zombies = fireXYDirection(xRange, yRange, map);
        }
        else if (direction == 1){
            zombies = fireCardinalDirection(xRange, yRange, map);
        }

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
    /**
     * Method to execute firing action towards the four main directions; north, south, east and west. x and y positions
     * are determined based on input direction. Using these positions, x and y coordinate ranges are obtained and
     * passed into the executeFiring() method.
     * @param x player's x coordinate
     * @param y player's y coordinate
     * @param direction direction fired
     * @param map map where the actor is
     */
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
            if (y >= 0 && y <= height) {
                yRange[i] = y;
            }
        }
        // Fire Shotgun!
        return executeFiring(xRange, yRange, map,0);
    }

    /**
     * Method to execute firing action towards the cardinal positions; north east, south east, north west and south
     * west. x and y positions are determined based on input direction. Using these positions, x and y coordinate
     * ranges are obtained and passed into the executeFiring() method.
     * @param x x coordinate of the player
     * @param y y coordinate of the player
     * @param direction direction fired
     * @param map map where the actor is
     */
    private String shootingCardinal(int x, int y, String direction, GameMap map){
        xRange = new int[4];
        yRange = new int[4];

        // Setting x position
        if (direction.equalsIgnoreCase("ne") || direction.equalsIgnoreCase("se")){
            x -= 1;
        }
        else {
            x += 1;
        }

        // Setting y position
        if (direction.equalsIgnoreCase("ne") || direction.equalsIgnoreCase("nw")){
            y += 1;
        }
        else {
            y -= 1;
        }

        // Calculating x range
        for (int i = 0; i < xRange.length; i++){
            if (direction.equalsIgnoreCase("ne") || direction.equalsIgnoreCase("se")){
                x += 1;
            }
            else {
                x -= 1;
            }

            if (x >= 0 && x <= width) {
                xRange[i] = x;
            }
        }

        // Calculating y range
        for (int i = 0; i < yRange.length; i++){
            if (direction.equalsIgnoreCase("ne") || direction.equalsIgnoreCase("nw")){
                y -= 1;
            }
            else {
                y += 1;
            }
            if (y >= 0 && y <= height) {
                yRange[i] = y;
            }
        }
        // Fire Shotgun!
        return executeFiring(xRange, yRange, map,1);
    }
    @Override
    public String menuDescription(Actor actor) {
        return "Fire Shotgun";
    }

    /**
     * Used for north and south direction. Attack are is a triangle.
     * For a 75% chance of success, if there is a zombie in the area of damage, damage is dealt.
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
     * For a 75% chance of success, if there is a zombie in the area of damage, damage is dealt.
     * @param x x coordinate of player
     * @param y y coordinate of player
     * @param map map where actor is
     */
    public ArrayList<Actor> fireCardinalDirection(int[] x, int[] y, GameMap map){
        ArrayList<Actor> hurtActors = new ArrayList<>();

        for (int xValue : x){
            for (int yValue : y){
//                map.at(xValue,yValue).setGround(new Crop()); // Testing
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
