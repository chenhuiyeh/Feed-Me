package feedme.actors;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;

/*@author: Tiffany Yeh
 * GarbageList class - array list of garbage objects
 */

public class GarbageList extends BaseActor{

	public ArrayList<Object> garbageList;
	Object applecore;
	Object applecore2;
	Object bone;
	Object bone2;
	Object bone3;
	
	public GarbageList() {

		garbageList = new ArrayList<Object>();
		
		
		addAllObjects();
		
	}
	
	public void addAllObjects() {
		//add objects to list of garbage
		applecore = new Object ("assets/applecore.png");
		applecore.velocityY = -110;
		bone = new Object ("assets/bone.png");
		bone.velocityY = -110;
		applecore2 = new Object ("assets/applecore.png");
		applecore2.velocityY = -110;
		bone2 = new Object ("assets/bone.png");
		bone2.velocityY = -110;
		bone3 = new Object ("assets/bone.png");
		bone3.velocityY = -110;

	    garbageList.add(applecore);
	    garbageList.add(bone);
	    garbageList.add(bone2);
	    garbageList.add(bone3);
	    garbageList.add(applecore2);

		
	}
	
	public Object getObject (int i)
	{
		return garbageList.get(i);
	}
	
	public float getX (int i)
	{
		return garbageList.get(i).getX();
	}
	
	public float getY (int i)
	{
		return garbageList.get(i).getY();
	}
	
	public int getSize() {
		return garbageList.size();
	}
	
	public Rectangle getRect(int i) {
		return garbageList.get(i).getBoundingRectangle();
	}
	
	/**
	 * checks if objects overlap
	 */
	public boolean overlap(Object object) {
		
		int num = 0;
		for (int i = 0; i < garbageList.size(); i ++)
		{
			if ((object.getBoundingRectangle().overlaps(garbageList.get(i).getBoundingRectangle())))
				num ++;
		}   	
    	
		//if overlaps with object(s) other than itself
		if (num >= 2)
			return true;
		
    	return false;
	}
	

}

