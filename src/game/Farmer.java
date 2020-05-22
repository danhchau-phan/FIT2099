package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class representing an ordinary Farmer.
 *
 */

public class Farmer extends Human {

    private Behaviour behaviour = new WanderBehaviour();
    private final static double PROBABILITY = 0.33;

    /**
     * Default constructor creates Farmer
     *
     * @param name the Farmer's display name
     */
    public Farmer(String name) {
        super(name, DisplayChar.FARMER.toChar(), 50);
    }

    /**
     * During Farmer's playturn, if there is a patch of Dirt next to Farmer, there is a 33% chance
     * of Farmer sowing a Crop. If the Farmer is on a ripe Crop or is next to one, Farmer harvests it
     * leaving a Food item at the location of the harvested Crop. If there is a Farmer at the Crop's location,
     * Farmer fertilizes it by adding 10 turns to ripe.
     *
     * @param actions list of possible actions
     * @param lastAction previous action
     * @param map map where current Farmer is
     * @param display the Display where the Farmer's utterances will be displayed
     * @return wandering behaviour
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        // Checking which ground type is available around farmer
        List<Exit> exits = new ArrayList<Exit>(map.locationOf(this).getExits());
        Collections.shuffle(exits);

        for (Exit e: exits) {
            // Grow crops if farmer is next to a patch of dirt
            if (e.getDestination().getGround().getDisplayChar() == DisplayChar.DIRT.toChar()) {
                double rand = (new Random()).nextDouble();
                if (rand < PROBABILITY){
                    e.getDestination().setGround(new Crop());
                    return behaviour.getAction(this, map);
                }
            }
        }

        // If farmer is standing on a ripe crop, Farmer fertilizes it
        if (map.locationOf(this).getGround().getDisplayChar() == DisplayChar.CROP.toChar()){
            Crop crop = (Crop) map.locationOf(this).getGround();
            int ripeAge = crop.getRipe();

            if (ripeAge <= 10){
                crop.setRipe(ripeAge + 10);
            }

            else crop.setRipe(20);

            return behaviour.getAction(this, map);
        }

        // If farmer is on a ripe Crop
        if (map.locationOf(this).getGround().getDisplayChar() == DisplayChar.RIPECROP.toChar()){
            map.locationOf(this).setGround(new Dirt());
            map.locationOf(this).addItem(new Food());
            display.println(this + " harvested food!");
            return behaviour.getAction(this,map);
        }

        // If farmer is next to a ripe Crop
        for (Exit e: exits){
            if (e.getDestination().getGround().getDisplayChar() == DisplayChar.RIPECROP.toChar()){
                e.getDestination().setGround(new Dirt());
                e.getDestination().addItem(new Food());
                display.println(this + " harvested food!");
                return behaviour.getAction(this, map);
            }
        }

        return behaviour.getAction(this, map);
    }
}
