package eg.edu.alexu.csd.oop.circusPlates;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

@SuppressWarnings("restriction")
public class AudioFactory {

	private static AudioFactory audios;
	private Map<String, MediaPlayer> themes;
	private MediaPlayer current;
	JFXPanel panel = new JFXPanel();
	
	private AudioFactory() {
		themes = new HashMap<String, MediaPlayer>();
	}
	
	public static AudioFactory getInstance() {
		return (audios == null) ? audios = new AudioFactory() : audios;
	}

	public void play(String themeName, boolean continuous) throws FileNotFoundException, IOException, URISyntaxException {
		MediaPlayer mp = themes.get(themeName);
		if(mp == null) {
			Media md = new Media(AudioFactory.class.getResource("/res/Themes/"+ themeName + ".mp3").toURI().toString());
			mp = new MediaPlayer(md);
			themes.put(themeName, mp);
		}
		current = mp;
		current.setVolume(0.3);
		current.setAutoPlay(true);
		if(continuous) {
			current.setCycleCount(MediaPlayer.INDEFINITE);
		}
		current.play();
	}
	
	public void stop() {
		current.stop();
	}
	
	public void setCurrent(String current) {
		this.current.stop();
		this.current = themes.get(current);
	}
		
}
