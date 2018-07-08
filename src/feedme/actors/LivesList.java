package feedme.actors;

import java.util.ArrayList;
/*@author: Tiffany Yeh
 * LivesList - array list of lives
 */
public class LivesList{
	
	public ArrayList<Lives> lifeList;
	Lives life1;
	Lives life2;
	Lives life3;
	
	public LivesList() {
		
		lifeList = new ArrayList<Lives>();
		addLives();
		
	}
	
	public void addLives() {
		life1 = new Lives();
		life1.setPosition(10, 400);
		life1.beatingAnim();
		life2 = new Lives();
		life2.setPosition(60, 400);
		life2.beatingAnim();
		life3 = new Lives();
		life3.setPosition(110, 400);
		life3.beatingAnim();
		lifeList.add (life1);
		lifeList.add (life2);
		lifeList.add (life3);
	}
	
	public int getSize() {
		return lifeList.size();
	}
	
	public Lives getLife(int i) {
		return lifeList.get(i);
	}
	
	public ArrayList<Lives> remove(int i) {
		lifeList.remove(i);
		return lifeList;
	}

}
