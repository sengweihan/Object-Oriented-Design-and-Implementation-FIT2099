package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.Status;
import game.actions.moveaction.FlyAction;
import game.actions.moveaction.JumpAction;
import game.actions.moveaction.PowerStarMoveAction;
import game.interfaces.Flyable;
import game.interfaces.Jumpable;
import game.reset.Resettable;

import java.util.Random;

public abstract class Tree extends Ground implements Resettable, Jumpable, Flyable {
    protected Random rand = new Random();
    protected final int removed = 5;
    /**
     * Constructor.
     *
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.registerInstance();
    }

    /**
     * This method will prevent actor from entering this ground unless they uses a jump action.
     *
     * @param actor the Actor to check
     * @return a boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * This method will check whether the player already consume power star if it does
     * then it will add the PowerStarMoveAction into the action list or else it will
     * add the jump action to the action list.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an actionlist
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (actor.hasCapability(Status.IMMUNITY) && !location.containsAnActor()){
            actionList.add(new PowerStarMoveAction(location,direction));
        }
        else if (!location.containsAnActor()){
            actionList.add(new JumpAction(this,location,direction));
        }
        else if (actor.hasCapability(Status.FLYING) && !location.containsAnActor()){
            actionList.add(new FlyAction(this, location, direction));
        }
        return actionList;

    }

    @Override
    public String fly(Actor actor, GameMap map, Location location) {
        map.moveActor(actor,location);
        return actor + " flies to " + location;
    }

    @Override
    public void resetInstance(GameMap map) {
        NumberRange x = map.getXRange();
        NumberRange y = map.getYRange();
        for (int i : x){
            for (int j : y){
                Location here = map.at(i,j);
                if (here.getGround() == this){
                    if (removed >= rand.nextInt(10)){
                        here.setGround(new Dirt());
                    }
                }
            }
        }
    }
}
