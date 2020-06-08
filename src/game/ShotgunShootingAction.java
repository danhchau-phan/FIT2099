package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;


public class ShotgunShootingAction extends Action {

    private int damage;
    private Location location;
    private static final double PROBABILITY = 0.75;
    private ShotgunSubMenu menu = new ShotgunSubMenu();


    public ShotgunShootingAction(Location location, int damage) {
        this.location = location;
        this.damage = damage;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        int direction = menu.showMenu();
        int x = location.x();
        int y = location.y();

        if (direction == 1){
            x -= 4;
            int[] xRange = new int[7];
            int[] yRange = new int[3];

            for (int i = 0; i < xRange.length; i++){
                x += 1;
                xRange[i] = x;
            }

            for (int i = 0; i < yRange.length; i++){
                y -= 1;
                yRange[i] = y;
            }

            fireY(xRange, yRange, map);
        }

        if (direction == 5){
            x -= 4;
            int[] xRange = new int[7];
            int[] yRange = new int[3];

            for (int i = 0; i < xRange.length; i++){
                x += 1;
                xRange[i] = x;
            }

            for (int i = 0; i < yRange.length; i++){
                y += 1;
                yRange[i] = y;
            }

            fireY(xRange, yRange, map);
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Fire Shotgun";

    }

    public void fireY(int[] x, int[] y, GameMap map){

        int start = 3;
        int end = 3;
        int counter = -1;
        int raise = 1;

        for (int i = 0; i < y.length; i++){
            counter += 2;
            start -= raise;
            end += raise;

            while (start <= end){
                int xDirection = x[start];
                map.at(xDirection, y[i]).setGround(new Crop());
//                if (map.at(xDirection,y[i]).containsAnActor()){
//                    if (Math.random() >= PROBABILITY){
//                        map.at(xDirection,y[i]).getActor().hurt(damage);
//                        }
//                }

                start += 1;

            }
            raise += 1;
            start = 3;
            end = 3;
        }
    }
}
