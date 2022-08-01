package game.actions.moveaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class TeleportAction extends Action {

    private int x;
    private int y;
    private GameMap targetMap;

    /**
     * A teleport action class's constructor.
     * @param x
     * @param y
     * @param targetMap
     */
    public TeleportAction(int x, int y ,GameMap targetMap) {
        this.x = x;
        this.y = y;
        this.targetMap = targetMap;
    }

    /**
     * If the target map's warp pipe contains an actor on it, then when the player is teleporting
     * to the target map, the actor on the side of the target map will automatically be removed from the map
     * since there cannot be 2 actors on the same ground.
     *
     * The actor will be removed from the current map and being added to the second map at the exact
     * position where the warp pipe belongs to in first and second map.Then a string message will be
     * printed indicating that the player has successfully teleported to another map.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string
     */
    @Override
    public String execute(Actor actor, GameMap map) {


        if (targetMap.at(x,y).containsAnActor()){
            targetMap.removeActor(targetMap.at(x,y).getActor());
        }

        map.removeActor(actor);
        targetMap.addActor(actor, targetMap.at(x,y));

        return actor + " " + menuDescription(actor);


    }

    /**
     * Just to differentiate between 2 maps to allow the correct console statement to be
     * printed out in the console so that player can use that action to teleport to another map.
     *
     * @param actor The actor performing the action.
     * @return a string
     */
    @Override
    public String menuDescription(Actor actor) {
        if (targetMap.at(44,11).containsAnActor()){
            return "Teleport to original map";
        }
        return "Teleport to Lava Zone";
    }
}
