package jmathlib.toolbox.jmathlib.graphics.graph2d;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.functions.Function;

/**An external function for 2 dimensional plots of a function
uses the classes linspace, PerformFunction and plot*/
public class plotfunction extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{
		FunctionToken token = null;
		Function function = null;

        if (getNArgIn(operands) < 3)
			throwMathLibException("plot function: number of arguments < 3");

		ErrorLogger.debugLine("plot-function evaluate");

		//first generate the list of x values
		Token[] parameters = new Token[3];
		parameters[0] = operands[1];
		parameters[1] = operands[2];
		
		if(operands.length <= 3 || operands[3] == null)
			parameters[2] = new DoubleNumberToken(100);
		else
		{
			if(operands[3] instanceof DoubleNumberToken)
				parameters[2] = operands[3];
			else
			{
				if(operands.length > 4 && operands[4] != null && operands[4] instanceof DoubleNumberToken)
					parameters[2] = operands[4];
				else
					parameters[2] = new DoubleNumberToken(100);				
			}
		}
		

		try
		{
			token = new FunctionToken("linspace");
			function = getFunctionManager().findFunction(token);
		}
		catch(java.lang.Exception e)
		{}

		//then create the list of y values				
		OperandToken vector = function.evaluate(parameters);
		
		OperandToken modVector = ((OperandToken)vector.clone());
		parameters[0] = operands[0];
		parameters[1] = modVector;
		
		try
		{
			token = new FunctionToken("PerformFunction");
			function = getFunctionManager().findFunction(token);
		}
		catch(java.lang.Exception e)
		{}

		modVector = function.evaluate(parameters);

		//then draw the graph
		parameters[0] = vector;
		parameters[1] = modVector;

		if(operands.length > 3 && operands[3] instanceof CharToken)
			parameters[2] = operands[3];
        else if(operands.length > 4 && operands[4] instanceof CharToken)
			parameters[2] = operands[4];

		try
		{
			token = new FunctionToken("plot");
			function = getFunctionManager().findFunction(token);
		}
		catch(java.lang.Exception e)
		{}
	
        function.evaluate(parameters);
		return null; //function.evaluate(parameters);
	}
}

/*
@GROUP
graphics
@SYNTAX
PLOTFUNCTION(function, start, end, [no of points], [graph options])
@DOC
plots a graph of the function between the start and end points. The number of points defaults to 100.
@EXAMPLES
PLOTFUNCTION("SIN", 0, 4, 20)
@NOTES
graph settings
color
y   yellow
m   magenta
c   cyan         
r   red           
g   green                     
b   blue                      
w   white                     
k   black                     

linestyle   
-     solid
:     dotted
-.    dashdot
--    dashed

marker             
.     point       
o     circle                     
x     x-mark      
+     plus                    
*     star    
s     square
d     diamond
v     triangle (down)
^     triangle (up)
&lt;     triangle (left)
&gt;     triangle (right)
p     pentagram
h     hexagram
@SEE
plot, plot3
*/


