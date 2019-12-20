package eg.edu.alexu.csd.oop.circusPlates;

import java.net.URISyntaxException;
import java.util.List;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.objects.DynamicLoadObjects;

public class cratesFactory {

	private static cratesFactory cf;
    private List<Class<GameObject>> loadedCls;
    private DynamicLoadObjects dlo;
    
    private cratesFactory() throws URISyntaxException{
    	dlo = DynamicLoadObjects.getInstance();
    	loadedCls = dlo.getLoadedCls();
    }
    public static cratesFactory getInstance() throws URISyntaxException{
        if(cf==null){cf=new cratesFactory();}
        return cf;
    }
    public GameObject fallingObjectfactory(int in) throws InstantiationException, IllegalAccessException{
        if(in==0){return getClassBySimpleName("giftbox").newInstance();}
        else if(in==1){return getClassBySimpleName("tntBox").newInstance();}
        else if(in==2){return getClassBySimpleName("originalbox").newInstance();}
        else if(in==5){return getClassBySimpleName("redBox").newInstance();}
        else if(in==3){return getClassBySimpleName("blueBox").newInstance();}
        else if(in==4){return getClassBySimpleName("greenBox").newInstance();}
        else if(in==6){return getClassBySimpleName("pinkBox").newInstance();}
        else if(in==7){return getClassBySimpleName("turquoiseBox").newInstance();}
        
        else if(in==8){return getClassBySimpleName("originalBall").newInstance();}
        else if(in==9){return getClassBySimpleName("redBall").newInstance();}
        else if(in==10){return getClassBySimpleName("blueBall").newInstance();}
        else if(in==11){return getClassBySimpleName("greenBall").newInstance();}
        else if(in==12){return getClassBySimpleName("pinkBall").newInstance();}
        else if(in==13){return getClassBySimpleName("turquoiseBall").newInstance();}

        else return null;

    }
    
    private Class<GameObject> getClassBySimpleName(String name){
    	for(int i = 0; i < loadedCls.size(); i++) {
    		if(loadedCls.get(i).getSimpleName().equals(name)) {
    			return loadedCls.get(i);
    		}
    	}
		return null;
    }
	
}
