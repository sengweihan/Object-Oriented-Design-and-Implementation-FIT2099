package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.consumeaction.ConsumeFireFlower;

public class FireFlower extends ConsumableItems {

    protected static final int TICK_COUNT = 21;
    protected  int counter ;
    protected static final int NUMBER_OF_TURNS_EXPIRED = 20;

    /**
     * This fire flower is an item which can be consume by the player, hence it extends
     * consumableItems abstract class to promote abstraction.
     *
     * It also allow player to perform consumeFireFlower action on it when the player
     * encounter the fire flower in the map.
     *
     *
     *  A fire flower constructor
     */
    public FireFlower() {
        super("Fire Flower",'f',false);
        this.addAction(new ConsumeFireFlower(this));
        counter = 0;
    }

    /**
     * The fire flower will stay on the ground for 20 rounds before being remove from the map.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (counter != NUMBER_OF_TURNS_EXPIRED){
            counter += 1;

        }
        else{
            currentLocation.removeItem(this);
        }
    }

    /**
     * This method will be call in the consume fire flower action class.It will basically
     * add a status known as fire attack to the player to allow player to use fire attack on enemies.
     *
     * @param actor the actor that consumes the item
     */
    @Override
    public void consumeItem(Actor actor) {
        actor.addCapability(Status.FIRE_ATTACK);
    }

    /**
     * displays how many turns left before fire flower disappears
     * from the game
     *
     * @return a string that shows how many turns left before fire flower disappears
     * from the game
     */
    @Override
    public String toString() {
        return "Fire Flower - " + (TICK_COUNT-counter) + " turns remaining";
    }
}
