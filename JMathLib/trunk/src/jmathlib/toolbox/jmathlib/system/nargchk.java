package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.Errors;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for checking the number of arguments*/
public class nargchk extends ExternalFunction
{
	/**check the number of arguments for a script file
	@param operand[0] = the lowest number of arguments
	@param operand[1] = the highest number of arguments
	@param operand[2] = the actual number of arguments
	@return an error string if the number of arguments is
	not between the lowest and highest values
	*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		OperandToken result = null;

        if (getNArgIn(operands) !=3)
            throwMathLibException("nargchk: number of arguments !=3");
		
		if(operands[0] instanceof DoubleNumberToken)
		{
			if(operands[1] instanceof DoubleNumberToken)
			{
				if(operands[2] instanceof DoubleNumberToken)
				{
					String message = "";
					double min = ((DoubleNumberToken)operands[0]).getValueRe();
					double max = ((DoubleNumberToken)operands[1]).getValueRe();
					double val = ((DoubleNumberToken)operands[2]).getValueRe();
					
					if(val < min)
						message = Errors.getErrorText(ERR_INSUFFICIENT_PARAMETERS, new Object[] {operands[0]});
					else if(val > max)
						message = Errors.getErrorText(ERR_TOO_MANY_PARAMETERS, new Object[] {operands[1]});
					
					result = new CharToken(message);
				}
				else
					Errors.throwMathLibException(ERR_INVALID_PARAMETER, new Object[] {"DoubleNumberToken", operands[2].getClass().getName()});
			}
			else
				Errors.throwMathLibException(ERR_INVALID_PARAMETER, new Object[] {"DoubleNumberToken", operands[1].getClass().getName()});
		}
		else
			Errors.throwMathLibException(ERR_INVALID_PARAMETER, new Object[] {"DoubleNumberToken", operands[0].getClass().getName()});
			
		return result;
	}
}

/*
@GROUP
system
@SYNTAX
nargchk(min, max, no of input args)
@DOC
Checks that the number of input args is between min and max
@NOTES
@EXAMPLES
NARGOUTCHK(1, 3, NARGIN)
@SEE
nargoutchk
*/

