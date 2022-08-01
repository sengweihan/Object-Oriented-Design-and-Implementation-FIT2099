package game.actions.enemiesaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * a class specifically designed for Koopas that is in Dormant state,
 */
public class DormantAction extends Action {
    /**
     * an execute method to display menuDescription method
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that tells the player current Koopa is in dormant state
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /**
     * display that current Koopa is in dormant state
     *
     * @param actor The actor performing the action.
     * @return a string that tells the player current Koopa is in dormant state
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is dormant!";
    }
}
