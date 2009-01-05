package jmathlib.toolbox.time;

/* This file is part or MATHLIB */

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import java.util.*;

/*
Author: Stefan Mueller (stefan@held-mueller.de) 2002
*/


/**An external function for computing the time difference between a 
   call to tic() and toc()  (internal stop watch)  */
public class toc extends ExternalFunction
{
	/**returns a time difference 
	* @return the time difference in seconds as a double number */
	public OperandToken evaluate(Token[] operands)
	{

		Date d = new Date();
        
        double stop = (double)d.getTime();
        
        if (!getGlobalVariables().isVariable("_tic"))
        	throwMathLibException("toc: you must call tic before toc");
            
   	 	OperandToken ticTok = getGlobalVariables().getVariable("_tic").getData();
		
        if (ticTok instanceof DoubleNumberToken)
        {
        	double start = ((DoubleNumberToken)ticTok).getValueRe();
            return new DoubleNumberToken( (stop - start)/1000 );
        }
        else
            throwMathLibException("toc: _tic variable has wrong type"); 	
            
        return null; 

	} // end eval
}


/*
@GROUP
time
@SYNTAX
toc()
@DOC
Returns the time difference in seconds between the call to tic()
and toc().
@EXAMPLES
<programlisting>
tic()
toc()
returns
ans = 1.34
</programlisting>
@NOTES
@SEE
tic, pause, date, time
*/
