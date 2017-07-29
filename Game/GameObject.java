package Game;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Abstract class that holds all object information e.g. X and Y coordinates
 * 
 * @author 071566301
 *
 */
public abstract class GameObject {

	//X and Y Coordinates
	protected int x, y;
	
	//Object speeds/ Velocities
	protected int velX, velY;
	
	//Differentiate objects from one another
	protected ID id;
	
	//Used for animation
	protected int image;

	/**
	 * Constructor for new objects
	 * @param x
	 * @param y
	 * @param id
	 */
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
		
	}

	public abstract void update();

	public abstract void render(Graphics g);
	
	/**
	 * Collision box for all objects
	 * @return a rectangle that represents a collision box
	 */
	public abstract Rectangle getBounds();

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}
	
	
	

}
