package jmathlib.core.interfaces;

import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.Expression;
import jmathlib.core.tokens.DataToken;

public interface TreeCallback 
{
	public OperandToken evaluateFunction(OperandToken token, String options);

	public OperandToken evaluateFunction(Expression token, String options);

	public OperandToken evaluateFunction(DataToken token, String options);	
}

