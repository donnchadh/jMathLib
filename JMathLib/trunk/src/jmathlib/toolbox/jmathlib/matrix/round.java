package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class round extends ExternalElementWiseFunction
{
    
    public round()
    {
        name = "round";
    }
    
    /**Standard functions - rounds the value to the nearest integer 
    @return the result as an OperandToken*/
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];

        result[REAL] = Math.rint(arg[REAL]);
        result[IMAG] = Math.rint(arg[IMAG]);
        return  result;                      
    }

}

/*
@GROUP
general
@SYNTAX
answer = round(value)
@DOC
Rounds a value to the nearest integer.
@EXAMPLES
round(2.2) = 2
round(5.5) = 6
@SEE
ceil, floor
*/

