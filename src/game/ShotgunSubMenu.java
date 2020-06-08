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

        int choice = display.readChar();
        switch (choice){

            case 1:
                return 1;

            case 2:
                return 2;

            case 3:
                return 3;

            case 4:
                return 4;

            case 5:
                return 5;

            case 6:
                return 6;

            case 7:
                return 7;

        }

        return 0;
    }
}
