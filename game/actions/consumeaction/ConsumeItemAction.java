package game.actions.consumeaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.items.ConsumableItems;

/**
 *  an abstract class for consuming ConsumableItems
 */

public abstract class ConsumeItemAction extends Action {
    /**
     * The item to be consumed
     */
    protected ConsumableItems item;

    /**
     * Constructor
     * @param item the item to be consumed.
     */
    public ConsumeItemAction(ConsumableItems item){
        this.item = item;
    }

    /**
     * display the menu description of what the actor can do, in this case, consume a
     * ConsumeableItem
     *
     * @param actor The actor performing the action.
     * @return the menu description of "actor can consume this item"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " can consume " + item;
    }
}
