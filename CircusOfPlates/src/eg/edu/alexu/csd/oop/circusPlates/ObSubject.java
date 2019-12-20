package eg.edu.alexu.csd.oop.circusPlates;

import java.util.ArrayList;
import java.util.Iterator;

import eg.edu.alexu.csd.oop.objects.fallingObject;

public class ObSubject {

	ArrayList <MyObserver> listObs ;
	 private int TnT ;
	 private int bonus;
	 private int size; //the maximum size of the stack
	 
	 public ObSubject(int T, int B,int s) {
		 this.TnT = T;
		 this.bonus = B ;
		 this.size = s ;
		 this.listObs = new ArrayList<MyObserver>();
	 }
	 public int getSize() {
		 return size ;
	 }
	 
	 public int getBonus() {
		 return bonus;
	 }
	 public int getTnT() {
		 return TnT;
	 }
	 
	 public void register (MyObserver obs) {
		 listObs.add(obs);
	 }
	 
	 public void unregister (MyObserver obs) {
		 if (listObs.contains(obs)) {
			 int index = listObs.indexOf(obs);
			 listObs.remove(index);
		 }
	 }
	 public void notifyObs (ArrayList<fallingObject> arr) {
	     Iterator<MyObserver> iterator = listObs.iterator();
	     while(iterator.hasNext()) {
	         ((MyObserver)iterator.next()).update(arr);
		 }
	 }
	
}
