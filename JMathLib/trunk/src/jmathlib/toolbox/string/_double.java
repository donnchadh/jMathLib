package jmathlib.toolbox.string;

/* This file is part or JMathLib 
 * author:  2006/05/16   
 * */

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;


/**An external function for changing strings into numbers */
public class _double extends ExternalFunction
{
    /**returns a matrix of numbers 
    * @param operands[0] = string (e.g. ["hello"]) 
    * @return a matrix of numbers                                */
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {

        // one operand 
        if (getNArgIn(operands)!=1)
            throwMathLibException("_double: number of input arguments != 1");

        if (operands[0] instanceof CharToken)
        {
            // get data from arguments
            String stringValue = ((CharToken)operands[0]).getValue();
    
            // convert string to array of bytes
            byte[] b = stringValue.getBytes();
            
            double[][] X = new double[1][b.length];
            
            // convert array of byte to array of double
            for (int i=0; i<b.length; i++)
            {
                X[0][i]= (double)b[i];
            } 
    
            return new DoubleNumberToken( X );
        }
        else if (operands[0] instanceof Int8NumberToken)
        {
            Int8NumberToken tok = (Int8NumberToken)operands[0];

            DoubleNumberToken d = new DoubleNumberToken( tok.getSize(), null, null);

            for (int i=0; i<tok.getNumberOfElements(); i++)
            {
                d.setValue(i, tok.getValueRe(i), tok.getValueIm(i));
            }
            
            return d;
            
        }
        else if (operands[0] instanceof UInt8NumberToken)
        {
            UInt8NumberToken tok = (UInt8NumberToken)operands[0];

            DoubleNumberToken d = new DoubleNumberToken( tok.getSize(), null, null);

            for (int i=0; i<tok.getNumberOfElements(); i++)
            {
                d.setValue(i, tok.getValueRe(i), tok.getValueIm(i));
            }
            
            return d;
            
        }
        else if (operands[0] instanceof DoubleNumberToken)
        {
            return (DoubleNumberToken)operands[0];
        }
        else
            throwMathLibException("_double: wrong type of argument");

        return null;
        
    } // end eval
}


/*
@GROUP
char
@SYNTAX
number = str2num( string )

@DOC
Convert strings into numbers

@EXAMPLES
str2num("hello 12")  returns [104, 101, 108, 108, 111, 32, 49, 50]


@NOTES

@SEE
num2str, char
*/