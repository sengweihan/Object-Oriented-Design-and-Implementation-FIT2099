package game.ground.fountains;

/**
 * A fountain that holds water that can heal whoever that consumes it
 */
public class HealthFountains extends Fountains {
    /**
     * Constructor.
     */
    public HealthFountains() {
        super('H',new HealthWater());
    }

    @Override
    public String toString() {
        return "Health Fountain (" + waterCount + "/10)";
    }
}