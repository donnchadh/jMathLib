package jmathlib.toolbox.general;

import jmathlib.core.interpreter.*;
import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.MathLibObject;
import jmathlib.core.functions.*;

/**External function for creating structures*/
public class struct extends ExternalFunction
{
    /**create a structure
    @param operands[n]   = name of field
    @param operands[n+1] = field value*/
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {
        MathLibObject obj;
        int length = operands.length;
        int start = 0;
        
        if(operands[0] instanceof MathLibObject)
        {
            ErrorLogger.debugLine("1st param structure");
            obj = new MathLibObject(((MathLibObject)operands[0]));
            start = 1;
        }
        else
        {
            obj = new MathLibObject();
        }

        for(int fieldno = start; fieldno < length; fieldno +=2)
        {
            String fieldName = operands[fieldno].toString();
            OperandToken value = null;
            if(length > fieldno + 1)
               value = ((OperandToken)operands[fieldno + 1]);
            else
                value = DoubleNumberToken.zero;
            
            obj.setField(fieldName, value);
        }
        return obj;
    }
}

/*
@GROUP
general
@SYNTAX
structure = STRUCT(variable1, value1, variable2, value2,...., variableN, valueN);
structure = STRUCT(structure, variable1, value1, variable2, value2,...., variableN, valueN);
@DOC
Creates a structured variable.
If the first paramater is a structure then the structure inherits it's values.
@EXAMPLES
<programlisting>
x=STRUCT("a", 1, "b", 2) = a = 1 : b = 2 :
y=STRUCT(x,"c",3) = a = 1 : b = 2 : c = 3 :
</programlisting>
@NOTES
@SEE
*/

