package game.actions.consumeaction;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.ConsumableItems;

public class ConsumeFireFlower extends ConsumeItemAction{

    /**
     * A consume fire flower class's constructor.
     * @param item
     */
    public ConsumeFireFlower(ConsumableItems item) {
        super(item);
    }

    /**
     * If the player has already consumed one fire flower,then if the player
     * choose to consume it second time, a string will be printed to notify that the player
     * has already consumed one and restrict it to not be able to consume another one unless
     * the fire attack status on the player fades after 20 rounds in the game.
     *
     * Else , the current fire flower on the ground where the player is standing will be remove from the map
     * and the consume item method will be called.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.FIRE_ATTACK)){
            return actor + " has already consumed a Fire Flower!";
        }
        map.locationOf(actor).removeItem(item);
        item.consumeItem(actor);

        return actor + " consumes Fire Flower";
    }

}
