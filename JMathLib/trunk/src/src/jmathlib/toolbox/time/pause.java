package jmathlib.toolbox.time;
   
/* This file is part or MATHLIB */

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/*
author: Stefan Mueller (stefan@held-mueller.de) 2003
*/

/**wait for a specified period of time*/
public class pause extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

        if (getNArgIn(operands) != 1)
			throwMathLibException("pause: number of arguments != 1");
            

		if (operands[0] instanceof DoubleNumberToken) 
        {
			double pause = (((DoubleNumberToken)operands[0]).getReValues()[0][0]);
        
            try {        
                Thread.sleep((int)(pause*1000));
            }
            catch (InterruptedException e)
            {
            }

		}
        
		return null;
	}
}

/*
@GROUP
time
@SYNTAX
pause(value)
@DOC
Wait for a specified period of time. The command pause(n)
waits for n seconds
@EXAMPLES
<programlisting>
pause(3) waits for 3 seconds
pause(3.5) waits for 3.5 seconds
</programlisting>
@NOTES
Keep in mind that the parsing of the pause(x) command will
also take some time. Therefor the  waiting time will be longer
than expected.
@SEE
tic, toc, date, time

*/

