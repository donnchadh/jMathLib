package jmathlib.toolbox.specfun;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.toolbox.specfun._private.*;

public class gammaln extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 1)
			throwMathLibException("gammaln: number of arguments != 1");

		if (!(operands[0] instanceof DoubleNumberToken)) 
            throwMathLibException("gammaln: argument must be a number");

        double[][] x = ((DoubleNumberToken)operands[0]).getReValues();

        int dy     = ((DoubleNumberToken)operands[0]).getSizeY();
        int dx     = ((DoubleNumberToken)operands[0]).getSizeX();   

        double[][] gammaln = new double[dy][dx]; 
        
        for (int xi=0; xi<dx ; xi++)
        {
            for (int yi=0; yi<dy ; yi++)
            {
                gammaln[yi][xi] = Gamma.logGamma(x[yi][xi]);
            }
        }
        
		return new DoubleNumberToken(gammaln);		

	} // end eval
}

/*
@GROUP
specfun
@SYNTAX
gammaln(x)
@DOC
return the log of the gamma function
@EXAMPLES
<programlisting>
gammaln(3.5)
gammaln([2,3,4;5,6,7])
</programlisting>
@NOTES
@SEE
beta
*/

