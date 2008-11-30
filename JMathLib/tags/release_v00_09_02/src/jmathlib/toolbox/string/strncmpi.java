package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.interpreter.*;

/**An external function for comparing two strings*/
public class strncmpi extends ExternalFunction
{
	/**compares two strings
	@param operands[0] = first string
	@param operands[1] = second string*/
	public OperandToken evaluate(Token[] operands)
	{
		int result = 0;
		
        if (getNArgIn(operands) !=3)
            throwMathLibException("strncmpi: number of arguments !=3");
		
		if(operands[0] instanceof CharToken)
		{
			if(operands[1] instanceof CharToken)
			{
				if(operands[2] instanceof DoubleNumberToken)
				{
					int index = ((DoubleNumberToken)operands[2]).getIntValue(0,0);
					String string1 = ((CharToken)operands[0]).toString().toUpperCase();				
					if(string1.length() > index)
						string1 = string1.substring(0, index);

					String string2 = ((CharToken)operands[1]).toString().toUpperCase();
					if(string2.length() > index)
						string2 = string2.substring(0, index);
					
					
					if(string1.equals(string2))
						result = 1;
				}
				else
					Errors.throwMathLibException(ERR_INVALID_PARAMETER, new Object[] {"DoubleNumberToken", operands[2].getClass().getName()});
			}
			else
				Errors.throwMathLibException(ERR_INVALID_PARAMETER, new Object[] {"CharToken", operands[1].getClass().getName()});
		}		
		else
			Errors.throwMathLibException(ERR_INVALID_PARAMETER, new Object[] {"CharToken", operands[0].getClass().getName()});
		
		return new DoubleNumberToken(result);
	}
}

/*
@GROUP
char
@SYNTAX
STRNCMP(string1, string2,no. of characters)
@DOC
Compares a number of characters in string1 to string2, ignoring case.
@NOTES
@EXAMPLES
STRNCMP("ABcd", "abce", 3)
1 
STRNCMP("abcd", "abce", 3) 
1 
STRNCMPI("abcd", "abce", 4) 
0 
@SEE
strcmp, strcmpi, strncmp
*/

