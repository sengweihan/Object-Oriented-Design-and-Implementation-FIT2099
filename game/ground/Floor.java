package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.moveaction.FlyAction;
import game.interfaces.Flyable;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground implements Flyable {
	public Floor() {
		super('_');
	}

	/**
	 * This method will only allow player to enter and not other actor such as koopa,goomba or toad.
	 *
	 * @param actor the Actor to check
	 * @return a boolean value
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
			return true;
		}
		else if (actor.hasCapability(Status.FLYING)){
			return true;
		}
		return false;
	}

	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction) {
		ActionList actions = new ActionList();
		if (actor.hasCapability(Status.FLYING) && !location.containsAnActor()){
			actions.add(new FlyAction(this, location, direction));
		}
		return actions;
	}

	@Override
	public String fly(Actor actor, GameMap map, Location location) {
		map.moveActor(actor,location);
		return actor + " flies to " + location;
	}
}
