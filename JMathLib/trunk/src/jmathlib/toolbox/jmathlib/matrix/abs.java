package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class abs extends ExternalElementWiseFunction
{
    
    public abs()
    {
        name = "abs";
    }
    
    /**Standard functions - absolute value  
     * @param double array
     * @return the result as a double array
     */
    public double[] evaluateValue(double[] arg)
    {
        double[] result = new double[2];

        if (arg[IMAG]==0)
        {
            result[REAL] = Math.abs(arg[REAL]);
            result[IMAG] = 0;
        }
        else
        {
            result[REAL] = Math.sqrt(arg[REAL]*arg[REAL] + arg[IMAG]*arg[IMAG]);
            result[IMAG] = 0;
        }
        
        return  result;                      
    }

}

/*
@GROUP
matrix
@SYNTAX
abs(value)
@DOC
Returns the absolute positive value of value.
@NOTES
@EXAMPLES
<programlisting>
abs(-5)     = 5
abs(2)      = 2
abs(3 + 4I) = 5
</programlisting>
@SEE
sign, angle
*/

