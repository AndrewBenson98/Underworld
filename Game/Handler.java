package Game;
import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Holds linked list of objects
 * 
 * @author Andrew Benson
 *
 */
public class Handler {

	//List that holds all objects in the game
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();

	/**
	 * Goes through all objects in the list and calls their update method
	 */
	public void update() {
		for (int i = 0; i < objects.size(); i++) {
			// Only updates if not an enemy- Because enemies will have own
			// thread
			if (objects.get(i).getId() != ID.Enemy)
				objects.get(i).update();
		}

	}

	/**
	 * Renders all objects in the game
	 * @param g
	 */
	public void render(Graphics g) {
		
		//For each object, call its render method
		for (int i = 0; i < objects.size(); i++) {

			objects.get(i).render(g);
		}

	}

	/**
	 * Add an object into the game
	 * @param object a game object
	 */
	public void add(GameObject object ) {
		this.objects.add(object);
	}

	/**
	 * Remove an object from the game
	 * @param object a game object
	 */
	public void remove(GameObject object) {
		this.objects.remove(object);
	}
	
	/**
	 * Removes all objects from the list
	 */
	public void removeAll()
	{
		for(int i =0;i<objects.size();)
		{
			this.objects.remove(objects.get(i));
		}
		
		
	}
}
