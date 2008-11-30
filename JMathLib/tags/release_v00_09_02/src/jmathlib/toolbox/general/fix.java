package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;


public class fix extends ExternalFunction
{
	/**rounds the element to the nearest element towards zero.
	@param operands[0] = value to round
	@return a matrix of the same size as the operands*/
	public OperandToken evaluate(Token[] operands)
	{
		
        // exactly one operand
        if (getNArgIn(operands) != 1)
			throwMathLibException("fix: number of arguments != 1");
        
		// only works on numbers
		if(!(operands[0] instanceof DoubleNumberToken))
			throwMathLibException("fix: only works on numbers");
		
		DoubleNumberToken matrix = ((DoubleNumberToken)operands[0]);
		OperandToken temp  = ((OperandToken)matrix.clone());
					
		double[][] reValues = matrix.getValuesRe();
		double[][] imValues = matrix.getValuesIm();
		for(int y = 0; y < matrix.getSizeY(); y++)
		{
			for(int x = 0; x < matrix.getSizeX(); x++)
			{
				if(reValues[y][x] >=0 )
				{	
					// e.g. fix(3.2) => 3
					reValues[y][x] = java.lang.Math.floor(reValues[y][x]);
				}
				else
				{
					// e.g. fix(-3.2) => -3
					reValues[y][x] = java.lang.Math.ceil(reValues[y][x]);
				}
				if(imValues[y][x] >=0 )
				{	
					// e.g. fix(3.2i) => 3
					imValues[y][x] = java.lang.Math.floor(imValues[y][x]);
				}
				else
				{
					// e.g. fix(-3.2i) => -3
					imValues[y][x] = java.lang.Math.ceil(imValues[y][x]);
				}
			}
		}
		return new DoubleNumberToken(reValues, imValues);
		
	}	
}

/*
@GROUP
general
@SYNTAX
answer=fix(matrix)
@DOC
Rounds the element to the nearest element towards zero.
@EXAMPLES
<programlisting>
fix(2.3)=3  
fix(-2.3)=-2 
fix(1.9+2.4i)=1+2i 
fix(1.9-2.4i)=1-2i 
</programlisting>
@NOTES
@SEE
ceil, round, floor
**/

