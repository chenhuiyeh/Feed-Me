package feedme.gamestates;

import feedme.managers.GameStateManager;
/*@author: Tiffany Yeh
 * GameState abstract class - all game state classes inherited from this class contains all five methods 
 */
public abstract class GameState {
	
	protected GameStateManager gsm;
	
	protected GameState(GameStateManager gsm)
	{
		this.gsm = gsm;
		init();
	}
	
	public abstract void init();
	public abstract void update(float dt);
	public abstract void draw();
	
	//gets called in update()
	public abstract void handleInput();
	
	//switch to another game state
	public abstract void dispose();

}
