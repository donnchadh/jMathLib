package jmathlib.core.functions;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.Token;

/**Base class for all external function classes which work element wise*/
abstract public class ExternalElementWiseFunction extends ExternalFunction
{

    /**
     *  standard function for evaluation of general external functions
     *  @param operands
     *  @return 
     */
    public OperandToken evaluate(Token[] operands)
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
