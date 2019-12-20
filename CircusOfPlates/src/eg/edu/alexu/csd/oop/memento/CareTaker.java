package eg.edu.alexu.csd.oop.memento;

import java.util.ArrayList;

public class CareTaker {

private ArrayList<memento> mementoList = new ArrayList<memento>();
	
	public void addMemento(memento memento) {
		if(this.mementoList.size() == 201) {
			this.mementoList.remove(0);
		}
		this.mementoList.add(memento);
	}
	
	public memento getMemento(int index) {
		if (index < 0 || index >= this.mementoList.size()) {
			return null;
		}
		return this.mementoList.get(index);
	}

	public void reset() { this.mementoList.clear(); }

	public int getsize(){return mementoList.size();}
	
}
