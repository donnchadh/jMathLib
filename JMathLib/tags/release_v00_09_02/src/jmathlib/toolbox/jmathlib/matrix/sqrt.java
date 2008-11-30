package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.functions.ExternalElementWiseFunction;

public class sqrt extends ExternalElementWiseFunction
{
    
    public sqrt()
    {
        name = "sqrt";
    }
    
    /**Calculates the sqrt of a complex number
    @param arg = the value as an array of double
    @return the result as an array of double*/ 
    public double[] evaluateValue(double[] arg)
    {
        
        // with thanks to Jim Shapiro <jnshapi@argo.ecte.uswc.uswest.com>
        // adapted from "Numerical Recipies in C" (ISBN 0-521-43108-5)
        // by William H. Press et al

        double[] result = new double[2];
        double   re     = arg[REAL];
        double   im     = arg[IMAG];

        double temp = Math.pow(re, 2) + Math.pow(im, 2);
        double mag  = Math.sqrt(temp);

        if (mag > 0.0) 
        {
            if (re > 0.0) 
            {
                temp =  Math.sqrt(0.5 * (mag + re));

                re =  temp;
                im =  0.5 * im / temp;
            } 
            else 
            {
                temp =  Math.sqrt(0.5 * (mag - re));

                if (im < 0.0) 
                {
                    temp =  -temp;
                }

                re =  0.5 * im / temp;
                im =  temp;
            }
        } 
        else 
        {
            re =  0.0;
            im =  0.0;
        }
        result[REAL] = re;
        result[IMAG] = im;
        
        return result;
    }


}

/*
@GROUP
general
@SYNTAX
answer = sqrt(value)
@DOC
Returns the sqrt of a value.
@EXAMPLES
sqrt(4) = 2
sqrt(9) = 3
@NOTES
@SEE
angle, abs
*/

