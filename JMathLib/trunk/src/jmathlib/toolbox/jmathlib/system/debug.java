package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.interpreter.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.tokens.OperandToken;

/**Display the debug information of an expression*/
public class debug extends ExternalFunction
{
	/**Executes an expression, displaying the parse tree.
	@param operand[0] = the string containing the expression
	@return the result of the expression*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		String answer = "";
		if(operands[0] instanceof CharToken)
		{
			Parser p = new Parser();
		
			String expression = ((CharToken)operands[0]).toString();
			// separate expression into tokens and return tree of expressions
            OperandToken expressionTree = p.parseExpression(expression);

			// open a tree to show the expression-tree for a parsed command
            jmathlib.tools.treeanalyser.TreeAnalyser treeAnalyser = new jmathlib.tools.treeanalyser.TreeAnalyser(expressionTree);

	        OperandToken answerToken = expressionTree.evaluate(null, globals);
			//while(answerToken != null)
			//{        
	        	if(answerToken != null)
	        	{
		            //storeAnswer(answerToken);
	
					//if(answerToken.display)
		            //	answer += answerToken.toString() + "\n";

		            answerToken = expressionTree.evaluate(null, globals);
		          
		        }
	        //}
		}

		return new CharToken(answer);				
	}
}

/*
@GROUP
system
@SYNTAX
DEBUG(expression)
@DOC
This evaluates expression, displaying the op tree which is created.
@NOTES
@EXAMPLES
DEBUG("3*5")=15
@SEE
*/
