package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class ln extends ExternalElementWiseFunction
{
    
    public ln()
    {
        name = "ln";
    }
    
    /**Calculates the logarithm of a complex number
    @param arg = the value as an array of double
    @return the result as an array of double*/ 
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];

        double temp = Math.pow(arg[REAL], 2) + Math.pow(arg[IMAG], 2);
        temp =  Math.sqrt(temp);
     
        result[REAL] = Math.log(temp);
        result[IMAG] = Math.atan2(arg[IMAG], arg[REAL]);
        
        return  result;                      
    }

}

/*
@GROUP
matrix
@SYNTAX
answer = ln(value)
@DOC
Returns the natural logarithm of value.
@EXAMPLES
<programlisting>
ln(2) = 0.6931471805599 
ln(10) = 2.30258509299
</programlisting>
@SEE
log
*/

