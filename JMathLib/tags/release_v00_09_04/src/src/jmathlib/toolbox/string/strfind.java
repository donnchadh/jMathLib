package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.interpreter.*;

/**An external function for finding a string within another*/
public class strfind extends ExternalFunction
{
	/**finds shorter string within a longer one
	@param operands[0] = first string
	@param operands[1] = second string*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		OperandToken result = null;
		
        if (getNArgIn(operands) !=2)
            throwMathLibException("strfind: number of arguments !=2");
		
		if(operands[0] instanceof CharToken)
		{
			if(operands[1] instanceof CharToken)
			{
				String string1 = ((CharToken)operands[0]).toString();
				String string2 = ((CharToken)operands[1]).toString();
				
				int index = 0;
				int count = 0;
				double lastpos = 0;
				double[][] tempResults = new double[1][string2.length()];

				do
				{
					index = string1.indexOf(string2);
					
					if(index > -1)
					{
						tempResults[0][count] = index + lastpos + 1;
						lastpos = tempResults[0][count];
						count++;
						
						string1 = string1.substring(index + 1, string1.length());
					}
				}while(index > -1);
				
				double[][] results = new double[1][count];
				for(index = 0; index < count; index++)
				{
					results[0][index] = tempResults[0][index];
				}
				result = new DoubleNumberToken(results);
			}
			else
				Errors.throwMathLibException(ERR_INVALID_PARAMETER, new Object[] {"CharToken", operands[1].getClass().getName()});
		}
		else
			Errors.throwMathLibException(ERR_INVALID_PARAMETER, new Object[] {"CharToken", operands[0].getClass().getName()});
		
		return result;
	}
}

/*
@GROUP
char
@SYNTAX
STRFIND(string1, string2)
@DOC
finds all occurences of string2 within string1.
@NOTES
@EXAMPLES
STRFIND("This is a test string", "is")
[3,6]
@SEE
findstr
*/

