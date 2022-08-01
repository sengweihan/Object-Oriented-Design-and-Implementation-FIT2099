package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;

/**
 * A plant that is capable of eating the player alive
 */
public class PiranhaPlant extends Enemies {

    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        this.behaviours.clear();
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90,"Chomps");
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Exit exits : map.locationOf(this).getExits()){
            Location location = exits.getDestination();
            if (location.containsAnActor()){
                Actor otherActor = location.getActor();
                if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && successAttack()){
                    this.behaviours.put(0,new AttackBehaviour(otherActor));
                }
            }
        }

        for(Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    @Override
    public void resetInstance(GameMap map) {
        if (this.isConscious()){
            this.increaseMaxHp(50);
        }
    }

}
