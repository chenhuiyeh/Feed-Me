package feedme.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
/*@author: Tiffany Yeh
 * ScorePopUps class - sets fade in fade out animation of score
 */
public class ScorePopUps extends BaseActor{
	BaseActor scorePopUps;
	
	public ScorePopUps () {
		scorePopUps = new BaseActor();
		setTexture(new Texture(Gdx.files.internal("assets/plusFive.png")));
		addAction(Actions.sequence(Actions.fadeIn(0.15f), Actions.fadeOut(0.15f)));
	}
	

}
