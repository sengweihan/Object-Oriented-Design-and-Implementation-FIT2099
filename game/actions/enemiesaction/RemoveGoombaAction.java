package game.actions.enemiesaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * a specific action class to remove Goomba from the map automatically.
 * this only runs when Goomba hits the 10% chance of being removed
 * from the map.
 */
public class RemoveGoombaAction extends Action {
    /**
     * an execute method that removes current Goomba from the map
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return menuDescription method.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return menuDescription(actor);
    }

    /**
     * displays a string that says current Goomba is removed
     *
     * @param actor The actor performing the action.
     * @return a string that tells the goomba is removed from the map.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is removed";
    }
}
