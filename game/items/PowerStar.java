package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.consumeaction.ConsumePowerStarAction;

/**
 * a magical item that provides immunity to the actor
 * upon being consumed
 */
public class PowerStar extends ConsumableItems{
    protected int tick = 0;
    protected static final int TICK_COUNT = 11;

    /***
     * Constructor.
     */
    public PowerStar() {
        super("Power Star", '*', true);
        this.addAction(new ConsumePowerStarAction(this));
    }

    /**
     * ticks the power star object, if reaches 10 turns, it will
     * be removed from the actor's inventory
     *
     * @param location current location of the item
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location location, Actor actor){
        if (tick < TICK_COUNT && tick != 10){
            tick++;
        }
        else {
            actor.removeItemFromInventory(this);
        }
    }

    /**
     * ticks the power star object, if reaches 10 turns, it will
     * be removed from the location of the power star
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (tick < TICK_COUNT && tick != 10){
            tick++;
        }
        else {
            currentLocation.removeItem(this);
        }
    }

    /**
     * upon being consumed, heals actor by 200hp
     * applies IMMUNITY status to the actor
     *
     * @param actor the actor that consumes the item
     */
    @Override
    public void consumeItem(Actor actor) {
        actor.heal(200);
        actor.addCapability(Status.IMMUNITY);
    }

    /**
     * displays how many turns left before power star disappears
     * from the game
     *
     * @return a string that shows how many turns left before power star disappears
     * from the game
     */
    @Override
    public String toString() {
        return "PowerStar - " + (TICK_COUNT-tick) + " turns remaining";
    }
}
