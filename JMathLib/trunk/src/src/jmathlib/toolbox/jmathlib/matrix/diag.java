package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for computing a mesh of a matrix  */
public class diag extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		if ((getNArgIn(operands) < 1) ||
            (getNArgIn(operands) > 2)    )
			throwMathLibException("diag: number of input arguments <1 or >2");


		if ( !(operands[0] instanceof DoubleNumberToken))
			throwMathLibException("diag: works only on numbers");

		// get data from arguments
		double[][] x =  ((DoubleNumberToken)operands[0]).getReValues();


		int dx = x[0].length;
        int dy = x.length;
        
        // diag([1,2,3;4,5,6;7,8,9] -> [1,5,9]
        // get the k-th diagonal diag(X,k)
        int k = 0;
        if (getNArgIn(operands)==2)
        {
            k = (int)((DoubleNumberToken)operands[1]).getReValues()[0][0];
            debugLine("diag k="+k);
        }

        
        // if diag([1,2,3]) -> [1,0,0; 0,2,0; 0,0,3]
        if (dy==1)
        {
            double[][] X = new double[dx+Math.abs(k)][dx+Math.abs(k)];
            for (int i=0; i<dx; i++)
            {
                if (k>=0)
                    X[i][i+k]= x[0][i];

                if (k<0)
                    X[i-k][i]= x[0][i];
            }
            
            return new DoubleNumberToken(X);
        }

        // if diag([1;2;3]) -> [1,0,0; 0,2,0; 0,0,3]
        if (dx==1)
        {
            double[][] X = new double[dy+Math.abs(k)][dy+Math.abs(k)];
            for (int i=0; i<dy; i++)
            {
                if (k>=0)
                    X[i][i+k]= x[i][0];

                if (k<0)
                    X[i-k][i]= x[i][0];

            }
            
            return new DoubleNumberToken(X);
        }
        
        
        
        // if k is greater number of rows and colums return empty matrix
        if ((k>=dx) || (-k>=dy))
            return new DoubleNumberToken();
        
        
        double[][] X = null;
        
        // get all diagonal elements if x[][]
        if (k>0)
        {
            int n = Math.min(dx, dy);
            if (k+n>dx)
                n=dx-k;

            X = new double[n][1];
            for (int i=0; i<n; i++)
            {
               	X[i][0] = x[i][i+k];
            } 
        }
        else if (k<0)
        {
            int n = Math.min(dx, dy);
            if (-k+n>dy)
                n=dy+k;

            X = new double[n][1];
            for (int i=0; i<n; i++)
            {
                X[i][0] = x[i-k][i];
            } 
        }
        else
        {
            // k==0: take main diagonal
            int n = Math.min(dx, dy);
            
            X = new double[n][1];
            
            for (int i=0; i<n; i++)
            {
                X[i][0] = x[i][i];
            } 
        }
		return new DoubleNumberToken( X );
        
	} // end eval
}


/*
@GROUP
Matrix
@SYNTAX
answer = diag (value)
@DOC
Returns the diagnoal of a matrix
@EXAMPLES
<programlisting>
diag([1,2,3;4,5,6;7,8,9]  
[1,
 5,
 9]
 
 diag([2,3,4;5,6,7],1]
 [3,
  7]
</programlisting>
@NOTES
.
@SEE
sum, trace, zeros, ones
*/
