package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.Status;
import game.actions.EndGameAction;

/**
 * A princess that is abducted by Bowser
 */
public class PrincessPeach extends Actor {
    /**
     * Constructor.
     */
    public PrincessPeach() {
        super("Princess Peach", 'P', 999);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * checks if other actor has the key in its inventory, if yes, allows the other actor
     * to perform EndGameAction.
     *
     * @param otherActor the Actor that might be performing an action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions of what other actor can do
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && otherActor.hasCapability(Status.WIN_GAME)){
            actions.add(new EndGameAction());
        }
        return actions;
    }
}
