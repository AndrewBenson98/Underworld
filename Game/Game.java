package Game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import ItemsAndObjects.EndDoor;
import ItemsAndObjects.Enemy;
import ItemsAndObjects.Inventory;
import ItemsAndObjects.Orb;
import ItemsAndObjects.Player;
import ItemsAndObjects.RewireTool;
import ItemsAndObjects.Trap;
import ItemsAndObjects.Wall;
import ItemsAndObjects.bronzeDoor;
import ItemsAndObjects.bronzeKey;
import ItemsAndObjects.goldDoor;
import ItemsAndObjects.goldKey;
import ItemsAndObjects.silverDoor;
import ItemsAndObjects.silverKey;
import Screens.LoadScreen;
import Screens.MainMenu;
import Screens.ScoreScreen;


/**
 * The main game method that initializes everything
 * 
 * @author Andrew Benson
 *
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 800, HEIGHT = 600;
	private String title = "GAME";
	public static int scale = 2;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Camera cam;
	private Inventory inventory;
	private Image ground;

	// Game states
	public static STATE state = STATE.MAIN;
	private MainMenu main;
	private HelpMenu help;
	private LoadScreen load;
	private ScoreScreen score;

	// Timer
	public static Timer timer;

	/**
	 * Creates a new game
	 */
	public Game() {
		// Initialize the game states, handler, camera, and timer
		main = new MainMenu();
		help = new HelpMenu();
		load = new LoadScreen();
		score = new ScoreScreen();
		cam = new Camera(0, 0);
		timer = new Timer(cam, this);
		Thread time = new Thread(timer);
		time.start();

		handler = new Handler();
		this.addKeyListener(new KeyInput(handler, this));

		inventory = new Inventory(cam, this);

		// Create a new window
		new Window(WIDTH, HEIGHT, title, scale, this);

		// Set an image for the ground
		try {
			ground = ImageIO.read(new File("res/Ground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot Find Image");
		}

		// loadLevel();

		// this.requestFocus();

	}

	/**
	 * Enums the differentiate which state the game is in
	 * 
	 * @author 071566301
	 *
	 */
	public enum STATE {
		MAIN(), HELP(), GAME(), LOAD(), SCORE();
	}

	/**
	 * Starts the main thread
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	/**
	 * Main Game Loop
	 */
	public void run() {
		// GameLoop

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		// long timer = System.currentTimeMillis();
		// int frames = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			// While difference in time is greater than 1
			while (delta >= 1) {
				update();
				delta--;
			}
			// Renders if still running
			if (running) {
				render();

			}
		}
		stop();// Stops the thread
	}

	/**
	 * Updates all the objects in the game
	 */
	public void update() {

		// If the current state is the game state
		if (state == STATE.GAME) {
			handler.update();

			for (int i = 0; i < handler.objects.size(); i++) {
				GameObject tempObject = handler.objects.get(i);
				if (tempObject.getId() == ID.Player) {

					cam.update(tempObject);
				}
			}
			inventory.update();
		}

	}

	/**
	 * Renders everything onto the screen
	 */
	public void render() {

		
		
		// Create new Buffer Strategy to get graphics
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		// Create new Graphics
		Graphics g = bs.getDrawGraphics();
		
		
		
		
		Graphics2D g2d = (Graphics2D) g;
		// g.clearRect(cam.getX(), cam.getY(), WIDTH*scale, HEIGHT*scale);
		// ///////////////////////////////////////////////////
		// Draw
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH * scale, HEIGHT * scale);

		// //////////////////////////////////
		// Translate everything so the camera stays on the player

		if (state == STATE.GAME) {

			g2d.translate(cam.getX(), cam.getY());

			// Draw the ground image until it fills the map
			for (int i = 0; i < (WIDTH * 2) * 4; i += WIDTH * 2) {
				for (int j = 0; j < (HEIGHT * 2) * 3; j += HEIGHT * 2) {
					g.drawImage(ground, i, j, WIDTH * 2, HEIGHT * 2, null);
				}
			}

			// Renders all the objects
			handler.render(g);
			// Renders the inventory
			inventory.render();
			// Renders the timer
			timer.render();
			g2d.translate(cam.getX(), cam.getY());

		}
		// If not in game then render the appropriate menu
		else if (state == STATE.MAIN) {
			main.Render(g);
		} else if (state == STATE.HELP) {
			help.Render(g);
		} else if (state == STATE.LOAD) {
			load.Render(g);
		} else if (state == STATE.SCORE) {
			score.Render(g);
		}

		g.dispose();
		bs.show();

	}

	/**
	 * Stops the thread
	 */
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Loads the level give a text file
	 */
	public void loadLevel() {
		File file = new File("Level1.txt");
		try {
			Scanner reader = new Scanner(file);

			int y = 0;
			int x = 0;

			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				for (int i = 0; i < line.length(); i++) {

					// Player
					if (line.charAt(i) == 'P') {
						handler.add(new Player(x, y, ID.Player, handler,
								inventory));
					}
					// Wall
					else if (line.charAt(i) == '1') {
						handler.add(new Wall(x, y, ID.Wall));
					}
					// Enemy
					else if (line.charAt(i) == 'E') {
						Enemy e = new Enemy(x, y, ID.Enemy, handler);
						handler.add(e);
						Thread thread = new Thread(e);
						thread.start();
					}
					// Bronze door
					else if (line.charAt(i) == 'A') {
						handler.add(new bronzeDoor(x, y, ID.bronzeDoor));
					}
					// Silver door
					else if (line.charAt(i) == 'S') {
						handler.add(new silverDoor(x, y, ID.silverDoor));
					}
					// Gold door
					else if (line.charAt(i) == 'D') {
						handler.add(new goldDoor(x, y, ID.goldDoor));
					}
					// Bronze key
					else if (line.charAt(i) == 'Z') {
						handler.add(new bronzeKey(x, y, ID.bronzeKey));
					}
					// Silver Key
					else if (line.charAt(i) == 'X') {
						handler.add(new silverKey(x, y, ID.silverKey));
					}
					// Gold Key
					else if (line.charAt(i) == 'C') {
						handler.add(new goldKey(x, y, ID.goldKey));
					}
					// Trap
					else if (line.charAt(i) == 'T') {
						handler.add(new Trap(x, y, ID.Trap));
					}
					// Rewire tool
					else if (line.charAt(i) == 'R') {
						handler.add(new RewireTool(x, y, ID.RewireTool));
					}
					// Orb
					else if (line.charAt(i) == 'B') {
						handler.add(new Orb(x, y, ID.Orb));
					}
					// End door
					else if (line.charAt(i) == 'M') {
						handler.add(new EndDoor(x, y, ID.EndDoor));
					}
					// Add 64 to the x value
					x += 64;
				}
				// Reset x value and add 64 to y value
				x = 0;
				y += 64;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Game();
	}

}
