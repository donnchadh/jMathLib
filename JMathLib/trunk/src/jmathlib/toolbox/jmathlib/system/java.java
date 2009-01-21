package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.plugins.dynjava.JavaPlugin;

/**An example of an external function - it returns the first parameter*/
public class java extends ExternalFunction
{
	/**Executes the function - returning the first parameter
	@param operands - the array of parameters
	@return the result of the function as an OperandToken*/	
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{    
	    
        // load java plugin
	    globals.getPluginsManager().addPlugin("JavaPlugin");
        
        String result = ((JavaPlugin)globals.getPluginsManager().getPlugin("JavaPlugin")).executeJavaExpression(operands[0].toString());

	    return new CharToken(result);
	}
}



/*

java('console.displayText("hello world");')
hello world
ans = null

To access a JMathLib variable enter
x=5
JAVA('global.getVariable("x");')
ans = 5

*/