package jmathlib.core.tokens;

import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.interpreter.GlobalValues;


/**Used to implement if-then-else operations within an expression*/
public class CaseToken extends CommandToken
{

	/**condition */
	OperandToken value;

	/** { code } to execute if condition is true*/
	OperandToken code;

	/**Constructor setting ifRelation and ifCode
	@param _ifRelation = the test relation
	@param _ifCode     = the code to execute if the test is true*/
	public CaseToken(OperandToken _value, OperandToken _code)
	{
		value 	= _value;
		code	= _code;
	}

	public OperandToken getExpression()
	{
		return code;
	}


    /**evaluates the operator
    @param operands = the operators operands
    @return the result of the test as an OperandToken*/
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {
    	if(value != null)
    	{
	    	Expression exp = new Expression(new RelationOperatorToken('e'), 
	    									((OperandToken)operands[0]), 
											value);
	    	
	    	OperandToken result = exp.evaluate(null, globals);
	    	
	    	if(result instanceof LogicalToken)
	    	{
	    		if(((LogicalToken)result).getValue(0))
	    		{
	    			ErrorLogger.debugLine("case is TRUE ");
	    			code.evaluate(null, globals);
	    			return new LogicalToken(true);
	    		}
	    	}
	 	}
	 	else
	 	{
	 		ErrorLogger.debugLine("case is DEFAULT ");
	    	code.evaluate(null, globals);
	    	return new LogicalToken(true);
	 	}
	 		   	
    	return null;
    }
    

    /**Convert the operator to a string
    @return the operator as a string*/
    public String toString()
    {
		if (value != null)
	        return "case: " + value.toString();
		else
			return "default: ";						  
    }

}
