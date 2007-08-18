package jmathlib.toolbox.general;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.interpreter.Errors;
import jmathlib.core.functions.*;

/**An external function to compute the binary inclusive or of two numbers*/
public class bitor extends ExternalFunction
{
	/**Returns the binary inclusive or of two numbers
	@param operands[0] - The first number
	@param operands[1] - The second number
	@return the result of the function as an OperandToken*/	
	public OperandToken evaluate(Token[] operands)
	{
        DoubleNumberToken result = DoubleNumberToken.zero;
        if (getNArgIn(operands) != 2)
			throwMathLibException("BitOr: number of arguments != 2");

        if(operands[0] instanceof DoubleNumberToken)
        {
            if(operands[1] instanceof DoubleNumberToken)
            {
                int value1 = ((DoubleNumberToken)operands[0]).getIntValue(0,0);   
                int value2 = ((DoubleNumberToken)operands[1]).getIntValue(0,0);   
                
                result = new DoubleNumberToken(value1 | value2);
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
general
@SYNTAX
bitor(number1, number2);
@DOC
Calculates the bitwise OR of number1 and number2
@EXAMPLE
<programlisting>
bitor(5, 9) = 13
bitor(5, 7) = 7
</programlisting>
@SEE
bitand, bitshift, bitxor
*/
