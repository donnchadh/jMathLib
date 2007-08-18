package jmathlib.core.tokens;

import jmathlib.core.interpreter.*;

/**Class representing all power operators used in an expression*/
public class PowerOperatorToken extends BinaryOperatorToken
{

    /**Constructor taking the operator and priority
    @param _operator = the actual operator being created */
    public PowerOperatorToken (char _operator)
    {
    	/**call the super constructor, type defaults to ttoperator and operands to 2*/
        super(_operator, POWER_PRIORITY);
    }

    /**evaluates the operator
    @param operands = the operators operands
    @return the result as an OperandToken*/
    public OperandToken evaluate(Token[] operands)
    {
        OperandToken result = null;

        OperandToken left = ((OperandToken)operands[0]); 
        
        if(left == null)
            Errors.throwMathLibException("PowerOperatorToken left null");
        	
        OperandToken right = ((OperandToken)operands[1]);
        if(right == null)
            Errors.throwMathLibException("PowerOperatorToken right null");

        //now evaluate op on left and right        
        if(value == 'm')
        {
            result = left.mPower(right);
        }
        else if (value=='p')
        {
            // e.g. 1.^[1,2,3]  or [1,2,3].^4
            result = left.power(right);
        }
        else
            Errors.throwMathLibException("PowerOperatorToken unknown power");


        return result;
    }
}
