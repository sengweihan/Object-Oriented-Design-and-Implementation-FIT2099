package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.reset.Resettable;

/**
 * Base class for ConsumeableItems. These represent items that can be consumed by the actor
 */
public abstract class ConsumableItems extends Item implements Resettable {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public ConsumableItems(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.registerInstance();
    }

    /**
     * if the item is picked up, it cannot be dropped
     * @param actor the actor that drops the item
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * abstract method that applies the buffs to the actor
     * after the actor consumes the item.
     *
     * @param actor the actor that consumes the item
     */
    public abstract void consumeItem(Actor actor);

    @Override
    public void resetInstance(GameMap map) {
        NumberRange x = map.getXRange();
        NumberRange y = map.getYRange();
        for (int i : x){
            for (int j : y){
                Location here = map.at(i,j);
                if (here.getItems().contains(this)){
                    here.removeItem(this);
                }


            }
        }
    }
}
