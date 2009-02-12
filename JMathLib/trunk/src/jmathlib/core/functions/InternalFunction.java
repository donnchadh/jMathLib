/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   
 * (c) 2005-2009   
 */
package jmathlib.core.functions;

import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;

/**The base class for all the internal function types*/
public class InternalFunction extends Function
{
	/**Default constructor - creates an internal function with a null name*/
	public InternalFunction()
	{
		name = "";
	}

	/**Creates an internal function with it's name set to _name
	 * @param _name = the name of the function
	 */
	public InternalFunction(String _name)
	{
		name = _name;
	}

	/**
	* Executes the internal function
	* @param operands - the array of parameters
	* @param pointer to global values
	* @return the result as an OperandToken
	*/	
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		return null;
	}
}