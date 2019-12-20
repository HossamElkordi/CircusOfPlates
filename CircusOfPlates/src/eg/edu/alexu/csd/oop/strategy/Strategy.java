package eg.edu.alexu.csd.oop.strategy;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.objects.fallingObject;

public interface Strategy {

	public int changeScore (int score);
    public ArrayList<fallingObject> removePlates(ArrayList<fallingObject> arr);
	
}
