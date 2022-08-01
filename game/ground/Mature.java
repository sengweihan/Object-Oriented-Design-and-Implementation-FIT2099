package game.ground;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.enemies.FlyingKoopa;
import game.enemies.NormalKoopa;
import game.interfaces.Jumpable;

import java.util.List;
import java.util.Random;

public class Mature extends Tree implements Jumpable {
    private int counter;
    private Random rand = new Random();
    private static final double CHANCE_SPAWN_KOOPA = 0.15;


    /**
     * Constructor.
     *
     * Everytime a new instance of Mature is created, the couter will be initialized to 0
     *
     */
    public Mature() {
        super('T');
        counter = 0;
    }



    /**
     *
     * @param location The location of the Ground
     * Allow sapling class to keep track of the passage of time
     * with the help of counter attribute.
     *
     * For every 5 turns , it will grow a new Sprout in one of the surroundings
     * and at the same time, it has 15% chance to spawn Koopa and 20% chance to wither and die
     * and eventually turns the ground into dirt.
     */
    @Override
    public void tick(Location location) {
        counter += 1;
        List<Exit> e =  location.getExits();
        int randomExit = rand.nextInt(e.size());
        Exit exit = e.get(randomExit);

        if (counter % 5 == 0 && exit.getDestination().getGround().hasCapability(Status.FERTILE)){
            location.setGround(new Sprout());
        }

        if (Math.random() <= CHANCE_SPAWN_KOOPA && !location.containsAnActor()){
            double spawn_random = 0.5;
            if (Math.random() <= spawn_random){
                location.addActor(new NormalKoopa());
            }
            else {
                location.addActor(new FlyingKoopa());
            }
        }

        if (Math.random() <= 0.2){
            location.setGround(new Dirt());
        }

    }

    /**
     * A method used to print the string when the instance of this class is being called.
     *
     * @return a string
     */
    public String toString(){
        return "Mature";
    }


    /**
     *
     * @param actor
     * @param map
     * @param location
     * @return String
     * Since mature implements Jumpable interface, it will need to implement this jump methods.
     * Have 70% success rate and 30 fall damage.If there player have super mushroom capability , they
     * can jump freely.
     */
    @Override
    public String jump(Actor actor, GameMap map, Location location) {
        if(Math.random()<= 0.7 || actor.hasCapability(Status.EFFECT_SUPER_MUSHROOM)){
            map.moveActor(actor,location);
            return actor + " successfully jumped on top of " + this + "(" + location.x() + ", " + location.y() + ")";
        }
        else{
            actor.hurt(30);
            /**
             * Need to check whether the player is still alive after unsuccessful jump or else
             * they will be cases where the player will have negative hitpoints and still alive in game.
             */
            if (!actor.isConscious()){
                map.removeActor(actor);
            }
            return actor + " jumped unsuccessful and inflicted a damage of 30";
        }
    }
}
