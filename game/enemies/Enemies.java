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
import game.actions.attackaction.FireAttackAction;
import game.actions.attackaction.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.Fire;
import game.reset.Resettable;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * an abstract class to classify subclasses extending this class
 * to be the enemy in the game
 */
public abstract class Enemies extends Actor implements Resettable {
    /**
     * TreeMap to store the behaviours
     */
    protected final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour
    /**
     * a variable to check if the current object of enemy has attacked a
     * target already or not
     */
    protected boolean attacked = false;
    /**
     * Random Number Generator
     */
    protected Random rand = new Random();
    /**
     * target's previous location
     */
    protected Location previousActorLocation;
    /**
     * variable to store the target object
     */
    protected Actor previousActor;
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemies(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(10, new WanderBehaviour());
        this.registerInstance();
    }

    @Override
    protected abstract IntrinsicWeapon getIntrinsicWeapon();

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && this.isConscious() && !otherActor.hasCapability(Status.FIRE_ATTACK)) {
            actions.add(new AttackAction(this,direction));
        }

        else if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && this.isConscious() && otherActor.hasCapability(Status.FIRE_ATTACK)){
            actions.add(new FireAttackAction(this,direction,new Fire()));
        }
        return actions;
    }

    /**
     * checks every turn if the enemy's exits contains the player, if yes, puts
     * a AttackBehaviour inside the TreeMap, and attack, else if the current enemy
     * attacked the player already, and the player isn't in the enemy exit's, put a
     * FollowBehaviour to follow the player around the map.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return an action from the TreeMap
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Actor otherActor;
        for (Exit exit : map.locationOf(this).getExits()) {
            Location destination = exit.getDestination();
            if (attacked && !destination.containsAnActor()){
                otherActor = previousActor;
                this.behaviours.put(5,new FollowBehaviour(otherActor));
            }
            else if (attacked && destination.containsAnActor()){
                otherActor = map.getActorAt(destination);
                if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    if (this.behaviours.get(5) != null){
                        this.behaviours.remove(5);
                    }
                    if (successAttack()){
                        this.behaviours.put(0, new AttackBehaviour(otherActor));
                    }
                }
            }
            else if (!attacked && destination.containsAnActor()) {
                otherActor = map.getActorAt(destination);
                if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
                    if (successAttack()){
                        this.behaviours.put(0, new AttackBehaviour(otherActor));
                        this.behaviours.remove(10);
                        attacked = true;
                        previousActorLocation = destination;
                        previousActor = destination.getActor();
                        break;
                    }
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

    /**
     * a method to check if the enemy successfully attacks the target
     * @return true if it successfully lands an attack, else false
     */
    public boolean successAttack(){
        if (this.getWeapon().chanceToHit() > rand.nextInt(100)){
            return true;
        }
        return false;
    }

    @Override
    public void resetInstance(GameMap map) {
        map.removeActor(this);
    }
}
