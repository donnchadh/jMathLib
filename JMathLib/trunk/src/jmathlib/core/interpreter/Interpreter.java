/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   
 * (c) 2002-2009   
 */
 
 package jmathlib.core.interpreter;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.interfaces.MathLibOutput;

/**This is the main interface for the program. Any interface to the MathLib program would access
it through the functions exposed by this class.*/
public class Interpreter 
{
    /**Is the class being called from an application or an applet*/
    boolean runningStandalone;

    /**panel used for displaying text*/
    private MathLibOutput outputPanel;
    
    /**global pointers and values */
    public GlobalValues globals = null;
    
    /**for testing purposes additional throwing of errors can be enables */
    public boolean throwErrorsB = false;

    /**
     * Constructs the interpreter and sets the constants
     * @param _runningStandalone = true if this is being used from an application
     */	
    public Interpreter(boolean _runningStandalone)
    {
    	// indicator if this is a stand alone application or
        // if JMathLib is running as an applet or servlet or ... without
        // direct access to the file system
        runningStandalone = _runningStandalone;
        
    	// initialize global pointers, this pointer will be passed to
    	//  all expressions for access to function manager, variable lists, contexts,...
    	globals = new GlobalValues(this, runningStandalone);

    	// set output panel to NULL
    	// ?? needed? outputPanel is initialized with NULl anyway (see above)
    	outputPanel = null;

	    // read preferences from a file on the disc or on the web
	    globals.loadPropertiesFromFile();
        
    }
    
    /**
     * sets the panel to write any text to
     * @param _outputPanel = the panel to write to, must implement the
     *         MathLibOutput interface
     */
    public void setOutputPanel(MathLibOutput _outputPanel)
    {
	    outputPanel = _outputPanel;
    }

    /**
     * returns the panel to write any text to
     * @return outputPanel = the panel to write to
     */
    public MathLibOutput getOutputPanel()
    {
	    return outputPanel;
    }

    /**
     * displays a string to the outputPanel
     *  @param text = the text to display
     */
    public void displayText(String text)
    {
	    if(outputPanel != null)
	        outputPanel.displayText(text);
    }
	
    /**
     * saves the variable list
     */
    public void save() 
    {
	    if(runningStandalone)
	    {
	        executeExpression("finish");
	    
	        // store current properties to file
            globals.storePropertiesToFile();
        }
    }
	
    /**execute a single line.
     * @param expression = the line to execute
     * @return the result as a String
     */
    public String executeExpression(String expression)
    {    	
        String answer = "";
        Parser p = new Parser();

        // if required rehash m-files
        if(runningStandalone)
            globals.getFunctionManager().checkAndRehashTimeStamps();

        try
        {
	        // separate expression into tokens and return tree of expressions
            OperandToken expressionTree = p.parseExpression(expression);

	        // open a tree to show the expression-tree for a parsed command
	        //MathLib.Tools.TreeAnalyser.TreeAnalyser treeAnalyser = new MathLib.Tools.TreeAnalyser.TreeAnalyser( expressionTree );

	        // evaluate tree of expressions
            OperandToken answerToken = null;
            if (expressionTree!=null)
            {
                answerToken = expressionTree.evaluate(null, globals);
            }
			
            //getVariables().listVariables();
        }
        catch(JMathLibException e)
        {
            answer = e.getMessage();
            
            //display currently parse line of code to display
            displayText("??? "+p.getScannedLineOfCode());
            
            //log error information
            ErrorLogger.debugLine("??? "+p.getScannedLineOfCode());
            ErrorLogger.debugLine(answer);
        	
            // save last error to special variable
            Variable var = globals.createVariable("lasterror");
	        var.assign(new CharToken(answer));
            
	        // rethrow erros if enabled
            if (throwErrorsB) throw(e);
        }
        catch(java.lang.Throwable error)
        {
            answer = error.getClass().toString() + " : " + error.getMessage();
			
            // print stack trace: will show the line and file where the error occured
            error.printStackTrace();
            
            if(runningStandalone)
            {
            	ErrorLogger.debugLine( answer );
            }

            // save last error to special variable
            Variable var = globals.createVariable("lasterror");
            var.assign(new CharToken(answer));	
        }

        ErrorLogger.debugLine("Interpreter: done");
	    
        return answer;
    }

    /**get the real part of a scalar variable 
     * @param name = name of the scalar variable
     * @return numerical value of the variable 
     */
    public double getScalarValueRe(String name)
    {
	    // get variable from variable list		 
        OperandToken variableData = globals.getVariable(name).getData(); 
				    	
        // check if variable is already set
        if (variableData == null) return 0.0;
        
        // check if data is a DoubleNumberToken
        if (!(variableData instanceof DoubleNumberToken)) return 0.0;
        
        // cast to number token
        DoubleNumberToken number = (DoubleNumberToken)(variableData.clone());
                
        if (number.isScalar())
        	return number.getValueRe();
        else
        	return 0.0;
    }

