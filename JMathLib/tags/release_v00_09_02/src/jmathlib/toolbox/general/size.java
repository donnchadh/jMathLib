package jmathlib.toolbox.general;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.DataToken;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for getting the size of matrices*/
public class size extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

		// at least one operand
        if ((getNArgIn(operands) < 1) ||
            (getNArgIn(operands) > 2)    )
			throwMathLibException("size: number of arguments <1 or >2");
        
		// first operand must be a number
		if (!(operands[0] instanceof DataToken)) 
        	throwMathLibException("size: argument must be a data token");
		
        // get size array (e.g. [2,3,1] for 2x3x1 array
        int[] size =  ((DataToken)operands[0]).getSize();
        
		// e.g. size(A) -> [2,3,2]
        if (getNArgIn(operands)==1)
		{
            double[][] values = new double[1][size.length];

            // copy size vector to return value
            for (int i=0; i<size.length; i++)
                values[0][i] = (double)size[i];
                
            // return vector of sizes
            return new DoubleNumberToken(values);		
        }
        
        
        // e.g. size([1,2,3],1)  -> 1
        // e.g. size([1,2,3],2)  -> 3
        // second operand must also be a number
        if (!(operands[1] instanceof DoubleNumberToken)) 
            throwMathLibException("size: second argument must be a number token");

        // retrieve requested dimension
        int n = (int)((DoubleNumberToken)operands[1]).getValueRe(0);

        // check index of dimension vector
        if ((n<1) || (n>size.length))
            throwMathLibException("size: dimension not supported");

        // return one element from size vector
        return new DoubleNumberToken(size[n-1]);
        
    }
}

/*
@GROUP
general
@SYNTAX
[y, x] = size(matrix)
 z     = size(matrix, n)
@DOC
returns the size of a matrix.
@EXAMPLES
<programlisting>
size([1,2;3,4]) = [2,2]
size([1,2,3;4,5,6]) = [2,3]

a=rand(4,4,2)
size(a)  -> [4,4,2)

size(a,3) -> 2
</programlisting>
@NOTES
@SEE
rowcount, colcount, row, col, ndims
*/

