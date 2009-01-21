package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.graphics.*;
import jmathlib.core.interpreter.GlobalValues;

public class set extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

        if (getNArgIn(operands) != 3)
            throwMathLibException("set: number of arguments != 3");

        // handle
        if (!(operands[0] instanceof DoubleNumberToken)) 
        throwMathLibException("get: argument must be a number");

        double x = ((DoubleNumberToken)operands[0]).getValueRe();

        // property
        if (!(operands[1] instanceof CharToken)) 
            throwMathLibException("set:");

        String prop = ((CharToken)operands[1]).toString();

        HandleObject ho = null;
        
        try {
        ho = HandleObject.getHandleObject((int)x);
        }
        catch (Exception e)
        {
            throwMathLibException("set exception ");
        }
        
        System.out.println("set prop: "+ho.getProperty(prop).getName() );
        
        ho.show();

        // value
        if ((operands[2] instanceof CharToken)) 
        {
            String val = ((CharToken)operands[2]).toString();
            ho.set(prop, val);
        }
        else if ((operands[2] instanceof DoubleNumberToken))
        {
            double[][] vals = ((DoubleNumberToken)operands[2]).getReValues();
            ho.set(prop, vals);
        }
        else
            throwMathLibException("set: value");

        return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
get
@DOC
.
@EXAMPLES
.
@NOTES
@SEE
set, plot
*/

