package game.actions.attackaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enemies.Enemies;
import game.interfaces.Burning;

import java.util.Random;

public class FireAttackAction extends AttackAction {

    protected Burning item;


    /**
     * A fire attack action constructor.
     * @param target
     * @param direction
     * @param item
     */
    public FireAttackAction(Enemies target, String direction,Burning item) {
        super(target, direction);
        this.item = item;

    }


    /**
     * Will call the burn method that is implemented by those class which implements the burning interface method.
     * Then it will call its parent's execute method which in this case is the AttackAction's execute method.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        item.burn(target,map);
        return super.execute(actor, map);

    }

    /**
     * A string that will be printed out in the console to allow player to choose which action to perform
     * on that current round.
     *
     * @param actor The actor performing the action.
     * @return a string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction + " with fire!";
    }
}
