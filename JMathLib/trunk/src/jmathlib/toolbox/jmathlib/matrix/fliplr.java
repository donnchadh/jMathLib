package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for flipping matrices from left to right */
public class fliplr extends ExternalFunction
{
	/**return a  matrix 
	@param operands[0] = matrix to flip from left to right */
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		// one operands (e.g. fliplr(A) )
		if (operands == null)                             
            return null;

		if (operands.length != 1)                         
            return null;

		if (operands[0] == null)       
			return null;

		if (!(operands[0] instanceof DoubleNumberToken))
			return null;


		// get data from array
		double[][] real =      ((DoubleNumberToken)operands[0]).getValuesRe();
        double[][] imag =      ((DoubleNumberToken)operands[0]).getValuesIm();

		int dy     = real.length;
        int dx     = real[0].length;	
	
        double re = 0.0;
        double im = 0.0;
    
		// flip matrix
		for (int xi=0; xi<dx/2 ; xi++)
		{
			for (int yi=0; yi<dy ; yi++)
			{
				// flip
				re = real[yi][xi];
	            real[yi][xi]      = real[yi][dx-1-xi];
                real[yi][dx-1-xi] = re; 
                
				im = imag[yi][xi];
	            imag[yi][xi]      = imag[yi][dx-1-xi];
                imag[yi][dx-1-xi] = im; 
			}
        }
		return new DoubleNumberToken(real, imag);		

	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
fliplr(A)
@DOC
Flips a matrix from left to right.
@NOTES
@EXAMPLES
fliplr([1,2,3;4,5,6]) = [3,2,1;6,5,4]
@SEE
flipud

*/

