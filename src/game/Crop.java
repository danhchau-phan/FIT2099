package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Crop extends Ground {

    private int ripe = 0;

    public Crop() {
        super('!');
    }

    public void setRipe(int ripe) {
        this.ripe = ripe;
    }

    public int getRipe() {
        return ripe;
    }

    @Override
    public void tick(Location location) {
        super.tick(location);

        ripe++;

        if (ripe == 20){
            displayChar = '$';
        }

    }
}
