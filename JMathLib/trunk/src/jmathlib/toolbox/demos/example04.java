package jmathlib.toolbox.demos;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.*;
import jmathlib.core.interpreter.*;

/**An example of an external function - it returns 2 * the first parameter*/
public class example04 extends ExternalFunction
{
	/**Execute the function returning the first parameter
	operands - array of parameters*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		ErrorLogger.debugLine("example04 right-hand arguments= "+operands.length);
		ErrorLogger.debugLine("example04 left-hand  arguments= "+getNoOfLeftHandArguments());

		if (operands[0] instanceof DoubleNumberToken)
		{
			double[][] argValues 	= ((DoubleNumberToken)operands[0]).getReValues();
			int        argSizeX 	= ((DoubleNumberToken)operands[0]).getSizeX();	
			int        argSizeY 	= ((DoubleNumberToken)operands[0]).getSizeY(); 

			ErrorLogger.debugLine("*** demo function: example04 ***");

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

		// return two left hand arguments: [a,b]=example04();
		OperandToken values[][] = new OperandToken[1][2];
		values[0][0] = new DoubleNumberToken(11.11);
		values[0][1] = new DoubleNumberToken(22.22);
		return new MatrixToken( values );
	}
}
