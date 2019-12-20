package eg.edu.alexu.csd.oop.objects;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import eg.edu.alexu.csd.oop.game.GameObject;


public class DynamicLoadObjects {
	
	private static DynamicLoadObjects object;
	
//	private final URL jarsPath = DynamicLoadObjects.class.getResource("/res/Jars");
	
	private final String jarsPath = "src/res" + System.getProperty("file.separator") + "Jars";
	
	private List<Class<GameObject>> loadedCls;

	private DynamicLoadObjects() {
		
		loadedCls = new ArrayList<Class<GameObject>>();
		
		File jarsDir = new File(jarsPath);
		File[] paths = jarsDir.listFiles();
		for (int i = 0; i < paths.length; i++) {
			try {
				if(paths[i].getAbsolutePath().toLowerCase().contains(".jar")) {
					getClassesFromJar(paths[i]);
				}
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public List<Class<GameObject>> getLoadedCls() {
		return loadedCls;
	}

	public static DynamicLoadObjects getInstance() {
		return (object == null) ? object = new DynamicLoadObjects() : object;
	}
	
	@SuppressWarnings({ "unchecked", "resource" })
	private void getClassesFromJar(File jarPath) throws IOException, ClassNotFoundException {
		JarFile jarFile = new JarFile(jarPath);
		Enumeration<JarEntry> e = jarFile.entries();

		URL[] urls = { new URL("jar:file:" + jarPath+"!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);

		while (e.hasMoreElements()) {
		    JarEntry je = e.nextElement();
		    if(je.isDirectory() || !je.getName().endsWith(".class")){
		        continue;
		    }
		    String className = je.getName().substring(0,je.getName().length()-6);
		    className = className.replace('/', '.');
		    this.loadedCls.add((Class<GameObject>) cl.loadClass(className));
		}
	}
	
}
