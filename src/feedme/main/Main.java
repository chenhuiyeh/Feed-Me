package feedme.main;



import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/*@author: Tiffany Yeh
 * Launcher of game
 */

public class Main {
	
	public static void main (String [] args) {
		FeedMe myProgram = new FeedMe();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration(); 
		
		//Dimensions
		config.width = 640;
		config.height = 480;
		config.resizable = false;
		config.forceExit = true;
			
		new LwjglApplication(myProgram,config);
	}

}
