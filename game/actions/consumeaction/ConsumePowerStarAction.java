package game.actions.consumeaction;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.ConsumableItems;
/**
 * a subclass of ConsumeItemAction, specifically to consume Power Star
 */
public class ConsumePowerStarAction extends ConsumeItemAction {

    public ConsumePowerStarAction(ConsumableItems item){
        super(item);
    }

    /**
     * an execute method to run so that player consumes the power star
     * and obtain the buffs by calling the consumeItem method
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that tells the player it has consumed power star
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.IMMUNITY)){
            return actor + " has already consumed a Power Star!";
        }
        else {
            if (actor.getInventory().contains(item)) {
                actor.removeItemFromInventory(item);
                item.consumeItem(actor);
            }
            else {
                map.locationOf(actor).removeItem(item);
                item.consumeItem(actor);
            }
            return actor + " has consumed Power Star!";
        }
    }
}
