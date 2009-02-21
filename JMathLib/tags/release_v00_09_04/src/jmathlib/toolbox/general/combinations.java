package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**External function to calculate the number of combinations
when k objects are taken from a set of k*/
public class combinations extends ExternalFunction
{
	/**calculate the number of combinations
	@param operand[0] = The the number of objects taken
	@param operand[1] = The total number of objects
	@return the number of combinations
	*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		OperandToken result = new DoubleNumberToken(0);
		
        if (getNArgIn(operands)!= 2)
			throwMathLibException("combinations: number of arguments != 2");
            
	    if (!(operands[0] instanceof DoubleNumberToken) && 
            !(operands[1] instanceof DoubleNumberToken)	 )
        	throwMathLibException("combinations: arguments must be numbers");

		//comb(x y) = y!/(x! * (y-x)!)
		DoubleNumberToken objects = ((DoubleNumberToken)operands[0]);
		DoubleNumberToken total   = ((DoubleNumberToken)operands[1]);
			
		//result = x!
		result = objects.factorial();

		//temp = y-x
		OperandToken temp = ((OperandToken)total.clone());
		temp = temp.subtract(objects);

		//temp = (y-x)!
		temp = temp.factorial();

		//result = x! * (y-x)!
		result = result.multiply(temp);

		//result = y! / (x! * (y-x)!)
		temp = total.factorial();

		result = temp.divide(result);

		return result;
	}
}

/*
@GROUP
general
@SYNTAX
answer = combinations(items, total)
@DOC
Returns the number of combinations when taking count items from a set of total items.
@EXAMPLES
combinations(3,5) = 10
combinations(3,6) = 20
@NOTES

@SEE
permutations

*/
