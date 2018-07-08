package feedme.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import feedme.actors.BaseActor;
import feedme.actors.FoodList;
import feedme.actors.GarbageList;
import feedme.actors.LivesList;
import feedme.actors.Pusheen;
import feedme.actors.ScorePopUps;
import feedme.main.FeedMe;
import feedme.managers.GameStateManager;
import feedme.userinput.Button;

/*@author: Tiffany Yeh
 * PlayState - main game
 * move Pusheen left or right using left/right keys to catch food and avoid garbage
 */
public class PlayState extends GameState{

	private Stage mainStage;
	private Stage uiStage;
	private Stage pauseStage;
	
	//main stage
	private Pusheen pusheen;
	
	private FoodList foodList;
	private GarbageList garbageList;
	private LivesList lives;
	private int numLives;
	
	private ScorePopUps scorePopUps;
	
	//pause
	//public static boolean paused;
	public static BaseActor pauseBg;
	public static BaseActor resumeButton;
	public static BaseActor restartButton;
	//private BaseActor exitButton;
	public static BaseActor menuButton;
	
	
	//ui stage
	private BaseActor bg;
	private Button pauseButton;
	private boolean isTouched; 
	private int firstX;
	private int firstY;
	
	private float timeElapsed;
	private Label timeLabel;
	
    private Label scoreLabel;
    public static int score = 0;
	
	public PlayState(GameStateManager gsm) {
		super(gsm);
		
	}

	@Override
	public void init() {
		
		mainStage = new Stage();
		
		pusheen = new Pusheen();
		pusheen.moveAnim();
		mainStage.addActor(pusheen);
		
		foodList = new FoodList();
		garbageList = new GarbageList();
		
		//sets initial position of food objects
		for (int i = 0; i < foodList.getSize(); i++)
		{
			foodList.getObject(i).setPosition(foodList.getObject(i).pickPositionXFood(), foodList.getObject(i).pickPositionY());
		}
		
		//checks if any food object overlap with any other food objects
		for (int i = 0; i < foodList.getSize(); i++)
		{
			if (foodList.overlap(foodList.getObject(i)))
			{
   			 foodList.getObject(i).setPosition(foodList.getObject(i).getX(), 480 + (i)*280);
			}
		}
		
		//sets position of the garbage
		for (int i = 0; i < garbageList.getSize(); i++)
		{
			garbageList.getObject(i).setPosition(garbageList.getObject(i).pickPositionXGar(), garbageList.getObject(i).pickPositionY());
		}
		
		//checks if any object overlaps with any other object in the array
		for (int i = 0; i < garbageList.getSize(); i++)
		{
			if (garbageList.overlap(garbageList.getObject(i)))
			{
   			 garbageList.getObject(i).setPosition(garbageList.getObject(i).getX(), 480 + (i)*250);
			}
		}
		
		
        for (int i = 0; i < foodList.getSize(); i++)
        {
        	mainStage.addActor( foodList.getObject(i) );
        }
  
        
       
        for (int i = 0; i < garbageList.getSize(); i++)
        {
        	mainStage.addActor( garbageList.getObject(i) );
        }
        
        numLives = 2;
        lives = new LivesList();
        for(int i = 0; i < lives.getSize(); i++)
        {
        	mainStage.addActor(lives.getLife(i));
        }
		
		uiStage = new Stage();

		bg = new BaseActor();
		bg.setTexture( new Texture(Gdx.files.internal("assets/background.png")) );
        bg.setPosition( 0, 0 );
        uiStage.addActor( bg );
        
        //setup for timer 30 seconds
        timeElapsed = 31;
        BitmapFont font = new BitmapFont();
        String text = "Time: 30";
        LabelStyle style = new LabelStyle( font, Color.BLACK );
        timeLabel = new Label( text, style );
        timeLabel.setFontScale(2);
        timeLabel.setPosition(20,450); 
        
        uiStage.addActor( timeLabel );
        
        String scoreText = "Score: 0";
        scoreLabel = new Label( scoreText, style );
        scoreLabel.setFontScale(2);
        scoreLabel.setPosition(250,450);
        uiStage.addActor( scoreLabel );
        
        pauseButton = new Button();
        pauseButton.setTexture(new Texture(Gdx.files.internal("assets/pauseButton.png")));
        pauseButton.setPosition(570,  430);
        mainStage.addActor(pauseButton);
        
        scorePopUps = new ScorePopUps();
        
		//pause
        pauseStage = new Stage();
        pauseBg = new BaseActor();
		pauseBg.setTexture(new Texture(Gdx.files.internal("assets/pauseScreen.png")));
		pauseBg.setPosition(0, 0);	

		resumeButton = new BaseActor();
		resumeButton.setTexture(new Texture(Gdx.files.internal("assets/resumeButton.png")));
		resumeButton.setPosition(250, 250);	

		restartButton = new BaseActor();
		restartButton.setTexture(new Texture(Gdx.files.internal("assets/restartButton.png")));
		restartButton.setPosition(250, 150);	

		menuButton = new BaseActor();
		menuButton.setTexture(new Texture(Gdx.files.internal("assets/menuButton.png")));
		menuButton.setPosition(250, 50);	

	}

