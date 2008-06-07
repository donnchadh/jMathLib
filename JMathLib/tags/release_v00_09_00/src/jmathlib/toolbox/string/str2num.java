package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.CharToken;

/**An external function for changing strings into numbers */
public class str2num extends ExternalFunction
{
    /**returns a matrix of numbers 
    * @param operands[0] = string (e.g. ["hello"]) 
    * @return a matrix of numbers                                */
    public OperandToken evaluate(Token[] operands)
    {

        // one operand 
        if (getNArgIn(operands)!=1)
            throwMathLibException("str2num: number of input arguments != 1");

        if ( !(operands[0] instanceof CharToken))
            throwMathLibException("str2num: works only on strings");

        // get data from arguments
        String stringValue = ((CharToken)operands[0]).getValue();

        // convert string to array of bytes
        byte[] b = stringValue.getBytes();
        
        double[][] X = new double[1][b.length];
        
        // convert array of byte to array of double
        for (int i=0; i<b.length; i++)
        {
            X[0][i]= (double)b[i];
        } 

        return new DoubleNumberToken( X );

    } // end eval
}


/*
@GROUP
char
@SYNTAX
number = str2num( string )
@DOC
Convert strings into numbers
@EXAMPLES
str2num("hello 12")  returns [104, 101, 108, 108, 111, 32, 49, 50]
@NOTES
.
@SEE
num2str
*/