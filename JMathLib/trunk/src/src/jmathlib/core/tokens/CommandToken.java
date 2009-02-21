package jmathlib.core.tokens;

import jmathlib.core.interpreter.GlobalValues;

/**Class for implementing commands */
public class CommandToken extends OperandToken
{

	/**default constructor*/
    public CommandToken()
    {
    }

	/**@return the command as a string*/
    public String toString()
    {
    	return null; 
    }

	/**Evaluates the command
	@param operands = the commands operands
	@return the result of the command as an OperandToken*/
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {
        return null;
    }
}
