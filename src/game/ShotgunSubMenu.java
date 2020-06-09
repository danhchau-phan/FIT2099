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

        int choice = display.readChar();
        int direction = 0;

        switch (choice){

            case '1':
                direction = 1;
                break;

            case '2':
                direction = 2;
                break;

            case '3':
                direction = 3;
                break;

            case '4':
                direction = 4;
                break;

            case '5':
                direction = 5;
                break;

            case '6':
                direction = 6;
                break;

            case '7':
                direction = 7;
                break;

            case '8':
                direction = 8;
                break;
        }

        return direction;
    }
}
