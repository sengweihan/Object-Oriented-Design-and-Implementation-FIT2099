package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.interfaces.Jumpable;
import game.items.Coin;
import game.items.FireFlower;

public class Sapling extends Tree implements Jumpable {
    private int counter ;
    private static  final double CHANCE_SPAWN_COIN = 0.1;
    private static  final double CHANCE_SPAWN_FIRE_FLOWER = 0.5;

    /**
     * Constructor.
     *
     *
     */
    public Sapling() {
        super('t');
        counter = 0;
    }


    /**
     *
     * @param location The location of the Ground
     * Allow sapling class to keep track of the passage of time
     * with the help of counter attribute.
     *
     * For every 10 turns , it will grow into a tall tree/Mature (t)
     * and at the same time, it has 10 percent chance to spawn Coins($20)
     * on its position in every turn .
     */
    @Override
    public void tick(Location location) {
        counter += 1;
        if (counter % 10 == 0){
            location.setGround(new Mature());
        }

        if (Math.random() <= CHANCE_SPAWN_COIN){
            location.addItem(new Coin(20,"Coin($20)"));
        }

        if (Math.random() <= CHANCE_SPAWN_FIRE_FLOWER){
            location.addItem(new FireFlower());
        }


    }

    /**
     * A method used to print the string when the instance of this class is being called.
     *
     * @return a string
     */
    public String toString(){
        return "Sapling";
    }


    /**
     *
     * @param actor
     * @param map
     * @param location
     * @return String
     * Since sapling implements Jumpable interface, it will need to implement this jump methods.
     * Have 80% success rate and 20 fall damage.If there player have super mushroom capability , they
     * can jump freely.
     */
    @Override
    public String jump(Actor actor, GameMap map, Location location) {
        if(Math.random()<= 0.8 || actor.hasCapability(Status.EFFECT_SUPER_MUSHROOM)){
            map.moveActor(actor,location);
            return actor + " successfully jumped on top of " + this + "(" + location.x() + ", " + location.y() + ")";
        }
        else{
            actor.hurt(20);
            /**
             * Need to check whether the player is still alive after unsuccessful jump or else
             * they will be cases where the player will have negative hitpoints and still alive in game.
             */
            if (!actor.isConscious()){
                map.removeActor(actor);
            }
            return actor + " jumped unsuccessful and inflicted a damage of 20";
        }

    }
}
