package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.enemiesaction.DestroyShellAction;
import game.actions.enemiesaction.DormantAction;
/**
 * An abstract class for future specialised type of koopas to be born
 */
public abstract class Koopa extends Enemies {


    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.KOOP);
    }

    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }

    /**
     * if current Koopa has Dormant status and player has a wrench, it will return
     * a DestroyShellAction for the player to attack the Koopa.
     *
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if (this.hasCapability(Status.DORMANT) && (otherActor.getInventory().contains(otherActor.getWeapon()))){
            ActionList actions = new ActionList();
//            actions.clear();
            actions.add(new DestroyShellAction(this,direction));
            this.behaviours.clear();
            return actions;
        }
        else{
            return super.allowableActions(otherActor,direction,map);
        }
    }

    /**
     * Figure out what to do next. if current Koopa object has Dormant status,
     * returns a Dormant Action.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (this.hasCapability(Status.DORMANT)) {
            this.setDisplayChar('D');
            this.behaviours.clear();
            return new DormantAction();
        }
        else{
            return super.playTurn(actions,lastAction,map,display);
        }
    }
}
