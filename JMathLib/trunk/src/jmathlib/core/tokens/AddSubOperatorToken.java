package jmathlib.core.tokens;

//import MathLib.Interpreter.Errors;
import jmathlib.core.interpreter.RootObject;
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

		//try
		//{
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
	 	//}
	 	//catch(Exception exception)
	 	//{
        //    Errors.throwMathLibException("exception in addSub");
	 	//	result = null;
	 	//}

        return result;
    }
    
    /**trigonometric functions - calculate the sine of this token
    @param operands = the operator's operands
    @return the result as an OperandToken*/
    public OperandToken sin(OperandToken[] operands)
    {
    	//sin(x+y) = sin(x)cos(y) + cos(x)sin(y)
    	//sin(x-y) = sin(x)cos(y) - cos(x)sin(y)
    	//x = operands[1]
    	//y = operands[0]
    	
    	OperandToken result = null;
    	OperandToken left1   = new FunctionToken("cos", operands[1]);
    	OperandToken left2   = new FunctionToken("sin", operands[0]);

    	OperandToken right1  = new FunctionToken("cos", operands[0]);
    	OperandToken right2  = new FunctionToken("sin", operands[1]);
		
		OperandToken result1 = new Expression(new MulDivOperatorToken('*'), left1, left2);

		OperandToken result2 = new Expression(new MulDivOperatorToken('*'), right1, right2);

		result = new Expression(new AddSubOperatorToken(value), result1, result2);
		result = result.evaluate(null);
    	return result;
    }

    /**trigonometric functions - calculate the cosine of this token
    @param operands = the operator's operands
    @return the result as an OperandToken*/
    public OperandToken cos(OperandToken[] operands)
    {
    	//cos(x+y) = cos(x)cos(y) - sin(x)sin(y)
    	//cos(x-y) = cos(x)cos(y) + sin(x)sin(y)
    	OperandToken result = null;
    	OperandToken left1   = new FunctionToken("cos", operands[0]);
    	OperandToken left2   = new FunctionToken("cos", operands[1]);

    	OperandToken right1  = new FunctionToken("sin", operands[1]);
    	OperandToken right2  = new FunctionToken("sin", operands[0]);
		
		OperandToken result1 = new Expression(new MulDivOperatorToken('*'), left1, left2);
		OperandToken result2 = new Expression(new MulDivOperatorToken('*'), right1, right2);

		char opToken = '+';
		if(value == '+')
			opToken = '-';
			
		result = new Expression(new AddSubOperatorToken(opToken), result1, result2);
		result = result.evaluate(null);
    	return result;
    }

    /**trigonometric functions - calculate the tangent of this token
    @param operands = the operator's operands
    @return the result as an OperandToken*/
    public OperandToken tan(OperandToken[] operands)
    {
    	//tan(iy) = sin(iy) / cos(iy)
		OperandToken cosine = cos(operands);
		OperandToken sine   = sin(operands);
    	
    	OperandToken result = new Expression(new MulDivOperatorToken('/'), cosine, sine);
    	result = result.evaluate(null);
    	
        return result;   	
    }

    /**Standard functions - calculates the exponent
    @param operands = the operator's operands
    @return the result as an OperandToken*/
    public OperandToken exp(OperandToken[] operands)
    {
    	//exp(x+y) = exp(x) * exp(y)
    	OperandToken left   = new FunctionToken("exp", operands[0]);
    	OperandToken right  = new FunctionToken("exp", operands[1]);
    	char opToken = '*';
    	if(value == '-')
    		opToken = '/';
    		
    	OperandToken result = new Expression(new MulDivOperatorToken(opToken), left, right);

    	result = result.evaluate(null);
    	return result;
    }

    /**Standard functions - calculates the natural logarythm
    @param operands = the operator's operands
    @return the result as an OperandToken*/
    public OperandToken ln(OperandToken[] operands)
    {
    	return null;
    }

    /**Standard functions - calculates the logarythm
    @param operands = the operator's operands
    @return the result as an OperandToken*/
    public OperandToken log(OperandToken[] operands, RootObject arg)
    {
    	return null;
    }

	/**Standard functions - calculates the square root    
    @param operands = the operator's operands
    @return the result as an OperandToken*/
    public OperandToken sqrt(OperandToken[] operands)
    {
    	return null;
    }
    
}
