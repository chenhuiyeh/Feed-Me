package feedme.gamestates;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import feedme.actors.BaseActor;
import feedme.managers.GameStateManager;
import feedme.userinput.Button;

/*@author: Tiffany Yeh
 * GameOverState class - switched when game over conditions met
 */
public class GameOverState extends GameState{

	private Stage mainStage;
	private Stage uiStage;
	
	private BaseActor bg;
	private BaseActor gameOver;
	private BaseActor scoreImg;
	
	private boolean isTouched; 
	private int firstX;
	private int firstY;
	private Button menuButton;
	
	private Label scoreLabel;
	private int score;
	
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		mainStage = new Stage();
		uiStage = new Stage();
		
		bg = new BaseActor();
		bg.setTexture( new Texture(Gdx.files.internal("assets/background.png")) );
		bg.setPosition( 0, 0 );
		mainStage.addActor(bg);
		
		gameOver = new BaseActor();
		gameOver.setTexture( new Texture(Gdx.files.internal("assets/gameover.png")) );
		gameOver.setPosition( 80, 200 );
		mainStage.addActor(gameOver);
		
		scoreImg = new BaseActor();
		scoreImg.setTexture( new Texture(Gdx.files.internal("assets/score.png")) );
		scoreImg.setPosition( 150, 210 );
		mainStage.addActor(scoreImg);
		
		score = PlayState.getScore();
		
		BitmapFont font = new BitmapFont();
		LabelStyle style = new LabelStyle( font, Color.BLACK );

		String scoreStr = Integer.toString(score);
		scoreLabel = new Label( scoreStr, style );
		scoreLabel.setFontScale(2);
        scoreLabel.setPosition(340,235);
        uiStage.addActor( scoreLabel );
        
        menuButton = new Button();
        menuButton.setTexture(new Texture(Gdx.files.internal("assets/menuButton.png")));
        menuButton.setPosition(250, 130);
        mainStage.addActor(menuButton);
		
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		
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
			if (firstX >= 250 && firstX < 250 + menuButton.getWidth() && firstY <= 350 && firstY > 350 - menuButton.getHeight())
			{
				menuButton.setTexture( new Texture(Gdx.files.internal("assets/menuButtonHover.png")) );
			}
			if (!(firstX >= 250 && firstX < 250 + menuButton.getWidth() && firstY <= 350 && firstY > 350 - menuButton.getHeight()))
			{
				menuButton.setTexture( new Texture(Gdx.files.internal("assets/menuButton.png")) );
			}
		}
		else if(isTouched)
		{
			if (firstX >= 250 && firstX < 250 + menuButton.getWidth() && firstY <= 350 && firstY > 350 - menuButton.getHeight())
			{
				gsm.setState(GameStateManager.MENU);

			}
		}
		
	}

	@Override
	public void dispose() {
		
	}

}

