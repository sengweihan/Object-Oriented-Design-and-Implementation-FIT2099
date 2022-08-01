package game;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.actors.PrincessPeach;
import game.actors.Toad;
import game.enemies.Bowser;
import game.actors.Player;
import game.ground.*;
import game.ground.fountains.HealthFountains;
import game.ground.fountains.PowerFountains;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.reset.ResetManager;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		Random rand  = new Random();

		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Sprout(), new Sapling(), new Mature(),
				new PowerFountains(), new HealthFountains());

		List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				"....................................AH.+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);

		FancyGroundFactory lavaFactory = new FancyGroundFactory(new Dirt(),new Sprout(),new Wall(),new Lava(),new Floor());

		List<String> lavaMap = Arrays.asList(
				"..................................L.......##..........+.....",
				"............+............+..................#...............",
				"......................................L.....#............L..",
				"...............L.............................##.............",
				"..........L..............L.....................#............",
				"................................................#...........",
				".................+........L.........L.............#.........",
				".........L.......................................##.........",
				".................+...............L..............##..........",
				".........+..............L...............+#____####..........",
				".......................................+#_____###++........L",
				".................L.............L.......+#______###..........",
				"..........L.............................+#_____###..........",
				"............+...........+........................##.........",
				"....................................+..............#........",
				"........L...........................................#.......",
				"...................+..........L...........L..........#......",
				"......................................................#.....",
				"......L...................L............................##...");

		GameMap secondGameMap = new GameMap(lavaFactory,lavaMap);

		world.addGameMap(secondGameMap);



		Actor mario = new Player("Mario", 'm', 100);
		world.addPlayer(mario, gameMap.at(43, 11));


		Actor toad = new Toad("MR.TOAD",'O',999);
		gameMap.addActor(toad,gameMap.at(44,11));


		gameMap.locationOf(mario).addItem(new PowerStar());
		gameMap.locationOf(mario).addItem(new SuperMushroom());

		secondGameMap.at(0,0).setGround(new WarpPipe(0,0,gameMap));
		gameMap.at(0,0).setGround(new WarpPipe(0,0,secondGameMap));

		NumberRange x = secondGameMap.getXRange();
		NumberRange y = secondGameMap.getYRange();

		int randomX;
		int randomY;


		for (int i =0 ; i<5; i++){ // 5 portals at both maps
			randomX = rand.nextInt(x.max());
			randomY = rand.nextInt(y.max());
			Location firstLocation = gameMap.at(randomX,randomY);
			Location secondLocation = secondGameMap.at(randomX,randomY);
			if (firstLocation.getGround().hasCapability(Status.FERTILE) && secondLocation.getGround().hasCapability(Status.FERTILE)){
				firstLocation.setGround(new WarpPipe(randomX,randomY,secondGameMap));
				secondLocation.setGround(new WarpPipe(randomX,randomY,gameMap));

			}
		}

		/**
		 * creating a singleton ResetManager instance here, and add
		 * all the gamemaps inside
		 */
		ResetManager resetManager = ResetManager.getInstance();
		resetManager.addMap(gameMap);
		resetManager.addMap(secondGameMap);

		PrincessPeach princessPeach = new PrincessPeach();
		secondGameMap.addActor(princessPeach,secondGameMap.at(40,8));
		secondGameMap.addActor(new Bowser(),secondGameMap.at(41,8));

		world.run();

	}
}
