package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.graphics.*;
import java.awt.Color;

public class ginput extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands)
	{


        double n=0;
        
        if (getNArgIn(operands) > 1)
			throwMathLibException("ginput: number of arguments >1");

        if (getNArgIn(operands) == 1)
        {
            if (!(operands[0] instanceof DoubleNumberToken)) 
            throwMathLibException("get: argument must be a number");

            n = ((DoubleNumberToken)operands[0]).getValueRe();
        }
        
        
        
        
		return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
ginput
@DOC
.
@EXAMPLES
<programlisting>
[x,y]=ginput

[x,y]=ginput(4)
</programlisting>
@NOTES
@SEE
plot, get, set
*/

