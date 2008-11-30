package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class log extends ExternalElementWiseFunction
{
    
    public log()
    {
        name = "log";
    }
    
    /**Calculates the logarythm of a complex number
    @param arg = the value as an array of double
    @return the result as an array of double*/ 
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];

        double temp = Math.pow(arg[REAL], 2) + Math.pow(arg[IMAG], 2);
        temp        =  Math.sqrt(temp);
     
        result[REAL] = Math.log(temp);
        result[IMAG] = Math.atan2(arg[IMAG], arg[REAL]);
        
        return  result;                      
    }

}

/*
@GROUP
matrix
@SYNTAX
answer = log(value, base)
@DOC
Returns the logarithm of value to the base base.
@EXAMPLES
<programlisting>
</programlisting>
@SEE
ln
*/

