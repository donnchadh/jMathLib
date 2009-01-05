package jmathlib.core.interpreter;

import java.io.*;
import java.util.Locale;
import java.applet.Applet;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

import jmathlib.core.interpreter.Interpreter;
import jmathlib.core.tokens.OperandToken;

/**This class contains the global variables, which are accessible throughout the program.
These include
System flags
The list of contexts
The interpreter object
The GraphicsManager
The working directory
These values are all private and should be access through the getter/setter functions*/
public class GlobalValues
{
    /**A list of contexts*/
    static transient private ContextList contextList;
    
    /**Object to control function usage*/
    static transient private jmathlib.core.functions.FunctionManager functionManager;

    /**Class Loader for loading classes for handling casting*/
    //static transient private MathLib.Casts.CastClassLoader castClassLoader; 

    /**A pointer to the interpreter itself*/
    static transient private Interpreter interpreter;
    
    /**Object to control graphical output*/
    static transient private jmathlib.core.graphics.GraphicsManager graphicsManager;  

    /**Object to control plugins */
    static transient private jmathlib.plugins.PluginsManager pluginsManager;     

    /**sets whether to write debug lines to console and log files*/
    static transient private boolean debug = true;
    
    /**stores the number format for displaying the number*/
    public transient static NumberFormat numFormat = new DecimalFormat("0.0000", new DecimalFormatSymbols(Locale.ENGLISH));

    /**Initialises the global values
       @param _interpreter = the Interpreter object
       @param _runningStandalone = true if this was run from an application*/
    public GlobalValues(Interpreter _interpreter, boolean _runningStandalone, Applet applet)
    {
        contextList     = new ContextList();

        functionManager = new jmathlib.core.functions.FunctionManager(_runningStandalone, applet);
        
        graphicsManager = new jmathlib.core.graphics.GraphicsManager();

        //if(_runningStandalone)
        //    castClassLoader = new MathLib.Casts.CastClassLoader("." + File.separator, flags);
        //else
        //    castClassLoader = null;
            
        //set up a pointer to the interpreter object
        interpreter = _interpreter;

        // set up plugins manager
        pluginsManager = new jmathlib.plugins.PluginsManager();
        pluginsManager.setInterpreter(interpreter);
        pluginsManager.setGlobalVariables(getGlobalVariables());
         
    }   

    /**@return the current variable list*/
    protected VariableList getLocalVariables()
    {
        return contextList.getLocalVariables();
    }

    /**@return the global variable list*/
    protected VariableList getGlobalVariables()
    {
        return contextList.getGlobalVariables();
    }

    /**@return the a variable from local or global workspace*/
    protected Variable getVariable(String name)
    {
        return contextList.getVariable(name);
    }

    /** create a variable in the local or global workspace*/
    protected Variable createVariable(String name)
    {
        return contextList.createVariable(name);
    }

    /** create a variable in the local or global workspace*/
    protected void setVariable(String name, OperandToken value)
    {
        contextList.setVariable(name, value);
    }

    /**Change the current context to point to the new Variable List
    @param _Variables = the new list of Variables to use*/
    protected void createContext(VariableList _variables)
    {
        contextList.createContext(_variables);
    }

    /**Return to the previous variable list*/
    protected void popContext()
    {
        contextList.popContext();
    }
    
    /**Allow access to the context list*/    
    protected ContextList getContextList()
    {
        return contextList;
    }

    /**@return the interpreter object*/
    protected Interpreter getInterpreter()
    {
        return interpreter;
    }

    /**@return the function manager*/
    protected jmathlib.core.functions.FunctionManager getFunctionManager()
    {
        return functionManager;
    }

    /**@return handle to graphics manager*/
    protected jmathlib.core.graphics.GraphicsManager getGraphicsManager()
    {
        return graphicsManager;
    }

    /**@return handle to plugins manager*/
    protected jmathlib.plugins.PluginsManager getPluginsManager()
    {
        return pluginsManager;
    }

    /** @return actual working directory */
    protected File getWorkingDirectory()
    {
        return functionManager.getWorkingDirectory();
    }

    /** @param set working directory */
    protected void setWorkingDirectory(File _workingDir)
    {
        functionManager.setWorkingDirectory(_workingDir);
    }

    /**@return the setting of the debug flag*/
    public boolean getDebug()
    {
        return debug;
    }
    
    /**sets the debug flag
    @param _debug = should debug information be displayed*/
    public void setDebug(boolean _debug)
    {
        debug= _debug;
    }
    
    /**
     * returns the number format for displaying numbers
     * @return format type
     */
    public NumberFormat getNumberFormat()
    {
        return numFormat;
    }

    /**
     * sets the number format for displaying numbers
     * @param _numFormat format type
     */
    public void setNumberFormat(NumberFormat _numFormat)
    {
        numFormat = _numFormat;
    }

}
