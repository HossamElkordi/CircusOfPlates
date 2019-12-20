package eg.edu.alexu.csd.oop.strategy;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.objects.fallingObject;

public class Bomb implements Strategy{

	public int changeScore(int score) {
        return score-1;
    }

    public ArrayList<fallingObject> removePlates(ArrayList<fallingObject> arr){
        if(arr.size()<2){
            arr.remove(0);
        }
        else {
            for (int i = 0; i < 2; i++) {
                arr.remove(arr.size() - 1);
            }
        }

        return arr;
    }
	
}
