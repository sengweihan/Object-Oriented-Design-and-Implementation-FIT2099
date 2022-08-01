package game.actions.coinaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.systems.WalletSystem;

public class BuyAction extends Action {

    private Item item;
    private int cost;
    private String name;


    public BuyAction(Item item, int cost,String name){
        this.item = item;
        this.cost = cost;
        this.name = name;
    }

    /**
     * This method will be executed when the player press on the key
     * button.In this method, it will basically checks whether the player wallet value > cost
     * if it does then the item will be added to player's inventory or else an unsuccessful
     * buying attempt string will be printed out.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string
     *
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String message = "";
        if (actor.hasCapability(Status.BUYING) && WalletSystem.getWalletValue() > cost){
            actor.addItemToInventory(item);
            WalletSystem.subtractWalletValue(cost);
            message += actor + " successfully bought " + name;
            return message;

        }
        else{
            message += actor + " do not have enough coins to buy " + name + "!";
            return message;
        }
    }

    /**
     * This method will be display in the console to allow player to select that particular action
     * to perform.
     *
     * @param actor The actor performing the action.
     * @return a string
     */
    public String menuDescription(Actor actor) {
        return actor + " buys "  + name+ " ($" + this.cost + ")";
    }



}
