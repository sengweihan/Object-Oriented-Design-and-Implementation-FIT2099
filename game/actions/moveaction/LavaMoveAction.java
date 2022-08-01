package game.actions.moveaction;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class LavaMoveAction extends MoveActorAction {

    /**
     * A lava move action class's constructor.
     * @param moveToLocation
     * @param direction
     */
    public LavaMoveAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }

    /**
     * When the current ground contains the status burn meaning it is a lava ground, then
     * it will move the actor to that ground and the actor will get inflicted a damage of 15 due
     * to extreme burning or heat from the ground.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (moveToLocation.getGround().hasCapability(Status.BURN)){
            map.moveActor(actor,moveToLocation);
            actor.hurt(15);

        }
        if (!actor.isConscious()){
            map.removeActor(actor);
        }
        return "ARGHHH " + actor + " is currently burning!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " moves to " + moveToLocation.getGround() + " in " + direction;
    }
}
