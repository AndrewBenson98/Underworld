package Game;
/**
 * The Camera object that allow the screen to follow the player
 * @author Andrew Benson
 *
 */
public class Camera {

	private int x,y;
	
	/**
	 * Constructor
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Camera (int x, int y)
	{
		this.x=x;
		this.y=y;	
	}
	
	/**
	 * Updates the x and y 
	 * @param player the player object
	 */
	public void update(GameObject player)
	{
		x=-player.getX()+((Game.WIDTH*Game.scale)/2);
		y=-player.getY()+((Game.HEIGHT*Game.scale)/2);
		
	}

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
}
