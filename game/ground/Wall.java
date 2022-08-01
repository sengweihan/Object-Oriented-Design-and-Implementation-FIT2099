package game.ground;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.moveaction.FlyAction;
import game.actions.moveaction.JumpAction;
import game.actions.moveaction.PowerStarMoveAction;
import game.interfaces.Flyable;
import game.interfaces.Jumpable;

public class Wall extends Ground implements Jumpable, Flyable {

	/**
	 * A constructor.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Does not allow any actor to enter unless they uses a jump action.
	 *
	 * @param actor the Actor to check
	 * @return a boolean
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * The string that will be printed out when the instance of this class is being called.
	 *
	 * @return a string
	 */
	public String toString(){
		return "Wall";
	}


	/**
	 *
	 * @param actor
	 * @param map
	 * @param location
	 * @return String
	 * Since wall implements Jumpable interface, it will need to implement this jump methods.
	 * Have 80% success rate and 20 fall damage.If there player have super mushroom capability , they
	 * can jump freely.
	 */
	@Override
	public String jump(Actor actor, GameMap map, Location location) {
		if(Math.random()<= 0.8 || actor.hasCapability(Status.EFFECT_SUPER_MUSHROOM)){
			map.moveActor(actor,location);
			return actor + " successfully jumped on top of " + this + "(" + location.x() + ", " + location.y() + ")";
		}
		else{
			actor.hurt(20);
			return actor + " jumped unsuccessful and inflicted a damage of 20";
		}
	}

	/**
	 * This method will check whether the player already consume power star if it does
	 * then it will add the PowerStarMoveAction into the action list or else it will
	 * add the jump action to the action list.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return an actionlist
	 */
	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction) {
		ActionList actionList = new ActionList();
		if (actor.hasCapability(Status.IMMUNITY) && !location.containsAnActor()){
			actionList.add(new PowerStarMoveAction(location,direction));
		}
		else if (!location.containsAnActor()){
			actionList.add(new JumpAction(this,location,direction));
		}
		else if (actor.hasCapability(Status.FLYING) && !location.containsAnActor()){
			actionList.add(new FlyAction(this, location, direction));
		}

		return actionList;

	}

	@Override
	public String fly(Actor actor, GameMap map, Location location) {
		map.moveActor(actor,location);
		return actor + " flies to " + location;
	}
}
