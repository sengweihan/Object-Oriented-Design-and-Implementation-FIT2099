package game.actions.attackaction;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.enemies.Enemies;
import game.items.Key;
import game.items.SuperMushroom;

/**
 * Special Action for Player to attack other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Enemies target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * an execute method to run and attack the target.
	 * contains multiple if-statements to check if actor (player) has certain
	 * capabilites so that the game can proceed correctly
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a string that either actor misses the target or actor attacked/killed the target
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		if (actor.hasCapability(Status.IMMUNITY)){
			damage = 500;
		}
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);

		if (!target.isConscious()) {
			if (actor.hasCapability(Status.IMMUNITY)){
				if (target.hasCapability(Status.KOOP)) {
					Location dropMushroom = map.locationOf(target);
					dropMushroom.addItem(new SuperMushroom());
				}
			}
			else {
				if (target.hasCapability(Status.KOOP)) {
					if (!target.hasCapability(Status.DORMANT)){
						target.addCapability(Status.DORMANT);
					}
				}
			}
			if (target.hasCapability(Status.FINAL_BOSS)){
				Location dropKey = map.locationOf(target);
				dropKey.addItem(new Key());
			}
			if (!target.hasCapability(Status.DORMANT)){
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";
			}
		}

		return result;
	}

	/**
	 * display the menu description of what the actor can do, in this case,
	 * attack other actor
	 *
	 * @param actor The actor performing the action.
	 * @return a string that displays "actor attacks who at which direction"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}
