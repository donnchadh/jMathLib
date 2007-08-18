package jmathlib.core.interpreter;

import jmathlib.core.tokens.OperandToken; 
import jmathlib.core.tokens.Token; 

/**MathLib specific exception */
public class ControlException extends ArithmeticException
{
    public static final int Return = 0;
    public static final int Yield = 0;

    private int type;

    private OperandToken result;

    /**Create a new exception object*/
    public ControlException()
    {
    	type = 0;
    	result = null;
    }
    
    /**Set the message text
     @param text = the text to display*/
    public ControlException(int _type, Token _result)
    {
    	super("");
    	type = _type;
    	result = ((OperandToken)_result);
    }

    public int getType()
    {
    	return type;
    }

    public OperandToken getResults()
    {
    	return result;
    }
}
