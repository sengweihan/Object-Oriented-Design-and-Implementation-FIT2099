package game.enemies;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;

/**
 * The king of all Koopas
 */
public class Bowser extends Enemies {
    /**
     * Constructor.
     */
    public Bowser () {
        super("Bowser", 'B', 500);
        this.behaviours.clear();
        this.addCapability(Status.FINAL_BOSS);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80,"punches");
    }

    @Override
    public void resetInstance(GameMap map) {
        this.heal(500);
        map.removeActor(this);
        map.at(41,8).addActor(this);
        this.previousActor = null;
        this.previousActorLocation = null;
        this.attacked = false;
        this.behaviours.clear();
    }
}
