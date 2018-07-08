package feedme.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
/*@author: Tiffany Yeh
 * Pusheen class - the pusheen actor
 */
public class Pusheen extends AnimatedActor{
	
	AnimatedActor pusheen;
	private float width;
	private float height;
	
	
	
	public Pusheen () {
		pusheen = new AnimatedActor();
		width = getWidth();
		height = getHeight();
		
		velocityX = 0;
		velocityY = 0;
		
		
	}
	
	//sets animation for pusheen
	public void moveAnim() {
		
		//relays between 3 images
		TextureRegion[] frames = new TextureRegion[3];
        for (int n = 1; n < 4; n++)
        {   
            String fileName = "assets/pusheen" + n + ".png";
            Texture tex = new Texture(Gdx.files.internal(fileName));
            tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            frames[n-1] = new TextureRegion( tex );
        }
        Array<TextureRegion> framesArray = new Array<TextureRegion>(frames);

        Animation anim = new Animation(0.1f, framesArray, Animation.PlayMode.LOOP);

        setAnimation( anim );
        setOrigin( width/2, height/2 );
        setTexture( new Texture(Gdx.files.internal("assets/pusheen1.png")) );
        setPosition( 160, 0 );
	}


	
}
