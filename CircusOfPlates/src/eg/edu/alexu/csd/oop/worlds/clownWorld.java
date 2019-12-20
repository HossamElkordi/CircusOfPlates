package eg.edu.alexu.csd.oop.worlds;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import eg.edu.alexu.csd.oop.circusPlates.AudioFactory;
import eg.edu.alexu.csd.oop.circusPlates.CircusFacade;
import eg.edu.alexu.csd.oop.circusPlates.CircusLogger;
import eg.edu.alexu.csd.oop.circusPlates.PausePanel;
import eg.edu.alexu.csd.oop.circusPlates.UserInterface;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.memento.CareTaker;
import eg.edu.alexu.csd.oop.memento.memento;
import eg.edu.alexu.csd.oop.objects.BackgroundObject;
import eg.edu.alexu.csd.oop.objects.clownObject;
import eg.edu.alexu.csd.oop.objects.fallingObject;
import eg.edu.alexu.csd.oop.strategy.Bomb;
import eg.edu.alexu.csd.oop.strategy.Bonus;
import eg.edu.alexu.csd.oop.strategy.Strategy;
import eg.edu.alexu.csd.oop.strategy.sameThree;

public class clownWorld implements World{

	private final int width;
    private final int height;
    private  List<GameObject> constant = new LinkedList<GameObject>();
    private  List<GameObject> moving = new LinkedList<GameObject>();
    private  List<GameObject> control = new LinkedList<GameObject>();
    private final List<JComponent> components = new ArrayList<JComponent>();
    private PausePanel pause;
    boolean replayflag = false;
    private AudioFactory audio;
    private int replayindex=0;
    private CareTaker tem = CareTaker.getInstance();
    private int counter=0;
    private Strategy s;
    private CircusLogger cl ;
    private WorldController controller;
    
