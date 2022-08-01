package game.actions.moveaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Flyable;

/**
 * An action class that allows the actor that can fly to fly over tall grounds
 */
public class FlyAction extends Action {
    private Flyable flyable;
    private Location flyLocation;
    private String direction;

    /**
     * constructor
     *
     * @param flyable the ground that actor can fly over
     * @param moveToLocation the location the actor flies to
     */
    public FlyAction(Flyable flyable, Location moveToLocation, String direction){
        this.flyable = flyable;
        this.flyLocation = moveToLocation;
        this.direction = direction;
    }
    /**
     * execute method that simply takes in actor, map, and location
     * and calls the fly method in flyable to fly
     *
     * @param actor the ground that actor can fly over
     * @param map The map the actor is in
     * @return a string that returns the actor has flown to the direction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return flyable.fly(actor, map, flyLocation);
    }

    /**
     * display the menu description of what the actor can do, in this case,
     * fly over tall building
     *
     * @param actor The actor performing the action.
     * @return a string that displays the actor can fly to a direction
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " can fly to " + direction;
    }
}