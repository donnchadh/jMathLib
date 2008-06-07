package jmathlib.toolbox.time;

/* This file is part or MATHLIB */

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import java.util.*;


/*

Author: Stefan Mueller (stefan@held-mueller.de) 2002
*/


/**An external function for computing a mesh of a matrix  */
public class time extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        Date d = new Date();
        
        double time = (double)d.getTime();
        
        return new DoubleNumberToken(time);
    
    } // end eval
}


/*
@GROUP
time
@SYNTAX
t = time()
@DOC
Returns the elapsed milisecends from 1.1.1970.
@EXAMPLES
<programlisting>
t=time() 
</programlisting>
@NOTES
@SEE
tic, pause, date, time
*/
