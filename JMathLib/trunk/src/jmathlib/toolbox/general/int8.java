package jmathlib.toolbox.general;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

public class int8 extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

        if (getNArgIn(operands) != 1 )
			throwMathLibException("int8: number of arguments !=1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("int: only works on numbers");

        DoubleNumberToken num = (DoubleNumberToken)operands[0];
        
        int[] size = num.getSize();
        
        int n = num.getNumberOfElements();
        
        Int8NumberToken int8 = new Int8NumberToken(size, null, null);

        double re  = 0;
        double im  = 0;
        byte   reI = 0;
        byte   imI = 0;
        for (int i=0; i<n; i++)
        {

            re = num.getValueRe(i);
            im = num.getValueIm(i);
                
            if (re>127)
                reI = 127;
            else if (re<-128)
                reI = -128;
            else
                reI = (byte)re;

            if (im>127)
                imI = 127;
            else if (im<-128)
                imI = -128;
            else
                imI = (byte)im;

            int8.setValue(i, reI, imI);
            
        }
        
        return int8;
        
	} // end eval
}

/*
@GROUP
general
@SYNTAX
int8(x)
@DOC
converts a double array into an array of int8 (range -128 up to +127)
@EXAMPLES
<programlisting>

</programlisting>
@SEE
double, int16, uint8, uint16, uint32
*/

/*
%!@testcase
%!  ml.executeExpression("a=int8(88);");
%!  ml.executeExpression("b=class(a);");
%!  assertEquals( "int8", ml.getString("b"));
%!
*/