    /**get the imaginary part of a scalar variable 
     * @param name = name of the scalar variable
     * @return numerical value of the variable 
     */
    public double getScalarValueIm(String name)
    {
		// get variable from variable list		 
        OperandToken variableData = globals.getVariable(name).getData(); 
        		    	
        // check if variable is already set
        if (variableData == null) return 0.0;
        
        // check if data is a DoubleNumberToken
        if (!(variableData instanceof DoubleNumberToken)) return 0.0;
        
        // cast to number token
        DoubleNumberToken number = (DoubleNumberToken)(variableData.clone());
                
        if (number.isScalar())
        	return number.getValueIm();
        else
        	return 0.0;
    }

    /**
     * 
     * @param name
     * @return
     */
    public boolean getScalarValueBoolean(String name)
    {
        // get variable from variable list       
        OperandToken variableData = globals.getVariable(name).getData(); 
                        
        // check if variable is already set
        if (variableData == null) return false;
        
        // check if data is a LogicalToken
        if (!(variableData instanceof LogicalToken)) return false;
        
        // cast to number token
        LogicalToken l = (LogicalToken)(variableData.clone());
                
        if (l.isScalar())
            return l.getValue(0);
        else
            return false;
    }

    /**get the real values of a an array 
     * @param name = name of the array
     * @return numerical value of the array 
     */
    public double[][] getArrayValueRe(String name)
    {
        // get variable from variable list		 
        OperandToken variableData = globals.getVariable(name).getData(); 
				    	
        // check if variable is already set
        if (variableData == null) return null;
        
        // check if data is a DoubleNumberToken
        if (!(variableData instanceof DoubleNumberToken)) return null;
        
        // cast to number token
        DoubleNumberToken number = (DoubleNumberToken)(variableData.clone());
                
       	return number.getReValues();
    }
    
    /**get the imaginary values of a an array 
     * @param name = name of the array
     * @return numerical value of the array 
     */
    public double[][] getArrayValueIm(String name)
	{
        // get variable from variable list		 
        OperandToken variableData = globals.getVariable(name).getData(); 
				    	
        // check if variable is already set
        if (variableData == null) return null;
        
        // check if data is a DoubleNumberToken
        if (!(variableData instanceof DoubleNumberToken)) return null;
        
        // cast to number token
        DoubleNumberToken number = (DoubleNumberToken)(variableData.clone());
                
       	return number.getValuesIm();
    }

    /**
     * 
     * @param name
     * @return
     */
    public boolean[][] getArrayValueBoolean(String name)
    {
        // get variable from variable list       
        OperandToken variableData = globals.getVariable(name).getData(); 
                        
        // check if variable is already set
        if (variableData == null) return null;
        
        // check if data is a DoubleNumberToken
        if (!(variableData instanceof LogicalToken)) return null;
        
        // cast to logical token
        LogicalToken l = (LogicalToken)(variableData.clone());
                
        return l.getValues();
    }

    /** store a scalar variable in jmathlib's workspace
     * @param name    = name of the scalar
     * @param valueRe = real value of the scalar
     * @param valueIM = imaginary value of the scalar
     */
    public void setScalar(String name, double valueRe, double valueIm)
    {
        // Create variable. In case variable is already created it will
        // return the current variable
        Variable answervar =  globals.createVariable(name);

        // assign value to variable
        answervar.assign(new DoubleNumberToken(valueRe, valueIm));
    }
    
    /** Store an array variable in jmathlib's workspace
     * @param name    = name of the array
     * @param valueRe = real values of the array
     * @param valueIM = imaginary values of the array
     */
    public void setArray(String name, double[][] valueRe, double[][] valueIm)
    {
        // Create variable. In case variable is already created it will
        // return the current variable
        Variable answervar = globals.createVariable(name);

        // assign value to variable
        answervar.assign(new DoubleNumberToken(valueRe, valueIm));
    }

    /** Return the result of the last calculation
     * @return a string containing the last result
     */
    public String getResult()
    {
        // get variable from variable list		 
        OperandToken variableData = globals.getVariable("ans").getData(); 
				    	
        // check if variable is already set
        if (variableData == null) return "";

        return variableData.toString();               
    }

    /** Return the result of the last calculation
     * @return a string containing the last result
     */
    public String getString(String name)
    {
        // get variable from variable list      
        OperandToken variableData = globals.getVariable(name).getData(); 
                        
        // check if variable is already set
        if (variableData == null) return "";
        
        // check if data is a CharToken
        if (!(variableData instanceof CharToken)) return "";
        
        // return string
        return ((CharToken)(variableData.clone())).getValue();
                
    }

}
