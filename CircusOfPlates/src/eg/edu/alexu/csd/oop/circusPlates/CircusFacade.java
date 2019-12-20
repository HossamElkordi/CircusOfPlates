package eg.edu.alexu.csd.oop.circusPlates;

import java.net.URL;

import javax.swing.JButton;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import eg.edu.alexu.csd.oop.worlds.clownWorld;

public class CircusFacade {

	private final URL FIRE_PATH  = CircusFacade.class.getResource("/res/Images/fireCircusImg.png");
	private final URL NIGHT_PATH = CircusFacade.class.getResource("/res/Images/nightCircusImg.png");
	private final URL ICE_PATH   = CircusFacade.class.getResource("/res/Images/iceCircusImg.png");
	
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
