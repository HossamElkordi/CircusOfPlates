package eg.edu.alexu.csd.oop.circusPlates;

import javax.swing.JButton;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import eg.edu.alexu.csd.oop.worlds.clownWorld;

public class CircusFacade {

	private final String FIRE_PATH  =  "CircusPlates"+ System.getProperty("file.separator") + "res" +System.getProperty("file.separator") + "Images"+ System.getProperty("file.separator")  + "fireCircusImg.png";
	private final String NIGHT_PATH = "CircusPlates"+ System.getProperty("file.separator")+"res" +System.getProperty("file.separator") + "Images"+ System.getProperty("file.separator") + "nightCircusImg.png";
	private final String ICE_PATH   = "CircusPlates"+ System.getProperty("file.separator")+"res" +System.getProperty("file.separator") + "Images"+ System.getProperty("file.separator") + "iceCircusImg.png";
	
	private static GameController controller;
	
	public void chooseCircus(JButton button) {
		switch(button.getName()) {
			case "fire" : controller = GameEngine.start("Circus Plates", new clownWorld(1280, 687, FIRE_PATH)); return;
			case "ice" : controller = GameEngine.start("Circus Plates", new clownWorld(1280, 687, ICE_PATH)); return;
			case "night" : 	controller = GameEngine.start("Circus Plates", new clownWorld(1280, 687, NIGHT_PATH)); return;
		}
	}

	public static GameController getController() {
		return controller;
	}
	public static void setController(GameController control) {
		controller = control;
	}
	
}
