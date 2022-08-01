package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.attackaction.NPCAttackAction;

/**
 * a class that allows the Enemies to attack the player automatically
 */
public class AttackBehaviour implements Behaviour {

    private final Actor target;

    /**
     * constructor.
     *
     * @param subject the actor being attacked
     */
    public AttackBehaviour (Actor subject){
        this.target = subject;
    }

    /**
     * checks the actor's exits to see if there is a target, if yes,
     * attacks the target at that direction
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a NPCAttackAction for the actor to attack the target
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (!map.contains(target) || !map.contains(actor))
            return null;

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                String direction = exit.getName();
                return new NPCAttackAction(destination.getActor(),direction);
            }
        }
        return null;
    }
}
