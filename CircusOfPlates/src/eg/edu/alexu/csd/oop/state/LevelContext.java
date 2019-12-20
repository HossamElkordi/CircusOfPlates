package eg.edu.alexu.csd.oop.state;

public class LevelContext implements Cloneable{

	private Level level;
	private Level[] levels;
	private int currentIndex;
	private static LevelContext context;
	
	private LevelContext() {
		levels = new Level[4];
		levels[0] = new Level1();
		levels[1] = new Level2();
		levels[2] = new Level3();
		levels[3] = new Level4();

		
	}
	
	public static LevelContext getInstance() {
		if(context == null) {
			return new LevelContext();
		}else {
			return context;
		}
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(int score) {
		if(score < 8) {
			if(currentIndex == 0) {this.level = (Level1)levels[0];}
		}else if(score < 20) {
			if(currentIndex < 2) {this.level = (Level2)levels[1]; this.currentIndex = 1;}
		}else if(score < 30) {
			if(currentIndex < 3) {this.level = (Level3)levels[2]; this.currentIndex = 2;}
		}else {
			this.level = (Level4)levels[3]; this.currentIndex = 3;
		}
	}
	
	public LevelContext clone() {
		LevelContext newLvlContext = new LevelContext();
		if(this.level.getClass().getSimpleName().equals("Level1")) {
			newLvlContext.setLevel(2);
		}else if(this.level.getClass().getSimpleName().equals("Level2")) {
			newLvlContext.setLevel(7);
		}else if(this.level.getClass().getSimpleName().equals("Level3")) {
			newLvlContext.setLevel(15);
		}else {
			newLvlContext.setLevel(25);
		}
		return newLvlContext;
	}
	
}
