package game;

/**
 * A Food item dropped by Farmer when harvesting. Can be used to increase
 *
 */
public class Food extends PortableItem {

    private final static double HEALTH = 10;

    /**
     * Default constructor of Food
     */
    public Food() {
        super("Bread", 'o');
    }

    /**
     * Returns the health value regained by consuming Food item
     *
     * @return health value
     */
    public static double getHEALTH() {
        return HEALTH;
    }
}
