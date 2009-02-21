package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.CharToken;

/**An external function for changing numbers into strings*/
public class blanks extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		OperandToken result = null;
		
        if (getNArgIn(operands) != 1 )
            throwMathLibException("blanks: number of arguments !=1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("blanks: only works on numbers");
            
		int length = (new Double(((DoubleNumberToken)operands[0]).getValueRe())).intValue();
		
		StringBuffer buffer = new StringBuffer(length);
		
		for(int index = 0; index < length; index++)
		{
			buffer.append(' ');
		}
		String temp = new String(buffer);
		
		result = new CharToken(temp);

		return result;	
	}
}

/*
@GROUP
char
@SYNTAX
BLANKS(number)
@DOC
Outputs a number of spaces equal to number.
@NOTES
@EXAMPLES
"-" + BLANKS(5) + "-" = "-     -"
@SEE
deblank
*/

