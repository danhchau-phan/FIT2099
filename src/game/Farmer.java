package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Farmer extends Human {

    private Behaviour behaviour = new WanderBehaviour();
    private final static double PROBABILITY = 0.33;

    public Farmer(String name) {
        super(name, 'F', 50);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        // Checking which ground type is available around farmer
        List<Exit> exits = new ArrayList<Exit>(map.locationOf(this).getExits());
        Collections.shuffle(exits);

        for (Exit e: exits) {
            // Grow crops if farmer is next to a patch of dirt
            if (e.getDestination().getGround().getDisplayChar() == '.') {
                double rand = (new Random()).nextDouble();
                if (rand < PROBABILITY){
                    e.getDestination().setGround(new Crop());
                }
            }
        }

        if (map.locationOf(this).getGround().getDisplayChar() == '!'){
            return new FertilizeAction(map.locationOf(this).getGround(), this);
        }


        return behaviour.getAction(this, map);
    }
}
