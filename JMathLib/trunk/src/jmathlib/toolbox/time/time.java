package jmathlib.toolbox.time;

/* This file is part or JMathLib 

author: Stefan Mueller (stefan@held-mueller.de) 2002
*/

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

import java.util.*;

/**An external function for computing a mesh of a matrix  */
public class time extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
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
