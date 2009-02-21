package jmathlib.toolbox.general;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

public class uint32 extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

        if (getNArgIn(operands) != 1 )
			throwMathLibException("uint32: number of arguments !=1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("uint32: only works on numbers");

        DoubleNumberToken num = (DoubleNumberToken)operands[0];
        
        int[] size = num.getSize();
        
        int n = num.getNumberOfElements();
        
        UInt32NumberToken uint32 = new UInt32NumberToken(size, null, null);

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

            uint32.setValue(i, reI, imI);
            
        }
        
        return uint32;
        
	} // end eval
}

/*
@GROUP
general
@SYNTAX
uint32(x)
@DOC
converts a double array into an array of uint8 (range 0 up to +255)
@EXAMPLES
<programlisting>

</programlisting>
@SEE
double, int16, int8, uint8, uint16
*/

/*
%!@testcase
%!  ml.executeExpression("a=uint32(88);");
%!  ml.executeExpression("b=class(a);");
%!  assertEquals( "uint32", ml.getString("b"));
%!
*/

