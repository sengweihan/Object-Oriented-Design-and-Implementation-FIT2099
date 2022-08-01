package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action class that allows the wielder of a key to end the game.
 */
public class EndGameAction extends Action {
    /**
     * execute method that runs and removes the actor that wields the
     * key from the map.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return actor + " has successfully rescued Princess Peach!";
    }

    /**
     * display the menu description of what the actor can do
     *
     * @param actor The actor performing the action.
     * @return a string that displays the actor can save the princess
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " saves Princess Peach!";
    }
}
