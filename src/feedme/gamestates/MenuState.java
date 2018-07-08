package feedme.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

import feedme.actors.BaseActor;
import feedme.managers.GameStateManager;
import feedme.userinput.Button;
/*@author: Tiffany Yeh
 * MenuState - main menu of the game
 */
public class MenuState extends GameState{

	
	private Stage mainStage;
	private Stage uiStage;
	
	private BaseActor bg;
	
	private Button playButton;
	private Button howToButton;
	private Button quitButton;
	
	private boolean isTouched; 
	private int firstX;
	private int firstY;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
		mainStage = new Stage();
		uiStage = new Stage();
		
		bg = new BaseActor();
		bg.setTexture( new Texture(Gdx.files.internal("assets/background2.png")) );
		bg.setPosition( 0, 0 );
		mainStage.addActor(bg);
		
		playButton = new Button();
		playButton.setTexture( new Texture(Gdx.files.internal("assets/playButton.png")) );
		playButton.setPosition(300, 180);
		uiStage.addActor(playButton);
		
		
		howToButton = new Button();
		howToButton.setTexture( new Texture(Gdx.files.internal("assets/how-toButton.png")) );
		howToButton.setPosition(353, 115);
		uiStage.addActor(howToButton);

		quitButton = new Button();
		quitButton.setTexture( new Texture(Gdx.files.internal("assets/quitButton.png")) );
		quitButton.setPosition(353, 40);
		uiStage.addActor(quitButton);
		
		
		
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
		
		if (!isTouched)
		{
			if (firstX >= 300 && firstX < 300 + playButton.getWidth() && firstY <= 300 && firstY > 300 - playButton.getHeight())
			{
				playButton.setTexture( new Texture(Gdx.files.internal("assets/playButtonHover.png")) );
			}
			if (!(firstX >= 300 && firstX < 300 + playButton.getWidth() && firstY <= 300 && firstY > 300 - playButton.getHeight()))
			{
				playButton.setTexture( new Texture(Gdx.files.internal("assets/playButton.png")) );
			}
			if (firstX >= 353 && firstX < 353 + howToButton.getWidth() && firstY <= 354 && firstY > 354 - howToButton.getHeight())
			{
				howToButton.setTexture( new Texture(Gdx.files.internal("assets/how-toButtonHover.png")) );	
			}
			if (!(firstX >= 353 && firstX < 353 + howToButton.getWidth() && firstY <= 354 && firstY > 354 - howToButton.getHeight()))
			{
				howToButton.setTexture( new Texture(Gdx.files.internal("assets/how-toButton.png")) );
			}
			if (firstX >= 353 && firstX < 353 + quitButton.getWidth() && firstY <= 440 && firstY > 440 - quitButton.getHeight())
			{
				quitButton.setTexture( new Texture(Gdx.files.internal("assets/quitButtonHover.png")) );
			}
			if (!(firstX >= 353 && firstX < 353 + quitButton.getWidth() && firstY <= 440 && firstY > 440 - quitButton.getHeight()))
			{
				quitButton.setTexture( new Texture(Gdx.files.internal("assets/quitButton.png")) );
			}
		}
		else if (isTouched)
		{
			if (firstX >= 300 && firstX < 300 + playButton.getWidth() && firstY <= 300 && firstY > 300 - playButton.getHeight())
			{
				//System.out.println("to play state");
				gsm.setState(GameStateManager.PLAY);
			}
			if (firstX >= 353 && firstX < 353 + howToButton.getWidth() && firstY <= 354 && firstY > 354 - howToButton.getHeight())
			{
				//System.out.println("to how-to state");
				gsm.setState(GameStateManager.HOWTO);
			}
			if (firstX >= 353 && firstX < 353 + quitButton.getWidth() && firstY <= 440 && firstY > 440 - quitButton.getHeight())
			{
				System.exit(0);
			}
		}
		
	}

	@Override
	public void dispose() {

		
	}


}
