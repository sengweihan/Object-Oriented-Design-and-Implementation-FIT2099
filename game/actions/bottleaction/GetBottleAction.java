package game.actions.bottleaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.Bottle;

/**
 * An action class that allows the user to get the bottle from Toad
 */

public class GetBottleAction extends Action {

    /**
     * execute method that creates a Bottle instance and add it to the actor's inventory
     * and set the designated player to the bottle.
     * adds a status to the actor so that actor cannot obtain bottle again
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string message that displays the actor has obtained the bottle
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(new Bottle());
        Bottle.setPlayer(actor);
        actor.addCapability(Status.HAS_BOTTLE);
        return actor + " obtains bottle!";
    }

    /**
     * display the menu description of what the actor can do, in this case,
     * obtain the bottle from Toad.
     *
     * @param actor The actor performing the action.
     * @return a string that says actor can obtain bottle
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " obtains bottle";
    }
}
