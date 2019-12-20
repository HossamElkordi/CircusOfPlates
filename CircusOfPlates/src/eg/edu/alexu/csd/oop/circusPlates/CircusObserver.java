package eg.edu.alexu.csd.oop.circusPlates;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.objects.fallingObject;

public class CircusObserver implements MyObserver{

	private static int Tracker = 0 ;
	private int observerID ;
	private int TnT ; 
	private int bonus;
	private ObSubject boss;
	private int Updates ;
	private int maxStack ; 
	private CircusLogger cl ;
	
	public void MyObserver(ObSubject b) {
		this.boss = b ;
		
		this.TnT = boss.getTnT();
		
		this.bonus = boss.getBonus();
		
		this.observerID = ++Tracker;
		
		this.maxStack = boss.getSize();
		
		this.boss.register(this);
		
		cl = new CircusLogger();
		
	}
	

	public void update(ArrayList<fallingObject> arr) {
		int len = arr.size();
		
		if (len == 0 ) {
			Updates = 0;
			return;
		}
		else if (len == 1 || len == 2) {
			if (arr.get(len-1).getSerial() == TnT) {
				Updates=1;
				cl.InfoLog("TNT captured");
				return;
			}
			else if (arr.get(len-1).getSerial() == bonus) {
				Updates = 2;
				cl.InfoLog("Gift captured");
				return;
			}
			else {
				Updates = 0;
				return ;
				}
		}
		else {
			if (arr.get(len-1).getSerial() == TnT) {
				Updates = 1;
				cl.InfoLog("TNT captured");
				return ;
			}
			else if (arr.get(len-1).getSerial() == bonus && len < maxStack) {
				Updates = 2 ;
				cl.InfoLog("Gift captured");
				return ;
			}
			else if (arr.get(len-1).getSerial() == bonus && len >= maxStack) {
				Updates = -2 ; // This means game is over but the score will increase for one last time
				cl.WarningLog("Game Over");
				return ;
			}
			else {
				if (arr.get(len-1).getSerial() == arr.get(len -2 ).getSerial() && arr.get(len -2).getSerial() == arr.get(len -3).getSerial()) {
					Updates =  arr.get(len-1).getSerial();
					cl.InfoLog("3 crates collected");
					return ;
				}
				
				else if (len >= maxStack) {
					Updates = -1 ; //This means stack is full and game is over
					cl.WarningLog("Game Over");
					return;
				}
				else 
				{
					Updates = 0;
					return ;
				}
		}
		
	 }
	}
	
	public int getID(){
		return observerID;
	}
	public int getUpdates() {
		int var = Updates;
		Updates = 0;
		return var;
	}
	
}
