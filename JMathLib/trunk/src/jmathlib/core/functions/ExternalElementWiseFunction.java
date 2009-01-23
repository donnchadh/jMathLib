/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:  Stefan Mueller (stefan@held-mueller.de) 
 * (c) 2008, 2009   
 */

package jmathlib.core.functions;

import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.Token;

/**Base class for all external function classes which work element wise*/
abstract public class ExternalElementWiseFunction extends ExternalFunction
{

    /**
     *  standard function for evaluation of general external functions
     *  @param operands
     *  @param pointer to the global values (interpreter, function manager, graphics,...)
     *  @return 
     */
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {

        // function works for one argument only
        if (getNArgIn(operands)!=1)
            throwMathLibException(name + " number of arguments < 1");

        // works on numbers only
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException(name + " only works on numbers");

        // get number token
        DoubleNumberToken numOp = (DoubleNumberToken)operands[0];
        
        // get dimension of number token (2dimensional, 3dim, ....)
        int[] dim = numOp.getSize();
        
        // ceate array of correct size with dimensions "dim"
        DoubleNumberToken num = new DoubleNumberToken(dim, null, null);
        
        // call element evaluation for all values inside the DoubleNumberToken
        for (int i=0; i< numOp.getNumberOfElements(); i++)
        {
            num.setValueComplex(i, evaluateValue( numOp.getValueComplex(i) ));
        }
        
        return num;
        

    } // end evaluate

    
    // all subclasses of this cluss MUST implement the method below
    //abstract public double[] evaluateValue(double[] complex);
    abstract public double[] evaluateValue(double[] complex);
    
}
