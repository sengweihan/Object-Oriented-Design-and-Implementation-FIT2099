package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.moveaction.JumpAction;
import game.actions.moveaction.PowerStarMoveAction;
import game.actions.moveaction.TeleportAction;
import game.enemies.PiranhaPlant;
import game.interfaces.Jumpable;

public class WarpPipe extends Ground implements Jumpable {

    private int x;
    private int y;
    private GameMap targetMap;
    private int counter ;

    /**
     * Constructor.
     *
     */
    public WarpPipe(int x, int y ,GameMap targetMap) {
        super('C');
        this.addCapability(Status.TELEPORT);
        this.x = x;
        this.y = y;
        this.targetMap = targetMap;
        counter = 0;

    }

    /**
     * The first if statement is to check whether it is already the second
     * turn of the game, if it is then a piranha plant will be spawn on top of
     * the warp pipe.
     *
     * The second if statement is to check whether the warp pipe on the other side of map (target map)
     * has been destroyed due to power star effect, if it does then the warp pipe in the current
     * map will also be destroy as well since there's no need to maintain this warp pipe which
     * could not teleport the actor to the target map anymore.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        counter +=1;
        if (counter == 1 && !location.containsAnActor()){
            location.addActor(new PiranhaPlant());
        }

        if(!targetMap.at(x,y).getGround().hasCapability(Status.TELEPORT)){
            location.setGround(new Dirt());

        }


    }

    /**
     * Since warp pipe allow player to jump on top of it, thus it will also need to implement
     * the jumpable interface together with its method.
     *
     * @param actor
     * @param map
     * @param location
     * @return a string
     */
    @Override
    public String jump(Actor actor, GameMap map, Location location) {
        map.moveActor(actor, location);
        return actor + " is currently standing on top of the portal for teleportation ";
    }

    /**
     * Check for every possible cases that might happen :
     * 1) When the location of warp pipe does not contain any actor and at the same time, player
     * does not consume any power star then the player is allow to use jump action when encountering warp pipe.
     *
     * 2) When the player consumes power star and at the same time the location of warp pipe does not
     * contain any actor , then the player do not need to jump instead it can just walk normally
     * but for every ground that the player pass through, it will turn into a dirt and drop a coin.
     *
     * 3) If the location of warp pipe contains an actor and the actor is the player itself and at the
     * same time the warp pipe in the target map is not destroy by the power star effect, then
     * player can use the teleport action to teleport to the exact position of the warp pipe in the target map.
     *
     * 4) If all the above cases failed, then it will trigger the do nothing action meaning when
     * the player encounter the warp pipe, there's no action that are able to be perform by the player on
     * the warp pipe.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an action list
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actionList = new ActionList();
        if (!location.containsAnActor() && !actor.hasCapability(Status.IMMUNITY)){
            actionList.add(new JumpAction(this,location,direction));

        }

        else if (actor.hasCapability(Status.IMMUNITY) && !location.containsAnActor()){
            actionList.add(new PowerStarMoveAction(location,direction));
        }

        else if (location.getActor().hasCapability(Status.HOSTILE_TO_ENEMY) && targetMap.at(x,y).getGround().hasCapability(Status.TELEPORT)){
            actionList.add(new TeleportAction(x,y,targetMap));
        }

        else{
            actionList.add(new DoNothingAction());
        }
        return actionList;
    }



    /**
     * No actor can enter this ground
     * @param actor the Actor to check
     * @return a boolean (true/false)
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }


    /**
     * Print a string statement when an instance of this class is called.
     * @return a string
     */
    public String toString(){
        return "Warp pipe";
    }
}
