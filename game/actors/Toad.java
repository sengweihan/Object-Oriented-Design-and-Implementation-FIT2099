package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.bottleaction.GetBottleAction;
import game.actions.coinaction.BuyAction;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapon.Wrench;
import java.util.Map;
import java.util.TreeMap;

public class Toad extends Actor {
    /**
     * Hashmap is used to store respective items and its price for selling purposes.
     */
    private Map<Integer,Item> sellerItem = new TreeMap<>();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Toad(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        sellerItem.put(200, new Wrench());
        sellerItem.put(400, new SuperMushroom());
        sellerItem.put(600, new SuperMushroom());
    }

    /**
     * Toad is basically an actor which serves a purpose of selling items.Therefore it will not
     * do anything throughout the whole game.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }


    /**
     * This method will inform the player which actions they are allow to perform on when
     * the player approaches the toad.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an actionlist
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionsList = new ActionList();
        for (int item : sellerItem.keySet()){
            if (item == 200){
                actionsList.add(new BuyAction(new Wrench(),item,"Wrench"));
            }
            else if (item == 400){
                actionsList.add(new BuyAction(new SuperMushroom(),item,"Super Mushroom"));
            }
            else if (item == 600){
                actionsList.add(new BuyAction(new PowerStar(),item,"Power Star"));
            }
        }
        if (!otherActor.hasCapability(Status.HAS_BOTTLE)){
            actionsList.add(new GetBottleAction());
        }
        return actionsList;
        }
}
