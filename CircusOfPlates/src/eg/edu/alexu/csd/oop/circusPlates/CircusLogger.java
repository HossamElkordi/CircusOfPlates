package eg.edu.alexu.csd.oop.circusPlates;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CircusLogger {

	private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private ConsoleHandler ch ;
	private FileHandler fh ;
	private File MyLog ;
	
	
	public CircusLogger() {
		LogManager.getLogManager().reset();
		log.setLevel(Level.INFO);
		ch = new ConsoleHandler();
		ch.setLevel(Level.FINE);
		log.addHandler(ch);
		try {
			MakeDirectory();
			fh = new FileHandler("MyLogs"+System.getProperty("file.separator")+"MyCircusLogger%u.log",true);
			fh.setFormatter(new SimpleFormatter());
			fh.setLevel(Level.WARNING);
			log.addHandler(fh);
		} catch (SecurityException e) {
			log.log(Level.SEVERE,"logger File read error",e);
			e.printStackTrace();
		} catch (IOException e) {
			log.log(Level.SEVERE,"logger File read error",e);
			e.printStackTrace();
		}
	}
	private void MakeDirectory() {
		MyLog = new File("MyLogs");
		if(!MyLog.exists()) {
			MyLog.mkdirs();
		}
	}
	
	//Only write to the logger
	public void InfoLog(String s) {
		log.info(s);
	}
	public void FineLog(String s) {
		log.fine(s);
	}
	public void WarningLog(String s) {
		log.warning(s);
	}
	public void SevereLog(String s) {
		log.severe(s);
	}
	//Write to logger and add an exception , Only applicable for warning and severe logs
	public void WarningLog(String s,Exception e) {
		log.log(Level.WARNING,s,e);
	}
	public void SevereLog(String s , Exception e) {
		log.log(Level.SEVERE,s,e);
	}
	
}
