package jmathlib.toolbox.general;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.FunctionToken;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for performing functions*/
public class performfunction extends ExternalFunction
{
	/**Perform the named function on the operands
	@param operand[0] = the name of the function*/
	public OperandToken evaluate(Token[] operands)
	{
		FunctionToken function = null;

		if(operands[0] instanceof CharToken)
		{
			String name = ((CharToken)operands[0]).toString();
			function = new FunctionToken(name);
		}
		else if(operands[0] instanceof FunctionToken)
		{
			function = ((FunctionToken)operands[0]);
		}
		else
		{
			//error, unsupported type
		}
		
        OperandToken[] op = new OperandToken[operands.length-1];
        
		for(int operandNo = 0; operandNo < operands.length -1; operandNo++)
		{
			op[operandNo] = (OperandToken)(operands[operandNo + 1].clone());
		}
		
        function.setOperands(op);
        
		return function.evaluate(null);
	}
}

/*
@GROUP
general
@SYNTAX
PERFORMFUNCTION(function, parameters)
@DOC
Performs the function on the supplies parameters.
@EXAMPLES
PERFORMFUNCTION(ACOS, 1) = 0
PERFORMFUNCTION(MAX,1,3) = 3
@NOTES
@SEE
*/
