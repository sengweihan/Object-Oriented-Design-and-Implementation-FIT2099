package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;

/**
 * a specific action for player to reset the game
 * only calls if the game has not been reset before
 */
public class ResetAction extends Action {

    private ResetManager resetManager;

    /**
     * Constructor. Creates a ResetManager instance.
     */
    public ResetAction(){
        resetManager = ResetManager.getInstance();
    }

    /**
     * an execute method that runs the run() method in
     * ResetManager class.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that says the actor has reset the game
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        resetManager.run();
        return actor + " has reset the game!";
    }

    /**
     * displays that the game can be reset
     *
     * @param actor The actor performing the action.
     * @return the option string of resetting the game
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Resets the game";
    }

    /**
     * sets the reset hotkey to 'r'
     * @return a string of the letter "r"
     */
    @Override
    public String hotkey() {
        return "r";
    }
}
