package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class floor extends ExternalElementWiseFunction
{
    
    public floor()
    {
        name = "floor";
    }
    
    /**Standard functions - rounds the value down  
    @param  double array
    @return the result as an OperandToken
    */
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];

        result[REAL] = Math.floor(arg[REAL]);
        result[IMAG] = Math.floor(arg[IMAG]);
        
        return  result;                      
    }

}

/*
@GROUP
matrix
@SYNTAX
floor(value)
@DOC
Rounds the value of the first operand down to the nearest integer.
@EXAMPLES
floor(-5.5) = -6
floor(2.3)  = 2
@SEE
ceil, round
*/

