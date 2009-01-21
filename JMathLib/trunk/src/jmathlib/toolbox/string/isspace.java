package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.CharToken;

/**An external function for checking on whitespaces */
public class isspace extends ExternalFunction
{
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {
        if (getNArgIn(operands)!=1)
            throwMathLibException("isspace: number of input arguments != 1");

        if ( !(operands[0] instanceof CharToken))
            throwMathLibException("isspace: works only on strings");

        // get data from arguments
        String str = ((CharToken)operands[0]).getValue();

        double[][] ret = new double[1][str.length()];
        
        // find all whitespaces
        for (int i=0; i<str.length(); i++)
        {
            char c = str.charAt(i);
            if ((c==' ')  || 
                (c=='\t') || 
                (c=='\r') ||
                (c=='\n')   )
                ret[0][i]= 1.0;
        } 

        return new DoubleNumberToken( ret );

    } // end eval
}


/*
@GROUP
char
@SYNTAX
isspace()
@DOC
@EXAMPLES
@NOTES
@SEE
letter
*/