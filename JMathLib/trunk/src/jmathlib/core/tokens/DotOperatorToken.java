package jmathlib.core.tokens;

import jmathlib.core.interpreter.*;

/**Used to implement object orientated access of methods and arguments*/
public class DotOperatorToken extends OperatorToken
{
	/**Default Constructor - creates an operator with the value set to ' '*/
    public DotOperatorToken()
    {
        super(); 
    }

    /**evaluates the operator*/
    public OperandToken evaluate(Token[] operands)
    {
    	ErrorLogger.debugLine("DotOperatorToken: evaluate");
        
        //  syntax is <left><dot><right>  (e.g. a.b)
        Token left  = operands[0];
        Token right = operands[1];
        
		left = left.evaluate(null);
        
        // not needed. is done by variable token
        // check if left is a variable (e.g. a.abc, where "a" is a structure)
        //if(operands[0] instanceof VariableToken)
        //{
        //    String objName      = ((VariableToken)operands[0]).getName();
        //    String fieldName    = operands[1].toString();
        //    
        //    MathLibObject obj   = (MathLibObject)(getVariables().getVariable(objName).getData()); 
        //    OperandToken  op    = obj.getFieldData(fieldName);
		//
        //    ErrorLogger.debugLine("DotOperatorToken getting object " + objName);
        //    return op.evaluate(null);
        //}
        
        // (e.g. a.sin() or a.getColor() or 2.sin or 3.sin() )
        String name = "";
        
        if (right instanceof FunctionToken)
        { 
            name = ((FunctionToken)right).getName();
        }        
	    
        
        if (!name.equals(""))
        {
	        try
	   	 	{
	    	    //check if a function with this name exists
	    	    if (getFunctionManager().findFunctionByName(name) != null)
				{
	                ErrorLogger.debugLine("parser value.function");
		        	FunctionToken func = new FunctionToken(name, (OperandToken)left);
	     
	    	        return func.evaluate(null);
				}
	        }
	    	catch(Exception e){}
		}
        
        //if(function != null)
        //{
        //}
        //else
        //{
        //    String firstParam = operandStack.pop().toString();
        //    ErrorLogger.debugLine("parser value.field");
        //    OperandToken tree = new VariableToken(token.toString(), firstParam);
        //    return tree;
        //}

        return null;
    }
    

    /**Convert the operator to a string*/
    public String toString()
    {
		return ".";
    }
    
}
