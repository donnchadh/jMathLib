package jmathlib.core.tokens;

import jmathlib.core.interpreter.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;


/**Class representing any unary operators in an expression*/
public class UnaryOperatorToken extends OperatorToken
{
    /**Constructor taking the operator and priority
     * @param _operator = the operator being constructed 
     */
    public UnaryOperatorToken(char _operator) 
    {
    	/**call the super constructor, type defaults to ttoperator and operands to 1*/
        super(0); 
        value = _operator;
    }

    /**evaluate the operator
    @param operands = the operator operands
    @return the result as an OperandToken*/
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
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
        	    // e.g. "a--" first return "a" then decrement by 1

                // check if operand is a variable (e.g. a--, bar--)
                if(operand instanceof VariableToken)
                {
                    // first: evaluate and return original value variable
                    result = operand.evaluate(null, globals);
                    
                    // second: decrease variable
                    OperandToken op = result.subtract(new DoubleNumberToken(1));
    
                    // save new variable value
                    String variable = ((VariableToken)operand).getName();
                    //getVariables().getVariable(variable).assign(op);
                    globals.getVariable(variable).assign(op);
                    
                    return result;
                }
                else if(operand instanceof NumberToken)
                {
                    // 4--
                    result = operand.subtract(new DoubleNumberToken(1));
                }
                else
                    Errors.throwMathLibException("UnaryOperatorToken --");

        		break;
        	}
        	case '+':
        	{
                // ++ operator
                // e.g. "a++" first return "a" then increment by 1


                // check if operand is a variable (e.g. a++, bar++)
                if(operand instanceof VariableToken)
                {    
                    // first: evaluate and return original value variable
            		result = operand.evaluate(null, globals);
            		
            		// second: increase variable
                    OperandToken op = result.add(new DoubleNumberToken(1));
    
                    // save new variable value
                    String variable = ((VariableToken)operand).getName();
            		//getVariables().getVariable(variable).assign(op);
                    globals.getVariable(variable).assign(op);
                    
                    return result;
                }
                else if(operand instanceof NumberToken)
                {
                    // 5++
                    result = operand.add(new DoubleNumberToken(1));
                }
                else
                    Errors.throwMathLibException("UnaryOperatorToken ++");

                break;
        	}
            default:
            {
                Errors.throwMathLibException("UnaryOperatorToken unknown value");
            }
    	}
        
     	return result;
    }

    /**
     * @return the operator as a string
     */
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
