package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for flipping matrices from up to down */
public class flipud extends ExternalFunction
{
	/**return a  matrix 
	@param operands[0] = matrix to flip from up to down */
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		// one operands (e.g. flipud(A) )
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
		for (int xi=0; xi<dx ; xi++)
		{
			for (int yi=0; yi<dy/2 ; yi++)
			{
				// flip
				re = real[yi][xi];
	            real[yi][xi]      = real[dy-1-yi][xi];
                real[dy-1-yi][xi] = re; 

				im = imag[yi][xi];
	            imag[yi][xi]      = imag[dy-1-yi][xi];
                imag[dy-1-yi][xi] = im; 
                
			}
        }
		return new DoubleNumberToken(real, imag);		

	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
flipud(A)
@DOC
Flips a matrix from up to down.
@NOTES
@EXAMPLES
flipud([1,2,3;4,5,6]) = [4,5,6;1,2,3]
@SEE
fliplr

*/

