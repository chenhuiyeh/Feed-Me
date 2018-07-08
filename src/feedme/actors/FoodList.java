package feedme.actors;

import java.util.ArrayList;

import com.badlogic.gdx.math.Rectangle;

/*@author: Tiffany Yeh
 * FoodList class - array list of food objects
 */

public class FoodList{
	
	public ArrayList<Object> foodList;
	
	Object burger;
	Object chips;
	Object cake;
	Object pizza;
	Object burger2;
	Object cake2;
	Object pizza2;

		
	public FoodList() {

		foodList = new ArrayList<Object>();
		addAllObjects();
		
	}
	
	public void addAllObjects () {
		burger = new Object ("assets/burger.png");
		burger.velocityY = -120;
		chips = new Object ("assets/chips.png");
		chips.velocityY = -120;
		cake = new Object ("assets/cake.png");
		cake.velocityY = -120;
		pizza = new Object ("assets/pizza.png");
		pizza.velocityY = -120;
		
		burger2 = new Object ("assets/burger.png");
		burger2.velocityY = -120;
		cake2 = new Object ("assets/cake.png");
		cake2.velocityY = -120;
		pizza2 = new Object ("assets/pizza.png");
		pizza2.velocityY = -120;


		
		//add objects to list of food
	    foodList.add(burger);
	    foodList.add(chips);
	    foodList.add(cake);
	    foodList.add(pizza);
	    foodList.add(burger2);
	    foodList.add(cake2);
	    foodList.add(pizza2);
	}
	
	public Object getObject (int i)
	{
		return foodList.get(i);
	}
	
	public float getX (int i)
	{
		return foodList.get(i).getX();
	}
	
	public float getY (int i)
	{
		return foodList.get(i).getY();
	}
	
	public int getSize() {
		return foodList.size();
	}
	
	public Rectangle getRect(int i) {
		return foodList.get(i).getBoundingRectangle();
	}
	
	/**
	 * checks if objects overlap
	 */
	public boolean overlap(Object object) {
		
		int num = 0;
		for (int i = 0; i < foodList.size(); i ++)
		{
			if ((object.getBoundingRectangle().overlaps(foodList.get(i).getBoundingRectangle())))
				num++;
		}  
		
		//if overlaps with object(s) other than itself
		if (num >= 2)
			return true;
    	
    	return false;
	}
	
}
