package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;

import java.util.ArrayList;
import java.util.HashMap;

public class SniperSubMenu {

    private Display display = new Display();

    public Actor showTargets(ArrayList<Actor> zombies){
        ArrayList<Character> freeChars = new ArrayList<Character>();
        HashMap<Character, Actor> keyToActorMap = new HashMap<Character, Actor>();
        int counter = 0;

        for (char i = 'a'; i <= 'z'; i++)
            freeChars.add(i);

        display.println("Select a target to aim");

        for (Actor zombie : zombies){
            char c = freeChars.get(counter);
            keyToActorMap.put(c, zombie);
            display.println(c + ":" + zombie.toString());
            counter++;
        }

        char key;
        do {
            key = display.readChar();
        } while (!keyToActorMap.containsKey(key));

        return keyToActorMap.get(key);
    }

    public int showMenu() {
        display.println("Select an option");
        display.println("1. Fire");
        display.println("2. Aim");
        display.println("3. Retreat");

        int choice = display.readChar();
        int selection = 0;

        switch (choice) {

            case '1':
                selection = 1;
                break;

            case '2':
                selection = 2;
                break;

            case '3':
                selection = 3;
                break;
        }

        return selection;
    }
}
