package game;

import edu.monash.fit2099.engine.Display;

public class ShotgunSubMenu {

    private Display display = new Display();

    public int showMenu(){
        display.println("Select direction to fire\n");
        display.println("1. North");
        display.println("2. North East");
        display.println("3. East");
        display.println("4. South East");
        display.println("5. South");
        display.println("6. South West");
        display.println("7. West");
        display.println("8. North West");
        
        char choice;
        int direction;
        do {
        	choice = display.readChar();
        	direction = Character.getNumericValue(choice);
        } while (direction > 8 || direction < 1);

        return direction;
    }
}
