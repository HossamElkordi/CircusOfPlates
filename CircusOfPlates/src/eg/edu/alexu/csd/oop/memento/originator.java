package eg.edu.alexu.csd.oop.memento;

import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;

public class originator {

	private List<GameObject> control;
    private List<GameObject> moving;
    private List<GameObject> constant;
    public void setControl(List<GameObject> control) {
        this.control = control;
    }

    public void setMoving(List<GameObject> moving) {
        this.moving = moving;
    }

    public void setConstant(List<GameObject> constant) {
        this.constant = constant;
    }


    public memento saveStateToMemento(){
        return new memento(control,moving,constant);
    }

    public List<GameObject> getControl() {
        return control;
    }

    public List<GameObject> getMoving() {
        return moving;
    }

    public List<GameObject> getConstant() {
        return constant;
    }


    public void getStateFromMemento(memento Memento){
        this.constant=Memento.getConstant();
        this.moving=Memento.getMoving();
        this.control=Memento.getControl();

    }
	
}
