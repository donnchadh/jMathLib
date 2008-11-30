package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for computing a mesh of a matrix  */
public class cumsum extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

		if ((getNArgIn(operands) < 1) ||
            (getNArgIn(operands) > 2)    )
			throwMathLibException("cumsum: number of input arguments <1 or >2");


		if ( !(operands[0] instanceof DoubleNumberToken))
			throwMathLibException("cumsum: works only on numbers");

		// get data from arguments
		double[][] re =  ((DoubleNumberToken)operands[0]).getValuesRe();
        double[][] im =  ((DoubleNumberToken)operands[0]).getValuesIm();


		int dx = ((DoubleNumberToken)operands[0]).getSizeX();
        int dy = ((DoubleNumberToken)operands[0]).getSizeY();
        
        
        double[][] reX = new double[dy][dx];
        double[][] imX = new double[dy][dx];
        
        
        // copy first line
        for (int x=0; x<dx; x++)
        {
            reX[0][x] = re[0][x];
            imX[0][x] = im[0][x];
        }
        
        
        // special if only one line (e.g. cumsum([1,2,3]) -> [1,3,6]
        if (dy==1)
        {
            for (int x=1; x<dx; x++)
            {
                reX[0][x] = reX[0][x] + reX[0][x-1];
                imX[0][x] = imX[0][x] + imX[0][x-1];
            }
            return new DoubleNumberToken( reX, imX );
        }
        
        // start with second line
        for (int y=1; y<dy; y++)
        {
            for (int x=0; x<dx; x++)
            {
                reX[y][x] = re[y][x] + reX[y-1][x];
                imX[y][x] = im[y][x] + imX[y-1][x];
            }
        } 

        return new DoubleNumberToken( reX, imX );
        
	} // end eval
}


/*
@GROUP
Matrix
@SYNTAX
ans = cumsum(array)
@DOC
return the cummulated sum of an array
@EXAMPLES
<programlisting>
cumsum([1,2,3;4,5,6;7,8,9])
 ans = [1 , 2 , 3]
[5 , 7 , 9]
[12 , 15 , 18]
</programlisting>
@NOTES
@SEE
sum, diag, trace, zeros, ones
*/
