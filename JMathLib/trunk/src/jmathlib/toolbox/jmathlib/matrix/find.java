package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.LogicalToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for finding nonzero elements of a matrix    */
public class find extends ExternalFunction
{
	/**return a column vector which points to all nonzero elements of 
       the function arguments
       (e.g. find([1,2,3;0,0,4]) returns [1,3,5,6]')
	* @param  operands[0] = matrix 
	* @return position of nonzero elements  */
	public OperandToken evaluate(Token[] operands)
	{

		// Check number of arguments
		if ((getNArgIn(operands)<1) ||
            (getNArgIn(operands)>2) )
			throwMathLibException("find: number of arguments <1 or >2");

        double[][] values = null;
        int        dy     = 0;
        int        dx     = 0;
        
        if ((operands[0] instanceof DoubleNumberToken))
        {    
    		// get data from arguments
    		values = ((DoubleNumberToken)operands[0]).getValuesRe();
        }
        else if ((operands[0] instanceof LogicalToken))
        {
            DoubleNumberToken num = ((LogicalToken)operands[0]).getDoubleNumberToken();
            values          = num.getValuesRe();
        }
        else
            throwMathLibException("find: works only on numbers and booleans");

        dy     = values.length;
        dx     = values[0].length;  

		// find number of nonzero elements
		int no = 0;
		for (int yi=0; yi<dy ; yi++)
		{
			for (int xi=0; xi<dx ; xi++)
			{
				if (values[yi][xi] != 0.0)
					no++;
			}
		}	

        // check if there is at least one element unequal zero
		// In case there is no element unequal zero return an empty DoubleNumberToken()
        if (no==0)
            return new DoubleNumberToken();

        int n = no;
        if (getNArgIn(operands)==2)
        {
            if (!(operands[1] instanceof DoubleNumberToken))
                throwMathLibException("find: second argument must be a number");

            n = (int)((DoubleNumberToken)operands[1]).getValueRe();
        }
        debugLine("find "+n);
        
        double[][] ret = null;

        if (dy>1)
        {
            // build return vector
            ret = new double[Math.min(no,n)][1];
                
            int i = 0;
    		for (int xi=0; xi<dx; xi++)
    		{
    			for (int yi=0; yi<dy; yi++)
    			{
    				if (values[yi][xi] != 0.0)
    				{
    					// nonzero element found
    					// put element position into return column vector
    					ret[i][0] = yi + xi*dy + 1;
    					i++;
    				}
                    if (i>=Math.min(no,n))
                        break;
                }
                
                if (i>=Math.min(no,n))
                    break;
    		}	

        }
        else
        {
            // dy==1
            ret = new double[1][Math.min(no,n)];
            int i = 0;
            for (int xi=0; xi<dx; xi++)
            {
                if (values[0][xi] != 0.0)
                {
                    // nonzero element found
                    // put element position into return column vector
                    ret[0][i] = xi + 1;
                    i++;
                }
                if (i>=Math.min(no,n))
                    break;
            } 
        }

        return new DoubleNumberToken(ret);        

	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
find(matrix)
find(matrix, n)
@DOC
Finds all the non zero elements of a matrix
@NOTES
@EXAMPLES
<programlisting>
find([0,1,0,1])   = [2;4]
find([0,1,0,1],1) = [2]
</programlisting>
@SEE

*/

