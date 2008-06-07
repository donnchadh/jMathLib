package jmathlib.toolbox.trigonometric;

import jmathlib.core.functions.ExternalElementWiseFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.toolbox.jmathlib.matrix.log;

public class atan extends ExternalElementWiseFunction
{
    
    public atan()
    {
        name = "atan";
    }
    
    /**Calculates the arctangent of a complex number
    @param arg = the value as an array of double
    @return the result as an array of double*/ 
    public double[] evaluateValue(double[] arg)
    {
         double result[] = new double[2];
         double[] temp = new double[2];
         //  atan(z)  =  -i/2 * log( (i-z)/(i+z) )

         double _re1, _im1;

         // result:  i.Minus(z) ...
         temp[REAL] = -arg[REAL];
         temp[IMAG] = 1 - arg[IMAG];

         // _1:      i.Plus(z) ...
         result[REAL] = arg[REAL];
         result[IMAG] = 1 + arg[IMAG];

         // result:  result.Div(_1) ...
         DoubleNumberToken num = new DoubleNumberToken();
         result = num.divide(temp, result);

         // _1:      result.log() ...
         log logFunc = new log();
         result = logFunc.evaluateValue(result);

         // result:  half_i.neg().Times(_2) ...
         double t = -0.5 * result[REAL];
         result[REAL] =   0.5 * result[IMAG];
         result[IMAG] =  t;
         return  result;        
     }

}

/*
@GROUP
trigonometric
@SYNTAX
atan(angle)
@DOC
Calculates the inverse tangent of angle.
@EXAMPLES
<programlisting>
atan(1) = 0.7853981633974483
atan(0) = 0
</programlisting>
@SEE
atan2, tan
*/

