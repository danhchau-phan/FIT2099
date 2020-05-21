package game;

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
    public int getHealth() {
        return health;
    }
}
