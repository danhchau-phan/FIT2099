package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Action;

/**
 * A Food item dropped by Farmer when harvesting. Can be used to increase
 *
 */
public class Food extends PortableItem {

    private int health = 10;

    /**
     * Default constructor of Food
     */
    public Food() {
        super("Bread", DisplayChar.FOOD.toChar());
    }

    /**
     * Returns the health value regained by consuming Food item
     *
     * @return health value
     */
    @Override
    public int getHealth() {
        return health;
    }
    
    public List<Action> getAllowableActions() {
		return Arrays.asList(new EatAction(this));
    	
    }
}
