package eg.edu.alexu.csd.oop.worlds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.circusPlates.AudioFactory;
import eg.edu.alexu.csd.oop.circusPlates.CircusLogger;
import eg.edu.alexu.csd.oop.circusPlates.CircusObserver;
import eg.edu.alexu.csd.oop.circusPlates.MyObserver;
import eg.edu.alexu.csd.oop.circusPlates.ObSubject;
import eg.edu.alexu.csd.oop.circusPlates.cratesFactory;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.memento.CareTaker;
import eg.edu.alexu.csd.oop.memento.originator;
import eg.edu.alexu.csd.oop.objects.BackgroundObject;
import eg.edu.alexu.csd.oop.objects.bonusObj;
import eg.edu.alexu.csd.oop.objects.clownObject;
import eg.edu.alexu.csd.oop.objects.explosion;
import eg.edu.alexu.csd.oop.objects.fallingObject;
import eg.edu.alexu.csd.oop.state.LevelContext;

public class WorldController {

	private int score;
    private int moveSpeed = 2;
    private int colX,colY;
    private int[] lastX = {-1,-1,-1,-1,-1};
    private int lastxindex = 0;
    private int[] xes = {40, 140, 240, 340, 440, 540, 640, 740, 840, 940, 1040, 1140};
    private GameObject top = null;
    private cratesFactory a = cratesFactory.getInstance();
    private LevelContext lvlContext;
    private originator Originator = new originator();
    private boolean removeexpflag = false, removebonus = false;
    private AudioFactory audio;
    private GameObject temp0;
    private CareTaker tem = new CareTaker();
    private int explosionCounter = -1, bonuscounter = -1, highScoreSound = -1;
    private GameObject exp = new explosion(0,0), temppp, bonus = new bonusObj(0,0);
    private ObSubject ob;
    private MyObserver obsr;
    private int highScore, highScoreFlag = 0;
    private boolean hs = false;
    private CircusLogger cl;
    
    public WorldController() {
    	cl = new CircusLogger();
    	audio = AudioFactory.getInstance();
    	lvlContext = LevelContext.getInstance();
    	lvlContext.setLevel(score);
    	moveSpeed = lvlContext.getLevel().getSpeed();
    	
    	try {
			audio.play("game", true);
		} catch (IOException e) {
			cl.SevereLog("game audio file not working",e);
			e.printStackTrace();
		}
    }
    /*
     * Memento Stuff
     */
    public void setMemento(List<GameObject> constant, List<GameObject> control, List<GameObject> moving) {
        Originator.setConstant(listCloner(constant));
        Originator.setControl(listCloner(control));
        Originator.setMoving(listCloner(moving));
        tem.addMemento(Originator.saveStateToMemento());
    }
    
    private List<GameObject> listCloner(List<GameObject>target){
        List<GameObject>output = new LinkedList<>();
        for(int i=0;i<target.size();i++){
            output.add(gameObjectCloner(target.get(i)));
        }
        return output;
    }
    
    private GameObject gameObjectCloner(GameObject m){
        if(m instanceof fallingObject){
            return (((fallingObject) m).clone());
        }
        else if(m.getClass()==clownObject.class){
            return (((clownObject) m).clone());
        }
        else if(m.getClass()==BackgroundObject.class){
            return ((BackgroundObject) m).clone();
        }
        return null;
    }
    /*
     * Refresh Stuff
     */
    public void removeFromDown(List<GameObject> moving, int height) {
    	Iterator<GameObject> itr = moving.iterator();
        while(itr.hasNext()){
            if(((GameObject)itr.next()).getY()>height){itr.remove();}
        }
    }
    
    public GameObject getTop() {
		return top;
	}
	public void setColX(int colX) {
		this.colX = colX;
	}
	public void setColY(int colY) {
		this.colY = colY;
	}
	public void setTop(GameObject top) {
		this.top = top;
	}
	public void checkAnimationAndSoundEffect() {
    	if(explosionCounter!=-1){
            if(explosionCounter==10){removeexpflag=true;explosionCounter=-1; audio.stop(); audio.setCurrent("game");}
            else{explosionCounter++;}
        }

        if(bonuscounter!=-1){
            if(bonuscounter==10){removebonus=true;bonuscounter=-1; audio.stop();audio.setCurrent("game");
            }
            else{bonuscounter++;}
        }
        if(highScoreSound!=-1){
            if(highScoreSound==5){
            	highScoreSound=-1; audio.stop();
            }
            else{highScoreSound++;}
        }
    }
    
    public GameObject addToMoving() {
    	GameObject temp = null;
        try {
            temp = a.fallingObjectfactory((int) Math.round(800*Math.random())/moveSpeed);
        } catch (InstantiationException | IllegalAccessException e) {
        	cl.SevereLog("Error clownWorld : line 135",e);
            e.printStackTrace();
        }
        if(temp!=null){
            int x = (int) (12*Math.random());
            while(!lastXChecker(x)) {
                x = (int) (12*Math.random());
            }
            temp.setX((xes[x]));
            temp.setY(-80);
            lastxadd(x);
            return temp;
        }
		return temp;
    }
    
