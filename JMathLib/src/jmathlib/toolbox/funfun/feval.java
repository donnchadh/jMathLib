package jmathlib.toolbox.funfun;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.FunctionToken;
import jmathlib.core.tokens.CharToken;

/**An external function for evaluating functions*/
public class feval extends ExternalFunction
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
            throwMathLibException("feval: unknown type of argument");
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
FunFun
@SYNTAX
feval(function, parameters)
@DOC
evaluates the function on the supplies parameters.
@EXAMPLES
feval('cos', 1) = 0
feval("max",1,3) = 3
@NOTES
@SEE
*/
