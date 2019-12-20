package eg.edu.alexu.csd.oop.memento;

import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public class memento {

	private List<GameObject> control;
    private List<GameObject> moving;
    private List<GameObject> constant;

     public List<GameObject> getMoving() {
        return moving;
    }

     public List<GameObject> getConstant() {
        return constant;
    }


     public List<GameObject> getControl() {
        return control;
    }

    memento(List<GameObject>control, List<GameObject>moving, List<GameObject>constant){
        this.moving=moving;
        this.constant=constant;
        this.control=control;
    }
	
}
