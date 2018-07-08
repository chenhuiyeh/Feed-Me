package feedme.actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
/*@author: Tiffany Yeh
 * Lives class - lives of pusheen
 */
public class Lives extends AnimatedActor{
	
	private float width;
	private float height;
	private float x;
	private float y;
	
	
	public Lives () {
		width = getWidth();
		height = getHeight();
		setTexture(new Texture(Gdx.files.internal("assets/heart1.png")));
		velocityX = 0;
		velocityY = 0;
		x = 0;
		y = 0;
		
	}
	public void setPosition(float posx, float posy) {
		x = posx;
		y = posy;
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	//beating animation of heart
	public void beatingAnim() {
		//relays two heart images (1 small 1 big)
		TextureRegion[] frames = new TextureRegion[2];
        for (int n = 1; n < 3; n++)
        {   
            String fileName = "assets/heart" + n + ".png";
            Texture tex = new Texture(Gdx.files.internal(fileName));
            tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
            frames[n-1] = new TextureRegion( tex );
        }
        Array<TextureRegion> framesArray = new Array<TextureRegion>(frames);

        Animation anim = new Animation(0.25f, framesArray, Animation.PlayMode.LOOP);

        setAnimation( anim );
        setOrigin( width/2, height/2 );
        setTexture( new Texture(Gdx.files.internal("assets/heart1.png")) );
        setPosition( getX(), getY() );
		
	}


}
