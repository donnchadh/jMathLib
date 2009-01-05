package jmathlib.core.tokens;

import jmathlib.core.interpreter.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;


/**Used to implement for loops within an expression*/
public class ForOperatorToken extends CommandToken
{

	/**initialisation */
	OperandToken forInitialisation;

	/**condition*/
	OperandToken forRelation;
	
	/**increment*/
	OperandToken forIncrement;

	/** { code } to execute For the condition is true*/
	OperandToken forCode;

	/**Constructor setting the ForRelation and ForCode
	@param _ForInitialisation 	= the test start values
	@param _ForRelation     	= the test relationship
	@param _ForIncrement 		= the test increment
	@param _ForCode     = the code to execute For the test is true*/
	public ForOperatorToken(OperandToken _forInitialisation,
    						OperandToken _forRelation, 
                            OperandToken _forIncrement, 
                            OperandToken _forCode)
	{
		forInitialisation 	= _forInitialisation;
		forRelation 		= _forRelation;
		forIncrement 		= _forIncrement;
		forCode				= _forCode;
	}

	public OperandToken getForInitialisation()
	{
		return forInitialisation;
	}

	public OperandToken getForRelation()
	{
		return forRelation;	
	}

	public OperandToken getForIncrement()
	{
		return forIncrement;
	}

	public OperandToken getForCode()
	{
		return forCode;
	}

    /**evaluates the operator
    @param operands = the tokens parameters (not used)
    @return the result as an OperandToken*/
    public OperandToken evaluate(Token[] operands)
    {
		ErrorLogger.debugLine("Parser: For: evaluate");

		if(forRelation == null)
		{
			vectorFor();
		}
		else
		{	
			OperandToken intialisationLine = ((OperandToken)forInitialisation.clone());
			intialisationLine.evaluate(null);
			
			while(true)
			{
	
				// Check condition of For(...)
				OperandToken relationLine = ((OperandToken)forRelation.clone());
				OperandToken result       = relationLine.evaluate(null);
                
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
	
					// if condition is false, then break For loop
					if (!cond) break;
	
					/* evaluate code */
					OperandToken code;
					if (cond)
					{
						OperandToken codeLine = ((OperandToken)forCode.clone());
						ErrorLogger.debugLine("Parser: for number is true");
						code = codeLine.evaluate(null);
	
						//evaluate increment code
						OperandToken incrementLine = ((OperandToken)forIncrement.clone());
						incrementLine.evaluate(null);
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
                        OperandToken codeLine = ((OperandToken)forCode.clone());
                        ErrorLogger.debugLine("Parser: for boolean is true");
                        code = codeLine.evaluate(null);
    
                        //evaluate increment code
                        OperandToken incrementLine = ((OperandToken)forIncrement.clone());
                        incrementLine.evaluate(null);
                    }

                }
                else
                    Errors.throwMathLibException("For: unknown token");

			} // end while
		}

		return null;
    }
    

    /**@return the operator as a string*/
    public String toString()
    {
        return "For";
    }

    
	/**evaluate for loop defined with a vector*/
	private void vectorFor()
	{
		ErrorLogger.debugLine("vector for " + forInitialisation.toString());
		
		if(forInitialisation instanceof Expression)
		{
			Expression forExpression = ((Expression)forInitialisation);
			if(forExpression.getData() instanceof AssignmentOperatorToken)
			{
				ErrorLogger.debugLine("vector for assignmentop");
				
				if(forExpression.getChild(0) instanceof VariableToken)
				{
					ErrorLogger.debugLine("vector for evaluating 1");
					VariableToken variableToken = ((VariableToken)forExpression.getChild(0));
					Variable variable = createVariable(variableToken.getName());

					DoubleNumberToken   vector = null;
					Token child = forExpression.getChild(1);
					if(child instanceof DoubleNumberToken)
					{
						ErrorLogger.debugLine("vector for evaluating 2");
						vector   = ((DoubleNumberToken)child);
					}
					else if(child instanceof Expression)
					{
						ErrorLogger.debugLine("vector for evaluating 3");
						OperandToken childOp = (OperandToken)child.clone();
                        childOp = childOp.evaluate(null);
                        ErrorLogger.debugLine("for op "+ childOp);
                        
                        //child = ((Expression)child).getChild(1);
						if(childOp instanceof DoubleNumberToken)
						{
						    ErrorLogger.debugLine("vector for evaluating 4");
							vector   = ((DoubleNumberToken)childOp);						
							ErrorLogger.debugLine(vector.toString());
						}
					}
						
					int sizeX = vector.getSizeX();
					int sizeY = vector.getSizeY();
					
					double[][] values = vector.getReValues();
					
					for(int yy = 0; yy < sizeY; yy++)
					{
						for(int xx = 0; xx < sizeX; xx++)
						{
							DoubleNumberToken value = new DoubleNumberToken(values[yy][xx]);
							
							variable.assign(value);
							
							Expression exp = ((Expression)forCode.clone());
							exp.evaluate(null);
						}
					}
				}
			}
		}
	}	
}