    public GameObject getTemp0() {
		return temp0;
	}
	private void lastxadd(int in){
        if(lastxindex<lastX.length){lastX[lastxindex]=in;lastxindex++;}
        else{lastxindex=1;lastX[0]=in;}
    }
    
    private boolean lastXChecker(int in){
        for (int x : lastX) {
            if (in == x) {
                return false;
            }
        }
        return true;
    }
    
    public void setTopStuff(List<GameObject> control) {
    	top=control.get(control.size()-1);
    	if(top!=null){colX=top.getX();colY=top.getY();}
    }
    
    public void move(List<GameObject> moving) {
    	Iterator<GameObject> iter = moving.iterator();
    	while (iter.hasNext()) {
			GameObject gameObject = (GameObject) iter.next();
			gameObject.setY(gameObject.getY() + moveSpeed);
		}
    }
    
    public void fixControlY(List<GameObject> control, int height) {
    	int i = 0;
    	Iterator<GameObject> iter = control.iterator();
    	while (iter.hasNext()) {
			GameObject gameObject = (GameObject) iter.next();
			gameObject.setY(height-120-i*55);
			i++;
		}
    }
    
    public boolean checkIntersection(List<GameObject> control, List<GameObject> moving) {
    	if(top==null){colX=control.get(0).getX();colY=control.get(0).getY();}
    	
    	Iterator<GameObject> iter = moving.iterator();
    	while (iter.hasNext()) {
			GameObject gameObject = (GameObject) iter.next();
			if((intersect(gameObject))) {
                temppp = gameObject;
                new Thread(new Runnable() {
                    public void run() {
                        if(((fallingObject)temppp).getSerial()==1){
                            exp.setY(temppp.getY());
                            exp.setX(temppp.getX());
                            playAnimationSound("bomb");
                            moving.add(0,exp);
                            explosionCounter=0;
                        }
                        else if(((fallingObject)temppp).getSerial()==0){
                            bonus.setY(temppp.getY()-80);
                            bonus.setX(temppp.getX()+40);
                            playAnimationSound("bonus");
                            bonuscounter=0;
                            moving.add(0,bonus);
                        }
                    }
                }).start();
                temp0 = gameObject;
                gameObject.setY(colY-55);
                top = gameObject;
                colX=top.getX();colY=top.getY();
                return true;
            }
		}
    	return false;
	}
    
    private void playAnimationSound(String name) {
    	try {
			audio.play(name, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private boolean intersect(GameObject o2){
        int delta = 75;
        if(o2 instanceof bonusObj||o2 instanceof explosion){return false;}
        if(((fallingObject)o2).getSerial()==-1)return false;
        return (Math.abs(colX - o2.getX()) <= delta/2) && ((colY - o2.getY()) <= delta && ((colY - o2.getY()) > delta/2));
    }
    
    public ArrayList<fallingObject> addIntersected(List<GameObject> control) {
    	control.add(temp0);
        ArrayList<fallingObject> temp1=new ArrayList<fallingObject>();
        for(int i=1;i<control.size();i++){
            temp1.add((fallingObject) control.get(i));
        }
        return temp1;
    }
    
    public boolean isHs() {
		return hs;
	}
    
    public LevelContext getLvlContext() {
		return lvlContext;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void checkAnimationRemoval(List<GameObject> moving, List<GameObject> control) {
    	if(removeexpflag){((explosion)exp).setVisible(false);exp=new explosion(0,0);removeexpflag=false;}
        if(removebonus){((bonusObj)bonus).setVisible(false);bonus=new bonusObj(0,0);removebonus=false;}
    }
    
    public int  getObserverResult(ArrayList<fallingObject> temp1) {
    	ob=new ObSubject(1,0,10);
        obsr=new CircusObserver();
        ((CircusObserver) obsr).MyObserver(ob);
        ob.notifyObs(temp1);
        return obsr.getUpdates();
	}
    
    
    
    public void save(){
        try {
            File outputFile = new File("highscore.txt");
            PrintWriter sv = new PrintWriter(outputFile);
            sv.write(Integer.toString(score));
            sv.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void load(){
        FileInputStream f= null;
        try {
            f = new FileInputStream("highscore.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader save=new BufferedReader(new InputStreamReader(f));
        String rd=null;
        try {
            rd=save.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(rd==null){
            highScore=0;
        }
        else{
            highScore=Integer.parseInt(rd);
        }
    }
    
    public void checkForHighScore() {
    	if(score>highScore){
            highScore=score;
            hs=true;
            highScoreFlag=highScoreFlag+1;
            if (highScoreFlag <= 1) {
                try {
                    audio.play("highscore", false);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                highScoreSound = 0;
            }
        }else {
        	hs=true;
        }

    }
    
    public void updateLevel() {
    	lvlContext.setLevel(score);
        if(moveSpeed != lvlContext.getLevel().getSpeed()) {
        	moveSpeed = lvlContext.getLevel().getSpeed();
        	cl.WarningLog("Level changed");
        }
	}
	
}
