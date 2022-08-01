package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that does soft-reset on the instances.
 * TODO: you may modify (add or remove) methods in this class if you think they are not necessary.
 * HINT: refer to Bootcamp Week 5 about static factory method.
 * A3: Think about how will you improve this implementation in the future assessment.
 * What could be the drawbacks of this implementation?
 */
public class ResetManager {
    /**
     * A list of resettable instances (any classes that implements Resettable,
     * such as Player implements Resettable will be stored in here)
     */
    private List<Resettable> resettableList;
    private List<GameMap> allMap;

    /**
     * A singleton reset manager instance
     */
    private static ResetManager instance;

    private boolean hasReset;

    /**
     * Get the singleton instance of reset manager
     * @return ResetManager singleton instance
     */
    public static ResetManager getInstance(){
        if(instance == null){
            instance = new ResetManager();
        }
        return instance;
    }

    public void addMap(GameMap map){
        allMap.add(map);
    }

    /**
     * Constructor
     */
    private ResetManager(){
        resettableList = new ArrayList<>();
        allMap = new ArrayList<>();
        hasReset = false;
    }

    public boolean getHasReset(){
        return hasReset;
    }

    /**
     * Reset the game by traversing through all the list
     * By doing this way, it will avoid using `instanceof` all over the place.
     *
     */
    public void run(){
        for (Resettable reset: resettableList){
            for (GameMap map : allMap){
                reset.resetInstance(map);
            }
        }
        hasReset = true;
    }

    /**
     * Add the Resettable instance to the list, only if the game
     * has not been reset by the player
     */
    public void appendResetInstance(Resettable reset){
        if (!hasReset)
            resettableList.add(reset);
    }


    /**
     * Remove a Resettable instance from the list
     * @param resettable resettable object
     */
    public void cleanUp(Resettable resettable){
        resettableList.remove(resettable);
    }
}
