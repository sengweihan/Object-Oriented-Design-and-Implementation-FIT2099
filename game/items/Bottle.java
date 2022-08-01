package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.ground.fountains.FountainWater;

import java.util.ArrayList;

/**
 * An item that allows the player to fill water with and
 * carry around during his adventure
 */
public class Bottle extends Item {
    private static ArrayList<FountainWater> waterType;
    private static Actor player;
    /***
     * Constructor.
     */
    public Bottle() {
        super("Bottle", 'b', false);
        waterType = new ArrayList<>();
    }

    /**
     * adds a fountain water to the bottle
     * @param fountainWater the water in the fountain
     */
    public static void addWater(FountainWater fountainWater){
        waterType.add(fountainWater);
    }

    /**
     * sets the designated bottle with one player
     * @param player the player that binds with the bottle
     */
    public static void setPlayer(Actor player){
        Bottle.player=player;
    }

    /**
     * a method that allows the player to drink water by first:
     * getting the last water added into the bottle out
     * reduce the arraylist size by 1 (removing the water fromm the bottle)
     * grants effect to the player by calling consumeWater method
     */
    public static void drinkWater(){
        FountainWater water = waterType.get(waterType.size()-1);
        waterType.remove(waterType.size()-1);
        water.consumeWater(player);
    }

    /**
     * a getter to return the size of the arraylist
     * @return size of the arraylist, 0 if arraylist is empty
     */
    public static int getWaterTypeLength(){
        if (waterType != null){
            return waterType.size();
        }
        return 0;
    }

    /**
     * gets the last water added into the bottle
     * @return last water added into the bottle
     */
    public static FountainWater getWater(){
        return waterType.get(waterType.size()-1);
    }

    /**
     * gets the bottle as a whole
     * @return arraylist of bottle.
     */
    public static ArrayList<FountainWater> getWaterType(){
        return waterType;
    }
}
