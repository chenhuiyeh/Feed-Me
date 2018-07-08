package feedme.managers;

import feedme.gamestates.GameOverState;
import feedme.gamestates.GameState;
import feedme.gamestates.HowToState;
import feedme.gamestates.MenuState;
import feedme.gamestates.PlayState;
/*@author: Tiffany Yeh
 * GameStateManager Class - manages all game states
 */
public class GameStateManager {
	
	//current game state
	private GameState gameState;
	
	public static final int MENU = 0;
	public static final int PLAY = 1;
	public static final int HOWTO = 2;
	public static final int GAMEOVER = 3;
	public static boolean PAUSED = false;
	
	public GameStateManager() {
		setState(MENU);
	}
	
	public void setState(int state) {
		if (gameState != null)
			gameState.dispose();
		
		if (state == MENU) {
			//switch to menu state
			gameState = new MenuState(this);
		}
		if (state == PLAY) {
			//switch to play state
			gameState = new PlayState(this);
		}
		
		if (state == HOWTO) {
			//switch to play state
			gameState = new HowToState(this);
		}
		if (state == GAMEOVER) {
			//SWITCH TO GAMEOVER STATE
			gameState = new GameOverState(this);
			
		}

	}
	
	public void update(float dt) {
		gameState.update(dt);
	}
	
	public void draw() {
		gameState.draw();
	}

}
