package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

public interface Jumpable {

    /**
     * A method that must be implemented by those class which implements this interface.
     *
     * @param actor
     * @param map
     * @param location
     * @return a string
     */
    String jump(Actor actor, GameMap map, Location location);
}
