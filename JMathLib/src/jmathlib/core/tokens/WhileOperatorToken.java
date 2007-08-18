package jmathlib.core.tokens;

import jmathlib.core.interpreter.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;


/**Used to implement if-then-else operations within an expression*/
public class WhileOperatorToken extends CommandToken
{

	/**condition */
	OperandToken whileRelation;

	/** { code } to execute while the condition is true*/
	OperandToken whileCode;

	/**Constructor setting the whileRelation and whileCode
	@param _whileRelation = the test relationship
	@param _whileCode     = the code to execute while the test is true*/
	public WhileOperatorToken(OperandToken _whileRelation, OperandToken _whileCode)
	{
		whileRelation 	= _whileRelation;
		whileCode		= _whileCode;
	}


    /**evaluates the operator
    @param operands = the tokens parameters (not used)
    @return the result as an OperandToken*/
    public OperandToken evaluate(Token[] operands)
    {
		ErrorLogger.debugLine("Parser: While: evaluate");
	
		while(true)
		{

			// Check condition of while(...)
			OperandToken relationLine = ((OperandToken)whileRelation.clone());
ErrorLogger.debugLine("line = " + relationLine.toString());			
			OperandToken result = relationLine.evaluate(null);
            
			if (result instanceof DoubleNumberToken)
			{
				double[][] opValues	= ((DoubleNumberToken)result).getReValues(); 
				int        opSizeX 	= ((DoubleNumberToken)result).getSizeX();	
				int        opSizeY 	= ((DoubleNumberToken)result).getSizeY(); 
				boolean    cond		= false;	

				// Check if relation hold for at least one element
				for (int yy=0; yy<opSizeY; yy++) 
				{
					for (int xx=0; xx<opSizeX; xx++)
					{
						if (opValues[yy][xx] != 0.0)
						{
							//System.out.println("opValues "+opValues[yy][xx]);
							// At least one element is TRUE (!=0)
							cond = true;
							break;
						}
					}
					if (cond) break;
					
				}

				// if condition is false, then break while loop
				if (!cond) break;

				/* evaluate code */
				OperandToken code;
				if (cond)
				{
					OperandToken codeLine = ((OperandToken)whileCode.clone());
					ErrorLogger.debugLine("Parser: while number is true");
					code = codeLine.evaluate(null);
				}
			}
            else if (result instanceof LogicalToken)
            {
                boolean cond = ((LogicalToken)result).getValue(0);

                // if condition is false, then break while loop
                if (!cond) break;

                /* evaluate code */
                OperandToken code;
                if (cond)
                {
                    OperandToken codeLine = ((OperandToken)whileCode.clone());
                    ErrorLogger.debugLine("Parser: while boolean is true");
                    code = codeLine.evaluate(null);
                }

            }
            else
                Errors.throwMathLibException("While: unknown token");

        } // end while

		return null;
    }
    

    /**@return the operator as a string*/
    public String toString()
    {
        return "while";
    }

    
}
