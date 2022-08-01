package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;

/**
 * An item that allows the wielder to save the princess and end the game!
 */
public class Key extends Item {
    /***
     * Constructor.
     */
    public Key() {
        super("Key", 'k', true);
        this.addCapability(Status.WIN_GAME);
    }
}
