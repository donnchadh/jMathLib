package jmathlib.core.tokens;

import jmathlib.core.interpreter.ErrorLogger;
import java.util.Vector;


/**Used to implement if-then-else operations within an expression*/
public class SwitchToken extends CommandToken
{

	/**test value*/
	OperandToken value;
	
	/**condition */
	Vector cases;

	/**Constructor setting cases
	@param _value = an OperandToken containing the value to test
	@param _cases = a vector of case tokens*/
	public SwitchToken(OperandToken _value, Vector _cases)
	{
		value = _value;
		cases = _cases;
	}

	public OperandToken getData()
	{
		return value;
	}

	public Vector getCases()
	{
		return cases;
	}

    /**evaluates the operator
    @param operands = the operators operands
    @return the result of the test as an OperandToken*/
    public OperandToken evaluate(Token[] operands)
    {
    	OperandToken result = null;
    	
    	int pos = 0;
    	int count = cases.size();
    	while((result == null) && (pos < count)) 
    	{
    		CaseToken caseToken = ((CaseToken)cases.elementAt(pos));
    		ErrorLogger.debugLine("switch "+caseToken.toString());
    		
    		result = caseToken.evaluate(new OperandToken[]{value});
    		
    		pos++;
    	}
    	
    	return null; // switch-case does not return any data
    }
    

    /**Convert the operator to a string
    @return the operator as a string*/
    public String toString()
    {
        return "switch"; //( "+value.toString()+" )";
    }
    
}
