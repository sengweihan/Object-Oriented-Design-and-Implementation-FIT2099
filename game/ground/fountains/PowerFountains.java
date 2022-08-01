package game.ground.fountains;

/**
 * A fountain that holds water that can increases consumer's damage
 */
public class PowerFountains extends Fountains {
    /**
     * Constructor.
     */
    public PowerFountains() {
        super('A', new PowerWater());
    }

    @Override
    public String toString() {
        return "Power Fountain (" + waterCount + "/10)";
    }
}
