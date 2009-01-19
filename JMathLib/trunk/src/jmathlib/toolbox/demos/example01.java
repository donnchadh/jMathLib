package jmathlib.toolbox.demos;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.*;
import jmathlib.core.interpreter.*;

/**An example of an external function - it returns 2 * the first parameter*/
public class example01 extends ExternalFunction
{
	/**Execute the function returning the first parameter
	operands - array of parameters*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		if (operands[0] instanceof DoubleNumberToken)
		{
			double[][] argValues 	= ((DoubleNumberToken)operands[0]).getReValues();
			int        argSizeX 	= ((DoubleNumberToken)operands[0]).getSizeX();	
			int        argSizeY 	= ((DoubleNumberToken)operands[0]).getSizeY(); 

			ErrorLogger.debugLine("*** demo function: example01 ***");

			/* Check dimensions of matrix */
			//ErrorLogger.debugLine("DoubleNumberToken: sub (n*m) - (n*m)");
			for (int yy=0; yy<argSizeY; yy++) 
			{
				for (int xx=0; xx<argSizeX; xx++)
				{
					argValues[yy][xx] = 2 * argValues[yy][xx] ;
				}
			}
   			return new DoubleNumberToken(argValues);   	
		}
		return new DoubleNumberToken(999.999);
	}
}
