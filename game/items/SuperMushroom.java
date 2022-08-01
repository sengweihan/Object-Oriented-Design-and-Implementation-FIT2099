package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.consumeaction.ConsumeSuperMushroomAction;

public class SuperMushroom extends ConsumableItems {
    /***
     * Constructor.
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        this.addAction(new ConsumeSuperMushroomAction(this));
    }

    /**
     * upon being consumed, increases actor max hp by 50
     * applies EFFECT_SUPER_MUSHROOM status to the actor
     *
     * @param actor the actor that consumes the item
     */
    @Override
    public void consumeItem(Actor actor) {
        actor.addCapability(Status.EFFECT_SUPER_MUSHROOM);
        actor.increaseMaxHp(50);
    }
}
