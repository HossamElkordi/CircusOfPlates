package eg.edu.alexu.csd.oop.strategy;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.objects.fallingObject;

public class Bonus implements Strategy {

	 public int changeScore(int score) {
        return score+3;
    }

    public ArrayList<fallingObject> removePlates(ArrayList<fallingObject> arr) {
        return arr;
    }

}
