package game.ground.fountains;

import edu.monash.fit2099.engine.actors.Actor;
import game.Status;
import game.ground.fountains.FountainWater;
/**
 * A water that increases the domage of the actor deals after
 * actor consumes it
 */
public class PowerWater extends FountainWater {
    @Override
    public void consumeWater(Actor actor) {
        actor.addCapability(Status.DRANK_POWER);
    }

    @Override
    public String toString() {
        return "Power Water";
    }
}
