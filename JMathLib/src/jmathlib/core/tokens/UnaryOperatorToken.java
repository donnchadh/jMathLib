package jmathlib.core.tokens;

import jmathlib.core.interpreter.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;


/**Class representing any unary operators in an expression*/
public class UnaryOperatorToken extends OperatorToken
{
    /**Constructor taking the operator and priority
    @param _operator = the operator being constructed */
    public UnaryOperatorToken(char _operator) 
    {
    	/**call the super constructor, type defaults to ttoperator and operands to 1*/
        super(0); 
        value = _operator;
    }

    /**evaluate the operator
    @param operands = the operator operands
    @return the result as an OperandToken*/
    public OperandToken evaluate(Token[] operands)
    {
        OperandToken result = null;

		OperandToken operand = ((OperandToken)operands[0]);

		//now evaluate op on left
     	switch(value)
    	{
        	case '!':
        	{
           	 	result = operand.factorial();
        		break;
        	}
        	case '\'':
        	{
           	 	result = operand.transpose();
        		break;
        	}
            case 't':
            {
                result = operand.ctranspose();
                break;
            }
        	case '-':
        	{
                // -- operator

                // check if operand is a variable (e.g. a--, bar--)
                if(!(operand instanceof VariableToken))
                    Errors.throwMathLibException("UnaryOperatorToken --");
                
                // first: evaluate and return original value variable
                result = operand.evaluate(null);
                
                // second: decrease variable
                OperandToken op = result.subtract(new DoubleNumberToken(1));

                // save new variable value
                String variable = ((VariableToken)operand).getName();
                //getVariables().getVariable(variable).assign(op);
                getVariable(variable).assign(op);

        		break;
        	}
        	case '+':
        	{
                // ++ operator

                // check if operand is a variable (e.g. a++, bar++)
                if(!(operand instanceof VariableToken))
                    Errors.throwMathLibException("UnaryOperatorToken ++");
                
                // first: evaluate and return original value variable
        		result = operand.evaluate(null);
        		
        		// second: increase variable
                OperandToken op = result.add(new DoubleNumberToken(1));

                // save new variable value
                String variable = ((VariableToken)operand).getName();
        		//getVariables().getVariable(variable).assign(op);
                getVariable(variable).assign(op);

                break;
        	}
            default:
            {
                Errors.throwMathLibException("UnaryOperatorToken unknown value");
            }
    	}
        
        //if(result == null)
        //{
        //    //return origional expression
        //    result = new Expression(this, operand);
        //}
        return result;
    }

    /**@return the operator as a string*/
    public String toString()
    {
        if (value=='-')
            return "--";
        else if (value=='+')
            return "++";
        else
            return String.valueOf(value);
    }

}
