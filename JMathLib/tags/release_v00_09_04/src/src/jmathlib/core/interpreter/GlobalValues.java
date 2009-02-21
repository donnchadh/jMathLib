/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   
 * (c) 2002-2009   
 */

package jmathlib.core.interpreter;

import java.io.*;
import java.util.Locale;
import java.util.Properties;
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
    private ContextList contextList;
    
    /** flag to indicate if JMathLib is running standalone or as applet,servlet */
    private boolean runningStandalone = false;
    
    /**Object to control function usage*/
    private jmathlib.core.functions.FunctionManager functionManager;

    /**A pointer to the interpreter itself*/
    private Interpreter interpreter;
    
    /**Object to control graphical output*/
    private jmathlib.core.graphics.GraphicsManager graphicsManager;  

    /**Object to control plugins */
    private jmathlib.plugins.PluginsManager pluginsManager;     

    /**stores the number format for displaying the number*/
    private NumberFormat numFormat = new DecimalFormat("0.0000", new DecimalFormatSymbols(Locale.ENGLISH));

    /** global properties */
    private static Properties props = new Properties();  
    
    /**Initialises the global values
     * @param _interpreter = the Interpreter object
     * @param _runningStandalone = true if this was run from an application
     */
    public GlobalValues(Interpreter _interpreter, boolean _runningStandalone)
    {
        
        // flag for stand alone or applet,servlet,...
        runningStandalone = _runningStandalone;
        
        // the list of contexts
        contextList     = new ContextList();

        // the function manager for loading m-files class-files
        functionManager = new jmathlib.core.functions.FunctionManager(_runningStandalone);
        
        // the graphics manager for plotting functions
        graphicsManager = new jmathlib.core.graphics.GraphicsManager();

        //set up a pointer to the interpreter object
        interpreter = _interpreter;

        // set up plugins manager
        pluginsManager = new jmathlib.plugins.PluginsManager();
        pluginsManager.setInterpreter(interpreter);
        pluginsManager.setGlobalVariables(getGlobalVariables());
         
    }   

    /**
     * @return the current variable list
     */
    public VariableList getLocalVariables()
    {
        return contextList.getLocalVariables();
    }

    /**
     * @return the global variable list
     */
    public VariableList getGlobalVariables()
    {
        return contextList.getGlobalVariables();
    }

    /**
     * @param
     * @return the a variable from local or global workspace
     */
    public Variable getVariable(String name)
    {
        return contextList.getVariable(name);
    }

    /** 
     * Create a variable in the local or global workspace
     * @param creates a new variable
     * @return returns the handle to that variable
     */
    public Variable createVariable(String name)
    {
        return contextList.createVariable(name);
    }

    /** 
     * Create a variable in the local or global workspace
     * @param name of the variable
     * @param data of the variable
     */
    public void setVariable(String name, OperandToken value)
    {
        contextList.setVariable(name, value);
    }

    /**
     * Change the current context to point to the new Variable List
     * @param _Variables = the new list of Variables to use
     */
    public void createContext(VariableList _variables)
    {
        contextList.createContext(_variables);
    }

    /**
     * Return to the previous variable list
     */
    public void popContext()
    {
        contextList.popContext();
    }
    
    /**
     * Allow access to the context list
     * @return the context list
     */    
    public ContextList getContextList()
    {
        return contextList;
    }

    /**
     * Returns the interpreter object
     * @return pointer to Interpreter
     */
    public Interpreter getInterpreter()
    {
        return interpreter;
    }

    /**
     * Returns a handle to the function manager
     * @return the function manager
     */
    public jmathlib.core.functions.FunctionManager getFunctionManager()
    {
        return functionManager;
    }

    /**
     * Returns a handle to the graphics manager
     * @return handle to graphics manager
     */
    public jmathlib.core.graphics.GraphicsManager getGraphicsManager()
    {
        return graphicsManager;
    }

    /**
     * Returns a handle to the plugin manager
     * @return handle to plugins manager
     */
    public jmathlib.plugins.PluginsManager getPluginsManager()
    {
        return pluginsManager;
    }

    /** 
     * Returns the working directory
     * @return actual working directory 
     */
    public File getWorkingDirectory()
    {
        return functionManager.getWorkingDirectory();
    }

    /** 
     * Sets the working directory
     * @param set working directory 
     */
    public void setWorkingDirectory(File _workingDir)
    {
        functionManager.setWorkingDirectory(_workingDir);
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

    /**
     * load JMathLib properties from a file or in applet version
     * from the web server.
     */
    public void loadPropertiesFromFile()
    {
        // load global properties from disc 
        try 
        {
             // check if JMathLib is running as applet or application
             if (!runningStandalone)
             {
                 // running as applet
                 //System.out.println("properties applet " + getWorkingDirectory());
                 //URL url = new URL(applet.getCodeBase(), "JMathLib.properties");
                 InputStream in = GlobalValues.class.getResourceAsStream("../../../JMathLib.properties");
                 props.load( in); //url.openStream() );
             }
             else
             {
                 // running as application
                 File f = new File("JMathLib.properties");
                 
                 // check if property file exist in "working directory" or
                 //  if it is not available yet, then take the original one
                 //  from the bin directory
                 if (!f.exists())
                     f = new File("bin/JMathLib.properties");
                 
                 // load properties
                 //System.out.println("properties file "+f.getCanonicalPath().toString());
                 props.load(new FileInputStream(f));
             }
        }
        catch (Exception e)
        {
            System.out.println("Properties global error");    
        }

        // display properties
        //Enumeration propnames = props.propertyNames();
        //while (globalPropnames.hasMoreElements())
        //{
        //    String propname = (String)globalPropnames.nextElement();
        //    System.out.println("Property: "+propname+" = "+globalProps.getProperty(propname));
        //}
    }

    /**
     * store properties back to a file on disc. In applet version
     * do not store, since there is no disc
     */
    public void storePropertiesToFile()
    {
        // do not try to store properties is an applet
        if (!runningStandalone)
            return;
        
        // store properties back to file
        try
        {
            File f = new File("JMathLib.properties");
            //System.out.println("properties file "+f.getCanonicalPath().toString());

            props.store(new FileOutputStream(f.getCanonicalPath()), 
                             "JMathLib property file" );
        }
        catch (Exception e)
        {
            System.out.println("GlobalVAues: properties store error");
        }
    }
    
    /**
     * Returns one of JMathLib's properties
     * @param property
     * @return string value of the requested property
     */
    public String getProperty(String property)
    {
        return props.getProperty(property);
    }

    /**
     * Sets a property of JMathLib
     * @param name of property
     * @param value of property 
     */
    public void setProperty(String property, String value)
    {
        props.setProperty(property, value);
    }

}
