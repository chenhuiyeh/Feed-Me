package feedme.actors;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/*@author: Tiffany Yeh
 * Object class - class of all falling objects
 */
public class Object extends BaseActor {
	
	BaseActor object;
	String name;
	public int xPos;
	public int yPos;
	
	Object (String imgName) {
		
		object = new BaseActor();
		name = imgName;
		setTexture(new Texture(Gdx.files.internal(name)));
		velocityX = 0;
		
	}
	
	/**
	 * picks 1 of the four random x positions for food objects 
	 * @return int pos
	 */
	 public int pickPositionXFood ()
	 {
	    	Random rnd = new Random();
	    	int num = rnd.nextInt(100)+1;
	    	int pos = 10;
	    	
	    	//four possible positions of food
	    	if (num < 25)
		    	pos = 10;
		    else if (num >= 25 && num < 50)
		    	pos = 187;
		    else if (num >= 50 && num < 75)
		    	pos = 363;
		    else 
		    	pos = 539;

	    	return pos;
	  }
	 
	 /**
	  * picks one of the four x positions for garbage objects
	  * @return int pos
	  */
	 public int pickPositionXGar ()
	 {
	    	Random rnd = new Random();
	    	int num = rnd.nextInt(100)+1;
	    	int pos = 99;
	    	
	    	//four possible x-positions of garbage
	    	if (num < 25)
		    	pos = 99;
		    else if (num >= 25 && num < 50)
		    	pos = 275;
		    else if (num >= 50 && num < 75)
		    	pos = 451;
		    else 
		    	pos = 450;

	    	return pos;
	  }
	
	/**
	 * picks random y-positions
	 * @return int pos
	 */
	public int pickPositionY ()
    {
    	int pos = 450;
    	
    	//random y-position greater than 480 (height of screen)
    	Random rnd = new Random();
    	int num = rnd.nextInt(800)+480;
    	
    	pos = num;    	
    	
    	return pos;
    }


}
