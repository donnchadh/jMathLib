package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.CharToken;

/**An external function for returning the length of a string*/
public class substring extends ExternalFunction
{
	/**Calculate the length of the string
	@param operands[0] the string to get the length for*/
	public OperandToken evaluate(Token[] operands)
	{
		OperandToken result = null;
		
        if ((getNArgIn(operands) < 2) || (getNArgIn(operands) > 3) )
            throwMathLibException("substring: number of arguments <2 or >3");
        
        if (!(operands[0] instanceof CharToken))
            throwMathLibException("subString: only works on chars");

        if (!(operands[1] instanceof DoubleNumberToken))
            throwMathLibException("subString: parameter 2 must be double");

		String argString = ((CharToken)operands[0]).toString();
		String substring = "";
		if(operands.length < 3 || operands[2] == null)
		{
			int pos = ((int)((DoubleNumberToken)operands[1]).getValueRe());
			substring = argString.substring(pos);
		}
		else
		{
            if (!(operands[2] instanceof DoubleNumberToken))
                throwMathLibException("subString: parameter 3 must be double");

			int pos  = ((int)((DoubleNumberToken)operands[1]).getValueRe());
			int pos2 = ((int)((DoubleNumberToken)operands[2]).getValueRe());
			substring = argString.substring(pos, pos2);
		}
		
		result = new CharToken(substring);
		
		return result;

	}
}

/*
@GROUP
char
@SYNTAX
answer = SUBSTRING(string, start, [length])
@DOC
Returns a portion of a string.
@NOTES
@EXAMPLES
SUBSTRING("HELLO WORLD", 0, 5) = "HELLO"
SUBSTRING("HELLO WORLD", 3, 5) = "LLO W"
@SEE
*/

