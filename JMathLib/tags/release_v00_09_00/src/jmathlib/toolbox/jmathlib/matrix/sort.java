package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.MatrixToken;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for sorting arrays    */
public class sort extends ExternalFunction
{
	/** sorting vectors and arrays */
	public OperandToken evaluate(Token[] operands)
	{

		// Check number of arguments
		if (getNArgIn(operands)!=1) 
			throwMathLibException("sort: number of arguments <> 1");

		if (!(operands[0] instanceof DoubleNumberToken))
			throwMathLibException("sort: works only on numbers");

		// get data from arguments
		double[][] values = ((DoubleNumberToken)operands[0]).getReValues();
		int        dy     = ((DoubleNumberToken)operands[0]).getSizeY();
        int        dx     = ((DoubleNumberToken)operands[0]).getSizeX();
        double     swap   = 0.0;
        double     swapI  = 0.0;
        
        // create target array
        double[][] ret = new double[dy][dx];
        
        // create index array
        double[][] index; 

        // initialize minimum and maximum values
        double[] minimum = new double[dx];
        double[] maximum = new double[dx];
        
        // copy original array to target array
        for (int xi=0; xi<dx ; xi++)
        {
            // initialize min/max of each column
            minimum[xi]= values[0][xi];
            maximum[xi]= values[0][xi];
            
            for (int yi=0; yi<dy; yi++)
            {
                ret[yi][xi] = values[yi][xi];
                
                if (values[yi][xi] < minimum[xi])
                    minimum[xi] = values[yi][xi];

                if (values[yi][xi] > minimum[xi])
                    maximum[xi] = values[yi][xi];

            }

            debugLine("sort minimum: "+minimum);
            debugLine("sort maximum: "+maximum);
        }   

        
        // find minimum
        if (dy==1)
        {
            // e.g. sort([2,8,4])  one row only
            
            // indices of original values  (1,2,3....,dx)
            index = new double[1][dx];
            for (int i=0; i<dx; i++)
            {
                index[0][i]=i+1;
            }

            // sorting
            for (int xxi=0; xxi<dx; xxi++)
            {
                for (int xi=0; xi<dx-1-xxi ; xi++)
                {
                    if (ret[0][xi] > ret[0][xi+1] )
                    {
                        // swap value
                        swap          = ret[0][xi+1];
                        ret[0][xi+1]  = ret[0][xi];
                        ret[0][xi]    = swap;

                        // swap index value
                        swapI         = index[0][xi+1];
                        index[0][xi+1]= index[0][xi];
                        index[0][xi]  = swapI;
                    }
                }
            }
        }
        else
        {    
            // e.g. sort[1,2,3;2,2,2;0,0,1])

            // indices of original values  (1,2,3....,dy)'
            index = new double[dy][dx];
            for (int i=0; i<dy; i++)
            {
                for (int x=0; x<dx; x++)
                {
                    index[i][x]=i+1;
                }
            }

            
            // sorting
            for (int yyi=0; yyi<dy; yyi++)
            {
                for (int yi=0; yi<dy-1-yyi ; yi++)
                {
                    
                    // go trough each row
                    for (int xi=0; xi<dx; xi++)
                    {
    
                        if (ret[yi][xi] > ret[yi+1][xi] )
                        {
                            // swap value
                            swap            = ret[yi+1][xi];
                            ret[yi+1][xi]   = ret[yi][xi];
                            ret[yi][xi]     = swap;
                            
                            // swap index value
                            swapI           = index[yi+1][xi];
                            index[yi+1][xi] = index[yi][xi];
                            index[yi][xi]   = swapI;

                        }
                    }
                }
            }

        }
        
        // e.g. [x,y]=sort([3,7,4;2,9,4])
        if (getNArgOut()==2)
        {
            OperandToken matrix[][] = new OperandToken[1][2];
            matrix[0][0] = new DoubleNumberToken(ret);
            matrix[0][1] = new DoubleNumberToken(index);
            return new MatrixToken( matrix );
        }

        // normal return for one return argument
        return new DoubleNumberToken(ret); 
        
	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
sort(a)
[x,y]=sort(A)
@DOC
sorting of vectors and arrays.
@NOTES
@EXAMPLES
<programlisting>
sort([6,2,9])   -> [2,6,9]
sort([5,1;2,3]) -> [2,1; 5,3]
[x,y]=sort([3,7,4;2,9,1]) -> x=[2,7,1;3,9,4]  y=[2,1,2;1,2,1]
</programlisting>
@SEE
find
*/

