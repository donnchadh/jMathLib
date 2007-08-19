package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.tokens.MatrixToken;

/**An external function for changing numbers into strings*/
public class num2str extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{
        // one operand 
        if (getNArgIn(operands)!=1)
            throwMathLibException("num2str: number of input arguments != 1");

        if ( !(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("num2str: works only on numbers");
        
        double[][]  x = ((DoubleNumberToken)operands[0]).getReValues();
        
        //JMH: The following limitation is not correct.
        if ( x.length != 1)
            throwMathLibException("num2str: works only on row vectors");
        
        if (x.length == 1 && x[0].length == 1) {
          String data = Double.toString(x[0][0]);
        
          //Cull the decimal point if the number is whole.
          if (data.endsWith(".0"))
            data = data.substring(0, data.length()-2);                   
             
          return new CharToken(data);            
        } else {
          CharToken[][] result = new CharToken[x.length][x[0].length];
          String data = null;
          for (int i=0;i<x.length;i++) {
            for (int j=0; j<x[i].length; j++) {
              data = Double.toString(x[i][j]);
        
              //Cull the decimal point if the number is whole.
              if (data.endsWith(".0"))
                data = data.substring(0, data.length()-2);    
              result[i][j] = new CharToken(data);
            }        
          }
          return new MatrixToken(result);
        }
	}
}

/*
@GROUP
char
@SYNTAX
string = num2str(number)
@DOC
Converts a number to a string.
@NOTES
@EXAMPLES
num2str([104, 101]) = ["104" "101"]
@SEE
str2num
*/

