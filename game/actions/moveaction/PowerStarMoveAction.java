package game.actions.moveaction;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.ground.Dirt;
import game.items.Coin;

/**
 * specific move action when player has power star buffs
 * this is only available when the player is under the effect of IMMUNITY
 */
public class PowerStarMoveAction extends MoveActorAction {
    public PowerStarMoveAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }

    /**
     * an execute method that moves the actor to the location, and destroys
     * the ground of the new location the actor is in, and drops a coin there
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that tells the actor has moved to the new location
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor,moveToLocation);
        Location here = map.locationOf(actor);
        here.setGround(new Dirt());
        here.addItem(new Coin(5,"Coin($5)"));
        return actor + " moves to " + super.direction;
    }
}
