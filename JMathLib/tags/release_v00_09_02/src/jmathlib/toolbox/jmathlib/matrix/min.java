package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.*;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function that return 1.0 is all elements of the argument are nonzero  */
public class min extends ExternalFunction
{
	/**return a  matrix 
	* @param operands[0] = matrix 
	* @return minimum of all elements   */
	public OperandToken evaluate(Token[] operands)
	{

        if ((getNArgIn(operands) < 1 ) ||
            (getNArgIn(operands) > 2 )    )
			throwMathLibException("min: number of arguments 1 or 2");
        
        // if boolean: convert LogicalToken to DoubleNumberToken
        if (operands[0] instanceof LogicalToken)
            operands[0]=((LogicalToken)operands[0]).getDoubleNumberToken(); 

        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("min: only works on numbers");


		// get data from arguments
		double[][] a_r     = ((DoubleNumberToken)operands[0]).getValuesRe();
        //double[][] a_i     = ((DoubleNumberToken)operands[0]).getValuesIm();
		int a_dy           = a_r.length;
        int a_dx           = a_r[0].length;	
        double   minRe     = 0;
        //double   minIm     = 0;
        double[][] result  = null;
        
        // one argument e.g. min([3,4,5;6,7,7]) or
        //  min(888) or min([2,3,4]) or  min([3;4;5])
        if (a_dy>1)
        {
            // e.g. min([2,3,4;5,6,7])
            // e.g. min([3;4;5])
            result = new double[1][a_dx];
	
    		for (int xi=0; xi<a_dx ; xi++)
    		{
                minRe = a_r[0][xi];
    			for (int yi=0; yi<a_dy ; yi++)
    			{
    				if (a_r[yi][xi] < minRe) 
                        minRe = a_r[yi][xi];
    			}
                result[0][xi]= minRe;
    		}	
        }
        else if (a_dy==1)
        {
            // e.g. min([2,3,4])
            result = new double[1][1];

            minRe = a_r[0][0];
            for (int xi=0; xi<a_dx ; xi++)
            {
                if (a_r[0][xi] < minRe) 
                    minRe = a_r[0][xi];
            }   
            result[0][0]= minRe;
        }

        
        
        if (getNArgIn(operands) == 2)  
        {
            // two arguments e.g. min([3,4,5;6,7,7], 99) or
            //  min(888, 999) or min(2, [4,5,6])

            // if boolean: convert LogicalToken to DoubleNumberToken
            if (operands[1] instanceof LogicalToken)
                operands[1]=((LogicalToken)operands[1]).getDoubleNumberToken(); 

            if (!(operands[1] instanceof DoubleNumberToken))
                throwMathLibException("min: only works on numbers");

            double[][] b_r     = ((DoubleNumberToken)operands[1]).getValuesRe();
            //double[][] b_i     = ((DoubleNumberToken)operands[1]).getValuesIm();
            int b_dy           = b_r.length;
            int b_dx           = b_r[0].length; 

            // if both arguments are matrices, then dimensions must match
            if (((a_dy>1) || (a_dx>1)) && 
                ((b_dy>1) || (b_dx>1)) &&
                ( (a_dy!=b_dy) || (a_dx!=b_dx))               )
                throwMathLibException("min: dimensions must agree");

            if (((a_dy>1) && (b_dy>1)) || ((a_dx>1) && (b_dx>1)))
            {
                // e.g. min([2,3,4] , [5,6,7])
                result = new double[a_dy][a_dx];
        
                for (int xi=0; xi<a_dx ; xi++)
                {
                    for (int yi=0; yi<a_dy ; yi++)
                    {
                        if (a_r[yi][xi] < b_r[yi][xi]) 
                            result[yi][xi]= a_r[yi][xi];
                        else
                            result[yi][xi]= b_r[yi][xi];
                    }
                }   
            }
            else if ((a_dy==1) && (a_dx==1))
            {
                // e.g. min(3, [2,3,4])
                result = new double[b_dy][b_dx];
                minRe = a_r[0][0];
                
                for (int xi=0; xi<b_dx ; xi++)
                {
                    for (int yi=0; yi<b_dy ; yi++)
                    {
                        if (b_r[yi][xi] < minRe) 
                            result[yi][xi]= b_r[yi][xi];
                        else
                            result[yi][xi]= minRe;
                    }
                }   
            }
            else if ((b_dy==1) && (b_dx==1))
            {
                // e.g. min([2,3,4], 3)
                result = new double[a_dy][a_dx];
                minRe = b_r[0][0];
                
                for (int xi=0; xi<a_dx ; xi++)
                {
                    for (int yi=0; yi<a_dy ; yi++)
                    {
                        if (a_r[yi][xi] < minRe) 
                            result[yi][xi]= a_r[yi][xi];
                        else
                            result[yi][xi]= minRe;
                    }
                }   
            }

            
        }
        return new DoubleNumberToken(result);
        
	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
answer = min(matrix)
answer = min(matrix,matrix)
answer = min(scalar,matrix)
answer = min(matrix,scalar)
@DOC
returns the minimum of a matrix
@NOTES
@EXAMPLES
min(3,4) return 3
min([2,3,4],3] return [2,3,4]
@SEE
max
*/

