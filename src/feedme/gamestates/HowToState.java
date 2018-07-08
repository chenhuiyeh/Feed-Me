package feedme.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import feedme.actors.BaseActor;
import feedme.managers.GameStateManager;
/*@author: Tiffany Yeh
 * HowToState - the instructions of the game
 */
public class HowToState extends GameState {

	
	private Stage mainStage;
	private Stage uiStage;
	
	private BaseActor bg;
	private BaseActor returnButton;
	
	private boolean isTouched; 
	private int firstX;
	private int firstY;
	
	public HowToState(GameStateManager gsm) {
		super(gsm);
		
	}

	@Override
	public void init() {
		mainStage = new Stage();
		uiStage = new Stage();
		
		bg = new BaseActor();
		bg.setTexture( new Texture(Gdx.files.internal("assets/howTo.png")) );
		bg.setPosition( 0, 0 );
		mainStage.addActor(bg);
		
		returnButton = new BaseActor();
		returnButton.setTexture( new Texture(Gdx.files.internal("assets/returnButton.png")) );
		returnButton.setPosition(550,40);
		uiStage.addActor(returnButton);
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		
		uiStage.act(dt);
		mainStage.act(dt);
		
	}

	@Override
	public void draw() {
		mainStage.draw();
		uiStage.draw();
		
	}

	@Override
	public void handleInput() {
		isTouched = Gdx.input.isTouched();
		firstX = Gdx.input.getX();
		firstY = Gdx.input.getY();
		
		//System.out.println("x: " + firstX + "y:" + firstY);
		if (isTouched)
		{
			if (firstX >= 550 && firstX < 550 + returnButton.getWidth() && firstY <= 440 && firstY > 440 - returnButton.getHeight())
			{
				//System.out.println("returning to menu");
				returnButton.setTexture( new Texture(Gdx.files.internal("assets/returnButtonHover.png")) );
				gsm.setState(GameStateManager.MENU);
			}
		}
		
		else if (firstX >= 550 && firstX < 550 + returnButton.getWidth() && firstY <= 440 && firstY > 440 - returnButton.getHeight())
		{
				returnButton.setTexture( new Texture(Gdx.files.internal("assets/returnButtonHover.png")) );
		}
		else
			returnButton.setTexture( new Texture(Gdx.files.internal("assets/returnButton.png")) );
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
