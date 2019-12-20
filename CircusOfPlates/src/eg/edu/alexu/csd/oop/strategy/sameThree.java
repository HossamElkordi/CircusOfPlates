package eg.edu.alexu.csd.oop.strategy;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.objects.fallingObject;

public class sameThree implements Strategy{

	public int changeScore(int score) {

        return score+5;
    }

    public ArrayList<fallingObject> removePlates(ArrayList<fallingObject> arr) {

        for(int i=0;i<3;i++ ){
            arr.remove(arr.size()-1);
        }

        return arr;
    }
	
}
