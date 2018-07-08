package feedme.actors;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import feedme.main.FeedMe;

/*modified from CheesePlease example*/
 
public class AnimatedActor extends BaseActor
{
    public float elapsedTime;
    public Animation anim;

    public AnimatedActor()
    {
        super();
        elapsedTime = 0;
    }

    public void setAnimation(Animation a)
    { 
        Texture t = a.getKeyFrame(0).getTexture();
        setTexture( t );
        anim = a;
    }
    
    public void wrap() {
    	if(x < 0)
    		x = 0;
    	if(x > FeedMe.WIDTH - boundary.width)
    		x = FeedMe.WIDTH - boundary.width;
    }

    public void act(float dt)
    {
        super.act( dt );
        elapsedTime += dt;
    }

    public void draw(Batch batch, float parentAlpha) 
    {
        region.setRegion( anim.getKeyFrame(elapsedTime) );
        super.draw(batch, parentAlpha);
    }
}