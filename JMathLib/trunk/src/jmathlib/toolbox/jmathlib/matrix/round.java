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

        if (arg[REAL]>=0)
            result[REAL] = Math.floor(arg[REAL] + 0.5);
        else
            result[REAL] = Math.ceil(arg[REAL] - 0.5);
            
        if (arg[IMAG]>=0)
            result[IMAG] = Math.floor(arg[IMAG] + 0.5);
        else
            result[IMAG] = Math.ceil(arg[IMAG] - 0.5);

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

