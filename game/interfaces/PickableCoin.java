package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public interface PickableCoin {

    /**
     * A method that must be implemented by other classes which implements this interface.
     *
     * @param actor
     * @param map
     * @return a string
     */
    String pickableCoin(Actor actor, GameMap map);
}
