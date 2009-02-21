package jmathlib.toolbox.statistics;

import jmathlib.core.functions.*;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;



/**
 * An external function for calculating the average
 */
public class average extends ExternalFunction
{

    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {
        int i;
        OperandToken ot = (OperandToken) operands[0];

        for (i=1; i<operands.length; i++)
            {
                ot.add((OperandToken)operands[i]);
            }
        ot.divide(new DoubleNumberToken(i));

        return ot;
    }
}



/*
@GROUP
statistics
@SYNTAX
average(1,2,3,...,n)
@DOC
Calculates the average of the passed parameters.
@EXAMPLES
<programlisting>
Average(a,b,3,4)
</programlisting>
@SEE
std, mean, var
*/
