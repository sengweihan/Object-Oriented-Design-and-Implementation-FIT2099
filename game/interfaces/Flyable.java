package game.interfaces;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An interface that allows classes that implements it to
 * be able to let actors to fly across
 */
public interface Flyable {
    String fly(Actor actor, GameMap map, Location location);
}
