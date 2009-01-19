package jmathlib.toolbox.general;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

public class int16 extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

        if (getNArgIn(operands) != 1 )
			throwMathLibException("int16: number of arguments !=1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("int16: only works on numbers");

        DoubleNumberToken num = (DoubleNumberToken)operands[0];
        
        int[] size = num.getSize();
        
        int n = num.getNumberOfElements();
        
        Int16NumberToken int16 = new Int16NumberToken(size, null, null);

        double re  = 0;
        double im  = 0;
        short  reI = 0;
        short  imI = 0;
        for (int i=0; i<n; i++)
        {

            re = num.getValueRe(i);
            im = num.getValueIm(i);
                
            if (re>32767)      //(2^15-1)
                reI = 32767;
            else if (re<-32768) // (-2^15)
                reI = -32768;
            else
                reI = (short)re;

            if (im>32767)
                imI = 32767;
            else if (im<-32768)
                imI = -32768;
            else
                imI = (short)im;

            int16.setValue(i, reI, imI);
            
        }
        
        return int16;
        
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
double, int8, uint8, uint16, uint32
*/

/*
%!@testcase
%!  ml.executeExpression("a=int16(8);");
%!  ml.executeExpression("b=class(a);");
%!  assertEquals( "int16", ml.getString("b"));
%!
*/
