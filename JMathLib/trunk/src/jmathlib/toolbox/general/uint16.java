package jmathlib.toolbox.general;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;

public class uint16 extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 1 )
			throwMathLibException("uint16: number of arguments !=1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("uint16: only works on numbers");

        DoubleNumberToken num = (DoubleNumberToken)operands[0];
        
        int[] size = num.getSize();
        
        int n = num.getNumberOfElements();
        
        UInt16NumberToken uint16 = new UInt16NumberToken(size, null, null);

        double re  = 0;
        double im  = 0;
        int    reI = 0;
        int    imI = 0;
        for (int i=0; i<n; i++)
        {

            re = num.getValueRe(i);
            im = num.getValueIm(i);
                
            if (re>65535)
                reI = 65535;
            else if (re<0)
                reI = 0;
            else
                reI = (int)re;

            if (im>65535)
                imI = 65535;
            else if (im<0)
                imI = 0;
            else
                imI = (int)im;

            uint16.setValue(i, reI, imI);
            
        }
        
        return uint16;
        
	} // end eval
}

/*
@GROUP
general
@SYNTAX
uint8(x)
@DOC
converts a double array into an array of uint16 
@EXAMPLES
<programlisting>

</programlisting>
@SEE
double, int16, int8, uint16
*/

/*
%!@testcase
%!  ml.executeExpression("a=uint16(88);");
%!  ml.executeExpression("b=class(a);");
%!  assertEquals( "uint16", ml.getString("b"));
%!
*/

