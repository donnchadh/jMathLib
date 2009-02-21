package jmathlib.plugins;

import jmathlib.core.interpreter.*;
import jmathlib.core.interfaces.JMathLibOutput;



/**Class containing the extensions of jmathlib*/
public class PluginsManager 
{
    
    // loaded plugins
    private Plugin[] plugins;

    jmathlib.core.interpreter.Interpreter interpreter;
    
    VariableList globals;
    
    JMathLibOutput outputPanel;
     
    public PluginsManager()
    {

        //String path = System.getProperty("java.class.path");
        //System.out.println("*************** "+path);
        //path = "..\\Source\\MathLib\\Tools\\dynjava\\dynamicjava.jar;." ;
        //System.setProperty("java.class.path", path);
        //System.out.println("*************** "+System.getProperty("java.class.path"));
    
    }

    public void addPlugin(String pluginName)
    {
        plugins    = new Plugin[1];

        ClassLoader cLoader = this.getClass().getClassLoader();
        if (cLoader != null) System.out.println("PluginsManager: cLoader != null");
        else                 System.out.println("PluginsManager: cLoader == null");
	    
        try
        {
            Class  extFunctionClass = cLoader.loadClass("MathLib/Plugins/"+pluginName);
            if (extFunctionClass != null) System.out.println("PluginsManager: loadclass != null");
	    	
            Object funcObj          = extFunctionClass.newInstance();
            if (funcObj != null) System.out.println("PluginsManager: funcObj != null");
	    	
            plugins[0] = (Plugin)funcObj;
            plugins[0].setPluginsManager(this);
            plugins[0].init();
        }
		catch(Exception readError)
		{
            System.out.println("PluginsManager: error loading ");
            ErrorLogger.displayStackTrace("");
        }   
        
        //plugins[0] = _plugin;

                  
    }
    
    public Plugin getPlugin(String _name)
    {
        if (plugins == null)
            return null;
            
        if (plugins.length == 0)
            return null;
            
        return plugins[0];
    }


    public void setInterpreter(jmathlib.core.interpreter.Interpreter _interpreter )
    {
        interpreter = _interpreter;
    }
    
    public jmathlib.core.interpreter.Interpreter getInterpreter()
    {
        return interpreter;
    }

    public void setGlobalVariables(VariableList _globals)
    {
        globals = _globals;
    }

    public VariableList getGlobalVariables()
    {
        return globals;
    }

       
}
