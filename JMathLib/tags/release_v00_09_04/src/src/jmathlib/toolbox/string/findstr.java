package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.CharToken;

/**An external function for finding a string within another*/
public class findstr extends ExternalFunction
{
	/**finds shorter string within a longer one
	@param operands[0] = first string
	@param operands[1] = second string*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		OperandToken result = null;
        if (getNArgIn(operands) !=2)
            throwMathLibException("findstr: number of arguments !=2");

        //Check the parameters		
        if ( !(operands[0] instanceof CharToken))
            throwMathLibException("findstr: works only on strings");

        if ( !(operands[1] instanceof CharToken))
            throwMathLibException("findstr: works only on strings");

        String string1 = ((CharToken)operands[0]).toString();
        String string2 = ((CharToken)operands[1]).toString();
        
        int index = 0;
        int count = 0;
        double lastpos = 0;
        if(string1.length() < string2.length())
        {
            double[][] tempResults = new double[1][string2.length()];

            do
            {
                index = string2.indexOf(string1);
                
                if(index > -1)
                {
                    tempResults[0][count] = index + lastpos + 1;
                    lastpos = tempResults[0][count];
                    count++;
                    
                    string2 = string2.substring(index + 1, string2.length());
                    
                    jmathlib.core.interpreter.ErrorLogger.debugLine(string2);
                }
            }while(index > -1);
            
            double[][] results = new double[1][count];
            for(index = 0; index < count; index++)
            {
                results[0][index] = tempResults[0][index];
            }
            result = new DoubleNumberToken(results);
        }
        else
        {
            double[][] tempResults = new double[1][string1.length()];

            do
            {
                index = string1.indexOf(string2);
                
                if(index > -1)
                {
                    tempResults[0][count] = index + lastpos + 1;
                    lastpos = tempResults[0][count];
                    count++;
                    string1 = string1.substring(index + 1, string1.length());
                }
            }while(index > -1);
            
            double[][] results = new double[1][count];
            for(index = 0; index < count; index++)
            {
                results[0][index] = tempResults[0][index];
            }
            result = new DoubleNumberToken(results);
        }
		
		return result;
	}
}

/*
@GROUP
char
@SYNTAX
findstr(string1, string2)
@DOC
Finds all occcurences of the shorter string within the longer
@NOTES
.
@EXAMPLES
<programlisting>
findstr("this is a test string", "is") = [3, 6]
findstr("is", "this is a test string") = [3, 6]
</programlisting>
@SEE
strfind
*/

