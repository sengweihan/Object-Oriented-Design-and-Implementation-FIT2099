package game.enemies;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.enemiesaction.RemoveGoombaAction;
import game.Status;
import game.reset.Resettable;

import java.util.*;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemies implements Resettable {
	protected final int REMOVED_FROM_MAP = 10;
	protected Random rand = new Random();

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
	}

	/**
	 * a method to check if Goomba hits the 10% chance of being removed
	 * from the game
	 *
	 * @return true if hits 10% chance of being removed from the map, else false
	 */
	public boolean remove(){
		if ((rand.nextInt(100) <= REMOVED_FROM_MAP)){
			return true;
		}
		return false;
	}

	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "kicks");
	}

	/**
	 * Figure out what to do next. if current Goomba is not a null object, and
	 * remove() method returns true, returns a RemoveGoombaAction()
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (this !=null && remove()){
			return new RemoveGoombaAction();
		}
		else{
			return super.playTurn(actions,lastAction,map,display);
		}
	}

	/**
	 * At the moment, we only make it can be attacked by Player.
	 * You can do something else with this method.
	 * @param otherActor the Actor that might perform an action.
	 * @param direction  String representing the direction of the other Actor
	 * @param map        current GameMap
	 * @return list of actions
	 * @see Status#HOSTILE_TO_ENEMY
	 */
	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		return super.allowableActions(otherActor,direction,map);
	}
}