    public clownWorld(int screenWidth, int screenHeight,URL bgPath) {
    	try {
			controller = new WorldController();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
    	cl = new CircusLogger();
    	audio = AudioFactory.getInstance();
        constant.add(new BackgroundObject(0,0,bgPath));//hena ba7ot el background fel constant
        setComponents();
        controller.setScore(0);
        width = screenWidth;
        height = screenHeight;
        control.add(new clownObject(screenWidth/2, screenHeight-120));
        controller.setMemento(constant, control, moving);
        try {
			audio.play("game", true);
			controller.load();
		} catch (IOException | URISyntaxException e) {
			cl.SevereLog("game audio file not working",e);
			e.printStackTrace();
		}
    }
    
    public boolean refresh() {
    	controller.removeFromDown(moving, height);
    	controller.checkAnimationAndSoundEffect();
    	
    	if(!replayflag) {
    		GameObject o = controller.addToMoving();
    		if(o != null) {
    			moving.add(o);
    		}
    		controller.setTopStuff(control);
    		controller.move(moving);
    		controller.fixControlY(control, height);
    		if(controller.checkIntersection(control, moving)) {
    			ArrayList<fallingObject> temp1 = controller.addIntersected(control);
    			int up = controller.getObserverResult(temp1);
    			s = new Bomb();//just to avoid the error that it might not be initialized
                int enter = 0;
                if(up == 1){
                  s = new Bomb();
                }
                else if(up == 2){
                  s = new Bonus();
                }
                else if(up == 3 || up == 4 || up == 5 || up == 6 || up == 7 || up == 8){
                  s = new sameThree();
                }
                else if (up == -1){
                  controller.save();
              	  playGameOver();
              	  pauseGame();
              	  pause.resumeBtn.setEnabled(false);
              	  pause.replayBtn.setEnabled(false);
                  return false;
                }
                else if (up==-2){
                	controller.setScore(controller.getScore() + 1);
                  if(controller.getScore() > controller.getHighScore()) {
                      controller.setHighScore(controller.getScore());
//                      controller.save();
                  }
                  playGameOver();
                  pauseGame();
                  pause.resumeBtn.setEnabled(false);
                  pause.replayBtn.setEnabled(false);
                  return false;
               }
               else{
                  enter=1;
               }
               if(enter==0) {

                  controller.setScore(s.changeScore(controller.getScore()));

                  controller.checkForHighScore();
                  
                  temp1 = s.removePlates(temp1);
                  int tmp = control.size();
                  for (int i = 1; i < tmp; i++) {
                      control.remove(1);
                  }
                  for (int i = 0; i < temp1.size(); i++) {
                      control.add(temp1.get(i));
                  }
                  controller.setTop(control.get(control.size()-1));
                  controller.setColX(controller.getTop().getX());
                  controller.setColY(controller.getTop().getY());
              }
              moving.remove(controller.getTemp0());
          }
    		
    		controller.updateLevel();
    		new Thread(new Runnable() {
              public void run() {
                  if(counter++==3){
                      controller.setMemento(constant, control, moving);
                      counter=0;
                  }
              }
          }).start();
    		
		}else if(replayflag){
          if(replayindex>=tem.getsize()){replayflag=false;}
          else replay();
        }
    	
    	controller.checkAnimationRemoval(moving, control);
    	
		return true;
    }
    
    public int getSpeed() {
        return 40;
    }

    public int getControlSpeed() {
        return 6;
    }

    public List<GameObject> getConstantObjects() {
        return constant;
    }

    @SuppressWarnings("unchecked")
	public List<GameObject> getMovableObjects() {
    	return (List<GameObject>) ((LinkedList<GameObject>)moving).clone();
    }

    public List<GameObject> getControlableObjects() {
        return control;
    }

    public List<JComponent> getComponents() {
        return components;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getStatus() {
    	if(control == null || control.size() == 0) {
    		return "Please Use Arrows To Move";
    	}
    	if(controller.isHs()) {
            return "Please Use Arrows To Move     |      Location = " + control.get(0).getX() + "," + control.get(0).getY() + "      |     Score = " + controller.getScore() +
                    "      |     " + controller.getLvlContext().getLevel().toString() + "    NEW HIGH SCORE!!!!!!!" ;    // update status
        }
    	else {
    	    return "Please Use Arrows To Move     |      Location = " + control.get(0).getX() + "," + control.get(0).getY() + "      |     Score = " + controller.getScore() +
                    "      |     " + controller.getLvlContext().getLevel().toString() ;
        }
    }
	
    private void setComponents() {
    	pause = new PausePanel(this);
        pause.setVisible(false);
        createBtns("Pause_BTN", 10);
        createBtns("Backward_BTN", 74);
        components.add(pause);
    }
    
    private void createBtns(String name, int x) {
    	JButton button = new JButton();
    	button.setBounds(x, 10, 54, 54);
    	if(name.toLowerCase().contains("pause")) {
    		button.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				pauseGame();
    			}
    		});
    	
    	}else if(name.toLowerCase().contains("backward")){
    		button.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
                    replayflag = true;
                    replayindex = 0;
    			}
    		});
    	}

    	Image img = null;
    	try {
			img = ImageIO.read(clownWorld.class.getResource("/res/Images/" + name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	button.setIcon(new ImageIcon(img.getScaledInstance(button.getWidth(), button.getHeight(), Image.SCALE_SMOOTH)));
    	components.add(button);
    }
    
    private void playGameOver() {
		audio.stop();
		try {
			audio.play("gameOver", false);
		} catch (IOException | URISyntaxException e) {
			cl.SevereLog("gameOver audio file not working",e);
			e.printStackTrace();
		}
	}
    
    private void pauseGame() {
		CircusFacade.getController().pause();
		pause.setVisible(true);
		for (int i = 0; i < components.size() - 1; i++) {
			components.get(i).setEnabled(false);
		}
		cl.InfoLog("Game paused");
	}

	private void replay(){
        memento x=tem.getMemento(replayindex++);
        if(x!=null) {
            this.moving = x.getMoving();
            this.constant = x.getConstant();
            this.control = x.getControl();
        }
        cl.InfoLog("replay : ON");
    }
}
