package game.actions.coinaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.interfaces.PickableCoin;

public class PickUpCoinAction extends Action {
    private PickableCoin pickableCoin;

    public PickUpCoinAction(PickableCoin pickableCoin){
        this.pickableCoin = pickableCoin;
    }

    /**
     * This method will call the pickableCoin instance's pickableCoin method and execute them.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return pickableCoin.pickableCoin(actor,map);
    }


    /**
     * This method will be display in the console to allow player to select that particular action
     * to perform.
     *
     * @param actor The actor performing the action.
     * @return a string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + pickableCoin;
    }
}
