package game.actions.moveaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.interfaces.Jumpable;

public class JumpAction extends Action {
    private Jumpable jumpable;
    private Location jumpLocation;
    private String direction;

    public JumpAction(Jumpable jumpable, Location moveToLocation, String direction){
        this.jumpable = jumpable;
        this.jumpLocation = moveToLocation;
        this.direction = direction;
    }

    /**
     * This method will call the jumpable instance's jump method and execute them
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return jumpable.jump(actor,map,jumpLocation);
    }

    /**
     * This method will be display in the console to allow player to select that particular action
     * to perform.
     *
     * @param actor The actor performing the action.
     * @return a string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps to the " + direction + " " + jumpable;
    }
}
