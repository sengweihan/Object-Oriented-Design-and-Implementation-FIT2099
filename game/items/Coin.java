package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.actions.coinaction.PickUpCoinAction;
import game.interfaces.PickableCoin;
import game.reset.Resettable;
import game.systems.WalletSystem;

public class Coin extends Item implements Resettable, PickableCoin {
    private int coinValue;

    public Coin(int coinValue,String name) {
        super(name, '$', true);
        this.coinValue = coinValue;
        this.addAction(new PickUpCoinAction(this));
        this.registerInstance();
    }



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

    @Override
    public String pickableCoin(Actor actor, GameMap map) {
        WalletSystem.addWalletValue(coinValue);
        map.locationOf(actor).removeItem(this);
        return actor + " picked up $" + coinValue + " coin";
    }

    /**
     * This method does not allow player to drop this item.
     *
     * @param actor
     * @return a DropItemAction
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * This method does not allow player to pick up this item.
     *
     * @param actor
     * @return a PickUpItemAction
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return null;
    }
}
