package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.LogicalToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;


public class logical extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

        if (getNArgIn(operands) != 1 )
			throwMathLibException("logical: number of arguments !=1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("logical: only works on numbers");

        DoubleNumberToken num = (DoubleNumberToken)operands[0];
        
        int[] size = num.getSize();
        
        int n = num.getNumberOfElements();
        
        LogicalToken l = new LogicalToken(size, null);

        for (int i=0; i<n; i++)
        {
            if (num.getValueRe(i)!=0)
                l.setValue(i, true);
            else
                l.setValue(i, false);
                
            if (num.getValueIm(i)!=0)
                throwMathLibException("logical: only works on real numbers");
        }
        
        return l;
        
	} // end eval
}

/*
@GROUP
general
@SYNTAX
logical(x)
@DOC
converts a double array into an array of boolean
@EXAMPLES
<programlisting>
logical([1,3,0,4]) -> [true, true, false, true]
</programlisting>
@SEE
true, false, islogical
*/

