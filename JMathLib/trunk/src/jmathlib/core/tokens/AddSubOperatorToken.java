package jmathlib.core.tokens;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;

/**Used to implement addition and subtraction operations within an expression*/
public class AddSubOperatorToken extends BinaryOperatorToken
{
    /**constant values*/
    //static public final AddSubOperatorToken add      = new AddSubOperatorToken('+');
    //static public final AddSubOperatorToken subtract = new AddSubOperatorToken('-');

    /**Constructor taking the operator and priority
    @param _operator = the actual operator		   */
    public AddSubOperatorToken (char _operator)
    {
    	/**call the super constructor, type defaults to ttoperator and operands to 2*/
        super(_operator, ADDSUB_PRIORITY);
    }

    /**evaluates the operator
    @param operands = the operators operands
    @return the result of the operation as an OperandToken*/
    public OperandToken evaluate(Token[] operands)
    {
        OperandToken result = null;

        OperandToken left = ((OperandToken)operands[0]);       
        if(left == null)
        	left = new DoubleNumberToken(0);
        	
        OperandToken right = ((OperandToken)operands[1]);
        if(right == null)
        	right = new DoubleNumberToken(0);

        OperandToken[] ops = {left, right}; //castOperands(left, right);			
        //now evaluate op on left and right        
        if(value == '+')
        {
        	result = ops[0].add(ops[1]);
        }
        else
        {
            result = ops[0].subtract(ops[1]);
        }
        
        if(result == null)
        {
            //return origional expression
            result = new Expression(this, left, right);
        }

        return result;
    }
    
    
}
