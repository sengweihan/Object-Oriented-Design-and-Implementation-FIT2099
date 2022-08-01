package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.moveaction.LavaMoveAction;
import game.Status;


public class Lava extends Ground {
    /**
     * This ground has a burning capabilities
     *
     * Constructor.
     *
     */
    public Lava() {
        super('L');
        this.addCapability(Status.BURN);
    }


    /**
     * Enemies cannot step on this lava ground.
     * @param actor the Actor to check
     * @return a boolean (true or false)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * When the player is encountering this type of ground, then an action called lava move action
     * will be trigger and allow the player to step on this ground by using that action.
     *
     * However, if the player insist to step on this ground, then he/she will get inflicted a damage of 15
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an action list
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (!location.containsAnActor())
            actionList.add(new LavaMoveAction(location,direction));
        return actionList;
    }

    /**
     * A string "Lava Ground" will be printed when instance of this class is being called.
     * @return a string
     */
    public String toString(){
        return "Lava Ground";
    }

}
