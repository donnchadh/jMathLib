package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class ceil extends ExternalElementWiseFunction
{
    
    public ceil()
    {
        name = "ceil";
    }
    
    /**Standard functions - rounds the value up   
    @return the result as a double array
    */
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];

        result[REAL] = Math.ceil(arg[REAL]);
        result[IMAG] = Math.ceil(arg[IMAG]);
        
        return  result;                      
    }

}

/*
@GROUP
@GROUP
general
@SYNTAX
ceil(value)
@DOC
Rounds the value of the first operand up to the nearest integer
@EXAMPLES
ceil(-5.5) = -5
ceil(2.3)   = 3
@SEE
floor, round
*/

