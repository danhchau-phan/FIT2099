package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SniperSubMenu extends Menu{
	public static final int REDIX = 10;
    private Actor target;
    
    public SniperSubMenu(Actor target) {
    	super();
    	this.target = target;
    }
    
    public SniperSubMenu(List<Actor> targets, Actor actor) {
    	super();
    	this.target = showTargets(targets);
    	actor.setZombieTarget(target);
    } 
    public Actor showTargets(List<Actor> zombies){
        ArrayList<Character> freeChars = new ArrayList<Character>();
        HashMap<Character, Actor> keyToActorMap = new HashMap<Character, Actor>();
        Display display = new Display(); 
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
    
    public Action showMenu(Actor actor, Actions actions, Display display) {
    	display.println("Select an option");
        
        HashMap<Character, Action> keyToActionMap = new HashMap<Character, Action>();
		for (int i = 0; i < actions.size(); i++) {
			char c = Character.forDigit(i+1, REDIX);
			actions.get(i).setTarget(this.target);
			keyToActionMap.put(c, actions.get(i));
			display.println(c + ": " + actions.get(i).menuDescription(actor));
		}
        
        
        char choice;
        do {
        	choice = display.readChar();
        } while (!keyToActionMap.containsKey(choice));

        return keyToActionMap.get(choice);
    }
}
