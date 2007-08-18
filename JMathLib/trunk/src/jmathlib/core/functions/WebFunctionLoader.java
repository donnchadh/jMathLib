package jmathlib.core.functions;

import java.util.Hashtable;
//import java.io.IOException;
//import java.io.File;
//import java.io.FileInputStream;
//import java.util.Vector;
import jmathlib.core.interpreter.*;
//import java.net.*;
//import java.security.*;
import java.applet.*;

/**Class to load any External functions used*/
public class WebFunctionLoader
{
	/**Root directory to load the class from*/
	//private String baseClassDir;

	/**Directory for the last loaded class*/
	public String classDir;	
	
	/**name of the last class loaded*/
	public String lastClassName;

	/**name of the last script-file (m-file) */
	public boolean mFileSwitch;

	/**Hashtable to store classes that have already been loaded*/
	private Hashtable loadedClasses;

    /**Pointer to the system flags*/
    //private Flags sysFlags;
    
    /**Pointer to applet context */
    //private Applet app;
    
	public WebFunctionLoader(Flags _sysFlags)
	{
        //super(urls);
		//baseClassDir = _classDir;
		
		loadedClasses = new Hashtable();
        
        //sysFlags = _sysFlags;

// no function yet

        
	}

	/**first checks the hashtable of already loaded classes
	@param fileName = the name of the function to load*/
	/*public boolean isClassLoaded(String fileName)
	{
		Class newClass = ((Class)loadedClasses.get(fileName.toUpperCase()));
		if (newClass != null) return true;
		else                  return false;

// no function yet

	}*/

    public void setApplet(Applet _app)
    {
        //app = _app;
    }

	public Class loadClass(String fileName) throws ClassNotFoundException
	{
		ErrorLogger.debugLine("web func loader loadClass "+ fileName);
		Class newClass = ((Class)loadedClasses.get(fileName.toUpperCase()));

		if(newClass != null)
		{
			return newClass;
		}

		return newClass;
// no function yet

	}

	//private String findClassOrMFile(String path, String fileName)
	//{
    //    String classDir = "nothing";
    // 	return classDir;
// no function yet

	//}
}
