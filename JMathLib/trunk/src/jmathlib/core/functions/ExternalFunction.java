/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   
 * (c) 2005-2009   
 */
package jmathlib.core.functions;

/**Base class for all external function classes*/
abstract public class ExternalFunction extends Function
{
    
    /**Index for real values within array*/
    protected static final int REAL = 0;
    
    /**Index for Imaginary values within array*/
    protected static final int IMAG = 1;

	/**Number of paramaters take by the function*/
	private int paramCount;	
	
	/**Default constructor - creates an external function with a null name*/
	public ExternalFunction()
	{
		name = "";
	}

	/**Creates an external function called _name
	@param _name = the name of the function*/
	public ExternalFunction(String _name)
	{
		name = _name;
	}

	/**@return the number of paramaters taken by the function*/
	public int getParamCount()
	{
		return paramCount;
	}

}
