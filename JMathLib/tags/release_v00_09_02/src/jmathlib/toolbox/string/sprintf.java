package jmathlib.toolbox.string;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.interpreter.*;

/**An external function for changing strings into numbers */
public class sprintf extends ExternalFunction
{
    // format string
    String formatS = "";
    
    //  convert string to array of bytes
    String retString = "";
    
    // position
    int pos = -1;
    
    // end of format string
    boolean EOL = false;
    
    // tokens
    Token[] tok;
    
    // nTok
    int nTok=-1;
    
    /**returns a matrix of numbers 
    * @param operands[0] = string (e.g. ["hello"]) 
    * @return a matrix of numbers                                */
    public OperandToken evaluate(Token[] operands)
    {

        // one operand 
        if (getNArgIn(operands)<2)
            throwMathLibException("sprintf: number of input arguments <2");

        if ( !(operands[0] instanceof CharToken))
            throwMathLibException("sprintf: format must be a string");

        // get format string
        formatS = ((CharToken)operands[0]).getValue();

        tok = new Token[operands.length-1];
        for (int i=0; i< (operands.length-1); i++)
            tok[i]= operands[i+1];
        
        // possible formats
        // %[Flags][width].[toleranz]typ
        // +, - 
        // d
        // i
        // u
        // o
        // x, X
        // f
        // e, E
        // a, A
        // g, G
        // c
        // s
        // N
        // P
        // %
        
        
        
        
        // convert array of byte to array of double
        while (EOL == false)
        {
            char c = getNextChar();
            switch(c)
            {
                case '%':
                {
                    parseFormat();
                    break;
                }
                default:
                {
                    retString= retString + c;
                    ErrorLogger.debugLine("sprintf: "+retString);
                }
            }
        } // end while

        return new CharToken( retString );

    } // end eval
    
    
    private void parseFormat()
    {
        while(!EOL)
        {
            char c = getNextChar();
            switch (c)
            {
                case '0': case '1': case '2': case '3':
                case '4': case '5': case '6': case '7':
                case '8': case '9': 
                {
                    ErrorLogger.debugLine("sprintf: Feature not implemented yet");
                    break;
                }
                case '.':
                {
                    ErrorLogger.debugLine("sprintf: Feature not implemented yet");
                    break;
                }
                case '+':
                {
                    ErrorLogger.debugLine("sprintf: Feature not implemented yet");
                    break;
                }
                case '-':
                {
                    ErrorLogger.debugLine("sprintf: Feature not implemented yet");
                    break;
                }
                case '#':
                {
                    ErrorLogger.debugLine("sprintf: Feature not implemented yet");
                    break;
                }
                case '%':
                {
                    retString= retString + c;
                    return;
                }
                case 'd': case'i': case 'u': case 'f':
                case 'e': case'E': case 'g': case 'G':
                {
                    nTok++;
                    retString = retString + tok[nTok].toString();
                    return;
                }
                case 's':
                {
                    nTok++;
                    retString= retString + tok[nTok].toString();
                    return;
                }
                default:
                {
                    ErrorLogger.debugLine("sprintf: Feature not implemented yet");
                }
        
            }
        } // end while
    }
    
    private char getNextChar()
    {
        if (pos<(formatS.length()-1))
        {
            pos++;
            if (pos == (formatS.length()-1))
               EOL = true;
            return  formatS.charAt(pos);
        }
        return ' ';
    }
    
    private char inspectNextChar()
    {
        if (pos < (formatS.length()-2))
            return formatS.charAt(pos+1);
        else
            return ' ';
    }
}


/*
@GROUP
char
@SYNTAX
number = sprintf( formatString, arg0, arg1, arg2, ... )
@DOC
Convert strings into numbers
@EXAMPLES
str2num("hello 12")  returns [104, 101, 108, 108, 111, 32, 49, 50]
@NOTES
.
@SEE
num2str
*/