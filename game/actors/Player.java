package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.ResetAction;
import game.actions.bottleaction.DrinkBottleAction;
import game.actions.bottleaction.DrinkPowerCount;
import game.items.Bottle;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.systems.WalletSystem;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable, DrinkPowerCount {

	private final Menu menu = new Menu();
	protected int tick = 0;
	protected  int secondTick = 0;
	protected final int TICK_COUNT = 10;
	protected  final int FIRE_FLOWER_TICK = 20;
	protected final static int INITIAL_WALLET_VALUE = 0;
	public int walletValue;
	private int drinkCount;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		WalletSystem.setPlayer(this); // used to tell that this wallet only belongs to this player.
		walletValue = INITIAL_WALLET_VALUE;
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.addCapability(Status.BUYING);
		this.registerInstance();
		drinkCount = 0;
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// check the buff after drinking power water
		if (this.hasCapability(Status.DRANK_POWER)){
			drinkPowerWaterCount();
			this.removeCapability(Status.DRANK_POWER);
		}

		// check if player bottle has water, if yes give DrinkAction
		if (this.hasCapability(Status.HAS_BOTTLE)){
			if (Bottle.getWaterTypeLength() > 0){
				actions.add(new DrinkBottleAction());
			}
		}

		// power star remaining turns
		if (tick<TICK_COUNT && this.hasCapability(Status.IMMUNITY)){
			tick++;
			display.println("MARIO IS INVINCIBLE!");
		}
		else if (this.hasCapability(Status.IMMUNITY)){
			tick = 0;
			this.removeCapability(Status.IMMUNITY);
			display.println("IMMUNITY effect has worn off!");
		}


		if (secondTick < FIRE_FLOWER_TICK && this.hasCapability(Status.FIRE_ATTACK)){
			secondTick ++;
			display.println("MARIO IS RAGING");
		}
		else if (this.hasCapability(Status.FIRE_ATTACK)){
			secondTick = 0;
			this.removeCapability(Status.FIRE_ATTACK);
			display.println("FIRE ATTACK EFFECT HAS WORN OFF!");
		}

		// once-off reset action
		ResetManager resetManager = ResetManager.getInstance();
		if (!resetManager.getHasReset()){
			actions.add(new ResetAction());

		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null){
			return lastAction.getNextAction();
		}
		Location actorCurrentLocation = map.locationOf(this);
		// return/print the console menu
		display.println(this + this.printHp() + " at (" + actorCurrentLocation.x() + ", " + actorCurrentLocation.y() + ")");
		display.println("wallet: $" + WalletSystem.getWalletValue());
		return menu.showMenu(this, actions, display);
	}

	/**
	 * This method will check whether the player has the status of EFFECT_SUPERMUSHROOM. If it
	 * does then the player's character will be converted into uppercase else it will remain as
	 * lowercase.
	 *
	 * @return a char
	 */
	@Override
	public char getDisplayChar(){
		char out = this.hasCapability(Status.EFFECT_SUPER_MUSHROOM) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
		return out;
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(5 + drinkCount*15,"punches");
	}

	@Override
	public void resetInstance(GameMap map) {
		super.resetMaxHp(100);
		this.removeCapability(Status.IMMUNITY);
		this.removeCapability(Status.EFFECT_SUPER_MUSHROOM);
	}

	@Override
	public void drinkPowerWaterCount() {
		drinkCount++;
		getIntrinsicWeapon();
	}
}

