package game.actions.bottleaction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ground.fountains.FountainWater;
import game.items.Bottle;

/**
 * An action class that allows the user to drink the water stored in the bottle.
 */

public class DrinkBottleAction extends Action {
    /**
     * execute method that gets the fountain water from the bottle,
     * and runs the static method in the bottle to apply certain effects
     * to the player.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string that displays user has drank the water in the bottle.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        FountainWater water = Bottle.getWater();
        Bottle.drinkWater();
        return actor + " drinks " + water;
    }

    /**
     * display the menu description of what the actor can do, in this case,
     * drink the water stored in the bottle.
     *
     * the string here is concatenated with the water stored in the bottle.
     * thus displaying all the water in the bottle.
     *
     * @param actor The actor performing the action.
     * @return a string that says actor can drink the water stored in the bottle
     */
    @Override
    public String menuDescription(Actor actor) {
        String output = "";
        for (int i = 0; i < Bottle.getWaterTypeLength(); i++){
            output += Bottle.getWaterType().get(i) + ", ";
        }

        return actor + " consumes Bottle[" + output.substring(0,output.length()-2) + "]";
    }
}
