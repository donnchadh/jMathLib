/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   
 * (c) 2005-2009   
 */
package jmathlib.core.interpreter;

import jmathlib.core.tokens.*;

import java.util.*;
import java.io.*;

/**Class used to store all the declared variables within a context*/
public class VariableList implements Cloneable, java.io.Serializable 
{
    /**The actual list of variables*/
    private HashMap variables;

    /**Sets up the ArrayList of variables*/
    public VariableList()
    {
        variables = new HashMap();
    }

    /**Create a duplicate of a VariableList
	@param parent = the previous variable context*/
    public VariableList(VariableList _variables)
    {
        variables = new HashMap();
        
        Iterator list = _variables.getIterator();
        
        while(list.hasNext())
        {
	        Map.Entry next = ((Map.Entry)list.next());
	        String name    = ((String)next.getKey());
	        Variable var   = ((Variable)next.getValue());
	        variables.put(name, var.clone());
        }
    }

    /**@return an iterator for the key/value pairs of the list*/
    public Iterator getIterator()
    {
        return variables.entrySet().iterator();
    }

    /**@return the number of variables*/
    public int getSize()
    {
        return variables.size();
    }

    /**Remove a variable from the list
       @param variable-string = the variable to remove*/
    public void remove(String name)
    {
        variables.remove(name);
    }

    /**remove all variables from the list*/
    public void clear()
    {
        variables = new HashMap();
    }

     /**@param name  = the name of the variable
	@param value = the value to set it to*/
    public void setVariable(String name, OperandToken value)
    {
        ((Variable)variables.get(name)).assign(value);
    }


     /**@param name = the name of the variable
	@return the variable represented with the name name*/
    public Variable getVariable(String name)
    {
        return ((Variable)variables.get(name));
    }

    /**Check wether or not a variable with the given name exists.
    @param name = the name of a variable */
    public boolean isVariable(String name)
    {
        return variables.containsKey(name);
    }


    /**Lists all the currently declared variables to the console and a log file*/
    public void listVariables()
    {
        ErrorLogger.debugLine("listing variables");
        Iterator iter = getIterator();
        Variable var;
        while(iter.hasNext())
        {
            Map.Entry next = ((Map.Entry)iter.next());
            var = ((Variable)next.getValue());
            ErrorLogger.debugLine(var.getName()); //toString());
        }
        ErrorLogger.debugLine("------------------------------");
    }
	
    /**creates a variable, if it doesn't already exist.
       it returns the created variable
       @param name = the name of the variable to create
       @return the variable with that name*/
    public Variable createVariable(String name)
    {
        Variable newVariable = null;
        if(!variables.containsKey(name))
        {
            newVariable = new Variable(name);
            variables.put(name, newVariable);
        }
        else
        {
            newVariable = ((Variable)variables.get(name));
        }
        
        
        return newVariable;
    }


    /**saves the list of variables
     @param fileName = the name of the file to save to*/
    public void saveVariables(String fileName)
    {
        try
        {    	
            //create streams
            FileOutputStream output = new FileOutputStream(fileName);
            
            //create object stream
            ObjectOutputStream objectOutput = new ObjectOutputStream(output);
            
            objectOutput.writeObject(variables);
            
            //close output objects	    	
            objectOutput.close();
            output.close();
        }
        catch(java.io.IOException except)
        {
            Errors.throwMathLibException("VariableList: IO exception");
            ErrorLogger.debugLine(except.getMessage());
            //except.printStackTrace();
        }
    }
	
    /**loads the list of variables
     @param fileName = the name of the file to load from*/
    public void loadVariables(String fileName)
    {        
        try
        {            
            FileInputStream input = new FileInputStream(fileName);
            
            ObjectInputStream objectInput   = new ObjectInputStream(input);
            
            try
            {
                variables = ((HashMap)objectInput.readObject());
            }
            catch(java.lang.ClassNotFoundException except)
            {
                Errors.throwMathLibException("VariableList: Class not found exception");
                
                //close input objects
                objectInput.close();
                input.close();	    	
            }
            catch(java.lang.ClassCastException except)
            {
                Errors.throwMathLibException("VariableList: Class cast exception");
                
                //close input objects
                objectInput.close();
                input.close();	    	
            }
            
            //close input objects
            objectInput.close();
            input.close();	    	
        }
        catch(java.io.IOException except)
        {
            Errors.throwMathLibException("VariableList: IO exception");
            ErrorLogger.debugLine(except.getMessage());
        }
    }
    
    /**Create a duplicate of this Variable List*/
    public Object clone()
    {
        return new VariableList(this);
    }   
}
