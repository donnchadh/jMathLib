package jmathlib.toolbox.time;

/* This file is part or JMathLib *
author: Stefan Mueller (stefan@held-mueller.de) 2002
*/

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.interpreter.*;
import jmathlib.core.functions.ExternalFunction;
import java.util.*;




/**An external function for starting the internal stop watch  */
public class tic extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		Date d = new Date();
        
        double start = (double)d.getTime();
        
   	 	Variable ticVar = globals.getGlobalVariables().createVariable("_tic");
		ticVar.assign(new DoubleNumberToken(start));
        
		return null; //DoubleNumberToken.one;

	} // end eval
}


/*
@GROUP
time
@SYNTAX
tic()
@DOC
Starts the stop watch.
@EXAMPLES
<programlisting>
tic()
toc()
ans = 1.34
</programlisting>
@NOTES
@SEE
toc, pause, date, time
*/
