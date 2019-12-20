package eg.edu.alexu.csd.oop.circusPlates;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.objects.fallingObject;

public interface MyObserver {

	public void update (ArrayList<fallingObject> arr);
    public int getUpdates();
	
}