	@Override
	public void update(float dt) {
			handleInput();
			
			//if timer passes 30 seconds
			if (timeElapsed < 0)
			{
				gsm.setState(GameStateManager.GAMEOVER);
			}
			
			//checking food objects
			for (int i = 0; i < foodList.getSize(); i++)
		    {
				//if food overlaps with pusheen from above
				if (pusheen.getBoundingRectangle().overlaps( foodList.getObject(i).getBoundingRectangle()) && pusheen.getY() + pusheen.getHeight() + 30 <  foodList.getObject(i).getY() +  foodList.getObject(i).getHeight())
		        {
		        	//add 5 to score
					score += 5;
					
					//score animations
		        	scorePopUps = new ScorePopUps();
		        	scorePopUps.setPosition(pusheen.getX() + 10, pusheen.getY() + pusheen.getHeight() + 10);
		        	mainStage.addActor(scorePopUps);
		        	
		        	scoreLabel.setText("Score: " + (int)score);  
		        	
		        	//resets food object positions
		        	foodList.getObject(i).setPosition(foodList.getObject(i).pickPositionXFood(), foodList.getObject(i).pickPositionY());
		        	
		        	//prevents food objects from overlapping with each other
			        if (foodList.overlap(foodList.getObject(i)))
			       	{
			       		//pushes item off screen if overlaps
			       		foodList.getObject(i).setPosition(foodList.getObject(i).getX(),  -70);
			       	}
			        
			        mainStage.addActor(  foodList.getObject(i) );
		        }
				
				//resets position of food objects to the top if they fall below visible screen
				if ( foodList.getObject(i).getY() <= -foodList.getObject(i).getHeight())
				{
					foodList.getObject(i).setPosition(foodList.getObject(i).pickPositionXFood(), foodList.getObject(i).pickPositionY());
					
					//prevent them from overlapping
					if (foodList.overlap(foodList.getObject(i)))
					{
						foodList.getObject(i).setPosition(foodList.getObject(i).getX(), -70);
					}
		        mainStage.addActor(  foodList.getObject(i) );
				}
		   }
		        
		   //checking garbage objects     
		   for (int i = 0; i < garbageList.getSize(); i++)
		   {
			   //if garbage overlaps with pusheen from above
		       if (pusheen.getBoundingRectangle().overlaps(garbageList.getObject(i).getBoundingRectangle()) && pusheen.getY() + pusheen.getHeight() + 30 < garbageList.getObject(i).getY() + garbageList.getObject(i).getHeight())
		       {	
		    	   //decreases numLives by 1 and sets its visibility to false
		    	   lives.getLife(numLives).setVisible(false);
		    	   numLives--;
		    	   
		    	   //switches to game over state if no more lives
		    	   if (numLives < 0)
		    		   gsm.setState(GameStateManager.GAMEOVER);
		        			
		    	   garbageList.getObject(i).setPosition(garbageList.getObject(i).pickPositionXGar(), garbageList.getObject(i).pickPositionY());
		    	   if (garbageList.overlap(garbageList.getObject(i)))
		    	   {
		    		   //pushes item off screen if overlaps
		    		   garbageList.getObject(i).setPosition(garbageList.getObject(i).getX(), -70);
		    	   }
		 
		    	   mainStage.addActor( garbageList.getObject(i) );
		        
		        }
		        if (garbageList.getObject(i).getY() <= -garbageList.getObject(i).getHeight())
		        {
		        		
		        	garbageList.getObject(i).setPosition(garbageList.getObject(i).pickPositionXGar(), garbageList.getObject(i).pickPositionY());
		        		
		        	if (garbageList.overlap(garbageList.getObject(i)))
		        	{
		        		garbageList.getObject(i).setPosition(garbageList.getObject(i).getX(),  -70);
		        	}		
		        		
		        	mainStage.addActor( garbageList.getObject(i) );
		        }
		  }
	        
		   if (timeElapsed >= 0)
	       {
			   timeElapsed -= dt;
	           timeLabel.setText( "Time: " + (int)timeElapsed );
	       }
			
		uiStage.act(dt);
		mainStage.act(dt);
		pauseStage.act(dt);
		
	}

	@Override
	public void draw() {
		uiStage.draw();
		mainStage.draw();
		pauseStage.draw();
	}

	@Override
	public void handleInput() {
		//clears pause stage when resume game
		if(FeedMe.resume)
			pauseStage.clear();
		
		//resets pusheen's velocity to 0
		pusheen.velocityX = 0;
		pusheen.velocityY = 0;
		//moves left if left key is pressed
		if (Gdx.input.isKeyPressed(Keys.LEFT)) 
        	pusheen.velocityX -= 130;
		//moves right if right key is pressed
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
        	pusheen.velocityX += 130;
        
        //bounds pusheen within the screen
        pusheen.setX( MathUtils.clamp( pusheen.getX(), 0,  FeedMe.WIDTH - pusheen.getWidth() ));
        
        //check if mouse presses pause
		isTouched = Gdx.input.isTouched();
		firstX = Gdx.input.getX();
		firstY = Gdx.input.getY();

		if (!isTouched)
		{
			if (firstX >= 570 && firstX < 570 + pauseButton.getWidth() && firstY <= 41 && firstY > 41 - pauseButton.getHeight())
			{
				pauseButton.setTexture( new Texture(Gdx.files.internal("assets/pauseButton.png")) );
			}
			if (!(firstX >= 570 && firstX < 570 + pauseButton.getWidth() && firstY <= 41 && firstY > 41 - pauseButton.getHeight()))
			{
				pauseButton.setTexture( new Texture(Gdx.files.internal("assets/pauseButtonHover.png")) );
			}
		}
		else if(isTouched)
		{
			if (firstX >= 570 && firstX < 570 + pauseButton.getWidth() && firstY <= 41 && firstY > 41 - pauseButton.getHeight())
			{
				pauseStage.addActor(pauseBg);
				pauseStage.addActor(resumeButton);
				pauseStage.addActor(restartButton);
				pauseStage.addActor(menuButton);
				GameStateManager.PAUSED = true; //stops rendering game
			}
		}
        

	}

	@Override
	public void dispose() {
	}    
    
    /**
     * returns score to game over state
     */
    public static int getScore() {
    	
    	return score;
    }

	

}
