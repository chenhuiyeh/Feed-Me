package feedme.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import feedme.gamestates.PlayState;
import feedme.managers.GameStateManager;

/*@author: Tiffany Yeh
 * FeedMe - the whole game loop controller
 */
public class FeedMe implements ApplicationListener{

	public static int WIDTH;
	public static int HEIGHT;
	
	private GameStateManager gsm;

	public static boolean resume;
	private boolean isTouched; 
	private int firstX;
	private int firstY;
	
	public void create() {
		WIDTH = Gdx.graphics.getWidth();
		HEIGHT = Gdx.graphics.getHeight();
		gsm = new GameStateManager();
		
	}
	
	public void render() {
		
		//stops updating when game is paused
	    if(GameStateManager.PAUSED)
	    {
	    	
	    	isTouched = Gdx.input.isTouched();
			firstX = Gdx.input.getX();
			firstY = Gdx.input.getY();
			//System.out.println ("PAUSED");
			//System.out.println ("x: " + firstX + "y: " + firstY);
			
			
			if (!isTouched)
			{
				if (firstX >= 250 && firstX < 250 + PlayState.resumeButton.getWidth() && firstY <= 230 && firstY > 230 - PlayState.resumeButton.getHeight())
				{
					PlayState.resumeButton.setTexture( new Texture(Gdx.files.internal("assets/resumeButtonHover.png")) );
				}
				if (!(firstX >= 250 && firstX < 250 + PlayState.resumeButton.getWidth() && firstY <= 230 && firstY > 230 - PlayState.resumeButton.getHeight()))
				{
					PlayState.resumeButton.setTexture( new Texture(Gdx.files.internal("assets/resumeButton.png")) );
				}
				if (firstX >= 250 && firstX < 250 + PlayState.restartButton.getWidth() && firstY <= 330 && firstY > 330 - PlayState.restartButton.getHeight())
				{
					PlayState.restartButton.setTexture( new Texture(Gdx.files.internal("assets/restartButtonHover.png")) );	
				}
				if (!(firstX >= 250 && firstX < 250 + PlayState.restartButton.getWidth() && firstY <= 330 && firstY > 330 - PlayState.restartButton.getHeight()))
				{
					PlayState.restartButton.setTexture( new Texture(Gdx.files.internal("assets/restartButton.png")) );
				}
				if (firstX >= 250 && firstX < 250 + PlayState.menuButton.getWidth() && firstY <= 430 && firstY > 430 - PlayState.menuButton.getHeight())
				{
					PlayState.menuButton.setTexture( new Texture(Gdx.files.internal("assets/menuButtonHover.png")) );
				}
				if (!(firstX >= 250 && firstX < 250 + PlayState.menuButton.getWidth() && firstY <= 430 && firstY > 430 - PlayState.menuButton.getHeight()))
				{
					PlayState.menuButton.setTexture( new Texture(Gdx.files.internal("assets/menuButton.png")) );
				}
			}
			else if (isTouched)
			{
				if (firstX >= 250 && firstX < 250 + PlayState.resumeButton.getWidth() && firstY <= 230 && firstY > 230 - PlayState.resumeButton.getHeight())
				{
					
					Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
				    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
					GameStateManager.PAUSED = false;
					resume = true;
					
				}
				if (firstX >= 250 && firstX < 250 + PlayState.restartButton.getWidth() && firstY <= 330 && firstY > 330 - PlayState.restartButton.getHeight())
				{
					resume = false;
					GameStateManager.PAUSED = false;
					PlayState.score = 0;
					gsm.setState(GameStateManager.PLAY);
				}
				if (firstX >= 250 && firstX < 250 + PlayState.menuButton.getWidth() && firstY <= 440 && firstY > 440 - PlayState.menuButton.getHeight())
				{
					
					resume = false;
					GameStateManager.PAUSED = false;
					PlayState.score = 0;
					gsm.setState(GameStateManager.MENU);
				}
			}
	    }
	    
	    //updates
	    else
	    {
	    	gsm.update(Gdx.graphics.getDeltaTime());
	    
	    	Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
	    	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    }
	    
	    gsm.draw();

		
	}

	public void dispose() {

		
	}

	public void pause() {

		
	}

	

	public void resize(int arg0, int arg1) {

		
	}

	public void resume() {

		
	}
	
	

}
