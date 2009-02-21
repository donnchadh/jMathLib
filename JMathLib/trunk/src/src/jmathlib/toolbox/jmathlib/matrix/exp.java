package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class exp extends ExternalElementWiseFunction
{
    
    public exp()
    {
        name = "exp";
    }
    
    /**Calculates the exponent of a complex number
    @param arg = the value as an array of double
    @return the result as an array of double*/ 
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];

        double scalar   = Math.exp(arg[REAL]);         // e^ix = cis x
        
        result[REAL]    = scalar * Math.cos(arg[IMAG]);
        result[IMAG]    = scalar * Math.sin(arg[IMAG]);
        
        return  result;                      
    }

}

/*
@GROUP
general
@SYNTAX
exp(value)
@DOC
.
@EXAMPLES
exp(-5.5) = -5
exp(2.3)   = 3
@SEE
log, ln
*/

