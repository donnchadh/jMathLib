package jmathlib.core.interpreter;

import java.io.*;

//import MathLib.Tokens.*;
import jmathlib.core.interpreter.Interpreter;

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

    /**The working directory */
    static transient private File workingDir;

    //static private boolean endFunction = false;

    /**store system flags*/
    static transient private Flags flags = null;

    /**Initialises the global values
       @param _interpreter = the Interpreter object
       @param _runningStandalone = true if this was run from an application*/
    public GlobalValues(Interpreter _interpreter, boolean _runningStandalone)
    {
        //variables = new VariableList();
        //globalVariables = variables;
        contextList = new ContextList();

        flags = new Flags();
        
        functionManager = new jmathlib.core.functions.FunctionManager(_runningStandalone, flags);
        
        graphicsManager = new jmathlib.core.graphics.GraphicsManager();

        //if(_runningStandalone)
        //    castClassLoader = new MathLib.Casts.CastClassLoader("." + File.separator, flags);
        //else
        //    castClassLoader = null;
            
        //set up a pointer to the interpreter object
        interpreter = _interpreter;

        // set working directory to actual directory
        workingDir = new File(".");     

        // set up plugins manager
        pluginsManager = new jmathlib.plugins.PluginsManager();
        pluginsManager.setInterpreter(interpreter);
        pluginsManager.setGlobalVariables(getGlobalVariables());
         
    }   

    /**Used to modify the variable list to point to the internal list used by a function
       @param _variables = the variable list to use*/
    //protected void setVariables(VariableList _variables)
    //{
    //    //variables = _variables;
    //}

    /**Resets the variable list to the global list after finishing processing a function*/
    //protected void resetVariables()
    //{
    //    //variables = globalVariables;
    //}

    /**@return the current variable list*/
    protected VariableList getVariables()
    {
        return contextList.getVariables();
    }

    /**@return the global variable list*/
    protected VariableList getGlobalVariables()
    {
        return contextList.getGlobalVariables();
    }

    /**@return the global variable list*/
    protected Variable getVariable(String name)
    {
        return contextList.getVariable(name);
    }

    /**Change the current context to point to the new Variable List
    @param _Variables = the new list of Variables to use*/
   //   protected void createContext(VariableList _variables, OperandToken _code)
   // {
   //     contextList.createContext(_variables, _code);
   // }

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
    
    //protected OperandToken executeCurrentContext()
    //{
    //    return contextList.executeCurrentContext();
    //}

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

    /**@return the CastClassLoader*/
    //protected MathLib.Casts.CastClassLoader getCastClassLoader()
    //{
    //    return castClassLoader;
    //}

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

    /**return the system flags*/
    static public Flags getFlags()
    {
        return flags;
    }
}
