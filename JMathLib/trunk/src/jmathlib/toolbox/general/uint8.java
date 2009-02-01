package jmathlib.toolbox.general;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

public class uint8 extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

        if (getNArgIn(operands) != 1 )
			throwMathLibException("uint8: number of arguments !=1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("uint8: only works on numbers");

        DoubleNumberToken num = (DoubleNumberToken)operands[0];
        
        int[] size = num.getSize();
        
        int n = num.getNumberOfElements();
        
        UInt8NumberToken uint8 = new UInt8NumberToken(size, null, null);

        double re  = 0;
        double im  = 0;
        short   reI = 0;
        short   imI = 0;
        for (int i=0; i<n; i++)
        {

            re = num.getValueRe(i);
            im = num.getValueIm(i);
                
            if (re>255)
                reI = 255;
            else if (re<0)
                reI = 0;
            else
                reI = (short)re;

            if (im>255)
                imI = 255;
            else if (im<0)
                imI = 0;
            else
                imI = (short)im;

            uint8.setValue(i, reI, imI);
            
        }
        
        return uint8;
        
	} // end eval
}

/*
@GROUP
general
@SYNTAX
uint8(x)
@DOC
converts a double array into an array of uint8 (range 0 up to +255)
@EXAMPLES
<programlisting>

</programlisting>
@SEE
double, int16, int8, uint16, uint32
*/

/*
%!@testcase
%!  ml.executeExpression("a=uint16(88);");
%!  ml.executeExpression("b=class(a);");
%!  assertEquals( "uint16", ml.getString("b"));
%!
*/

