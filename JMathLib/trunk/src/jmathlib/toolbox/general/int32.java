package jmathlib.toolbox.general;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;

public class int32 extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 1 )
			throwMathLibException("int32: number of arguments !=1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("int32: only works on numbers");

        DoubleNumberToken num = (DoubleNumberToken)operands[0];
        
        int[] size = num.getSize();
        
        int n = num.getNumberOfElements();
        
        Int32NumberToken int32 = new Int32NumberToken(size, null, null);

        double re  = 0;
        double im  = 0;
        int    reI = 0;
        int    imI = 0;
        for (int i=0; i<n; i++)
        {

            re = num.getValueRe(i);
            im = num.getValueIm(i);
                
            if (re > Integer.MAX_VALUE)
                reI = Integer.MAX_VALUE;
            else if (re < Integer.MIN_VALUE)
                reI = Integer.MIN_VALUE;
            else
                reI = (int)re;

            if (im > Integer.MAX_VALUE)
                imI = Integer.MAX_VALUE;
            else if (im < Integer.MIN_VALUE)
                imI = Integer.MIN_VALUE;
            else
                imI = (int)im;

            int32.setValue(i, reI, imI);
            
        }
        
        return int32;
        
	} // end eval
}

/*
@GROUP
general
@SYNTAX
int16(x)
@DOC
converts a double array into an array of int16 (range -32768=-2^25 up to +32767=+2^15-1)
@EXAMPLES
<programlisting>

</programlisting>
@SEE
double, int8, uint8, uint16
*/

