package jmathlib.core.tokens;

import jmathlib.core.interpreter.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;


/**Class representing multiplicaton and division operations within an expression*/
public class MulDivOperatorToken extends BinaryOperatorToken
{
    /**constant values*/
    static public final MulDivOperatorToken divide   = new MulDivOperatorToken('/');
    static public final MulDivOperatorToken multiply = new MulDivOperatorToken('*');


    /**Constructor taking the operator and priority
    @param _operator = the actual operator	  */
    public MulDivOperatorToken (char _operator)
    {
    	/**call the super constructor, type defaults to ttoperator and operands to 2*/
        super(_operator, MULDIV_PRIORITY);
    }

    /**evaluate the operator
    @param operands = the operators operands
    @return the result as an OperandToken*/
    public OperandToken evaluate(Token[] operands)
    {
        OperandToken result = null;

        OperandToken left = ((OperandToken)operands[0]);       
        if(left == null)
        	left = new DoubleNumberToken(0);
        	
        OperandToken right = ((OperandToken)operands[1]);
        if(right == null)
        	right = new DoubleNumberToken(0);

    	//try
    	//{
            OperandToken[] ops = {left, right}; //castOperands(left, right);			
    			
    	    //now evaluate op on left and right        
    	    if(value == '*')
    	    {
    	        result = ops[0].multiply(ops[1]);
    	    }
    	    else if (value == '/')
    	    {
    	        result = ops[0].divide(ops[1]);
    	    }
    	    else if (value == 'm')
    	    {
        		//scalar multiplication
        		result = ops[0].scalarMultiply(ops[1]);
    	    }	
    	    else if (value == 'd')
    	    {
        		//scalar division
        		result = ops[0].scalarDivide(ops[1]);
    	    }
            else if (value == 'L')
            {
                //left division
                result = ops[0].leftDivide(ops[1]);
            }
            else if (value == 'l')
            {
                //scalar left division
                result = ops[0].scalarLeftDivide(ops[1]);
            }
            else
                Errors.throwMathLibException("MulDiv: do not know operator");
            
            if(result == null)
            {
                //return origional expression
                result = new Expression(this, left, right);
            }
    	//}
    	//catch(Exception exception)
    	//{
        //    Errors.throwMathLibException("exception in muldiv");
    	//    result = null;
    	//}
        
        return result;
    }

    /**@return a string containing the operator and it's operands*/
    /*public String toString(OperandToken[] operands)
    {
    	if(value == '*')
    	{   		
    		if(operands[1] instanceof VariableToken)
    		{
    			return operands[0].toString() + " " + operands[1].toString();
    		}

    		if(operands[0] instanceof VariableToken)
    		{
    			return operands[1].toString() + " " +operands[0].toString();
    		}

        	return "(" + operands[0] + " " + value + " " + operands[1] + ")";
    	}
    	else
        	return "(" + operands[0] + " " + value + " " + operands[1] + ")";
    }*/
}
