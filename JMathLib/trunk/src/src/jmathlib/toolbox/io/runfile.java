package jmathlib.toolbox.io;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.interpreter.GlobalValues;

import java.io.*;

/**An external function for loading and running m-files  (script-files)      *
 * This function is only for script-files not for function-files             */
public class runfile extends ExternalFunction
{
	/** Check that the operand is a string then open the file                *
	 *  referenced.                                                          *
         * @param operands[0] string which specifies the function to load    */
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		String answerString="";
		String lineFile="";
		String line=" ";


		// One operand of type CharToken (e.g. runfile("test") )
        if (getNArgIn(operands) != 1)
			throwMathLibException("RunFile: number of arguments != 1");

		if (!(operands[0] instanceof CharToken)) 
        	throwMathLibException("RunFile: argument must be a string");
		
		String fileName = ((CharToken)operands[0]).toString();
			
		// If the filename doesn't have an extension add the default 
		//     extension of .m
		if(fileName.indexOf(".m") == -1)	fileName += ".m";
			

		File scriptFile = new File(globals.getWorkingDirectory(), fileName);

		if(!scriptFile.exists()) return null;

		ErrorLogger.debugLine("loading >"+fileName+"<");

		try 
		{			
			// load file 
			BufferedReader inReader = new BufferedReader( new FileReader(fileName));
			while ( line != null)
			{
				line= inReader.readLine();	    	
				lineFile += line;
			}
			inReader.close();					

			//execute the file and store the answer
			answerString = globals.getInterpreter().executeExpression(lineFile);		

		}
		catch (Exception e)
		{
			ErrorLogger.debugLine("RunFile: load function exception");
		}		    

		
		return null;
	}
}

/*
@GROUP
IO
@SYNTAX
runfile("filename")
@DOC
Runs the script file specified by filename.
@NOTE
This is used to run script files, not function files.
@EXAMPLES
<programlisting>
runfile("script.m");
</programlisting>
@SEE
dir, cd, systemcommand
*/

