package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.CharToken;

/**An external function for changing numbers into strings*/
public class _char extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

        // one operand 
        if (getNArgIn(operands)!=1)
            throwMathLibException("_char: number of input arguments != 1");

        if ( !(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("_char: works only on numbers");
        
        double[][]  x = ((DoubleNumberToken)operands[0]).getReValues();
        
        if ( x.length != 1)
            throwMathLibException("char: works only on row vectors");
        
        String data = "";
        
        // convert numbers into a string
        try {
            for (int i=0; i<x[0].length; i++)
            
            {
            	byte[] b = { new Double(x[0][i]).byteValue() };
                data += new String(b, "UTF8");
            }
        }
        catch (Exception e)
        {
            throwMathLibException("_char: exception");
        }

        return new CharToken(data);		
	}
}

/*
@GROUP
char
@SYNTAX
string = _char(number)
@DOC
Converts a number to a string.
@NOTES
@EXAMPLES
_char([104, 101, 108, 108, 111, 32, 49, 50]) = "hello 12"

@SEE
double
*/

