package jmathlib.toolbox.jmathlib.graphics.graph2d;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.interpreter.ErrorLogger;

/**An external function for 2 dimensional plots*/
public class plot extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands)
	{

		ErrorLogger.debugLine("plot evaluate");

        if (getNArgIn(operands) == 0)
			throwMathLibException("plot: number of arguments > 0");
            
	    if (!(operands[0] instanceof DoubleNumberToken)) 
        	throwMathLibException("plot: first argument must be a number");

        double[][] x = ((DoubleNumberToken)operands[0]).getReValues();
        int   xSizeX = ((DoubleNumberToken)operands[0]).getSizeX();
        int   xSizeY = ((DoubleNumberToken)operands[0]).getSizeY();

        double[][] y;
        int ySizeX;
        int ySizeY;


		//    color            linestyle   marker               
        //  y   yellow     -     solid    .     point             
        //  m   magenta    :     dotted   o     circle             
        //  c   cyan       -.    dashdot  x     x-mark              
        //  r   red        --    dashed   +     plus                
        //  g   green                     *     star
        //  b   blue                      s     square
        //  w   white                     d     diamond
        //  k   black                     v     triangle (down)
        //                                ^     triangle (up)
        //                                <     triangle (left)
        //                                >     triangle (right)
        //                                p     pentagram
        //                                h     hexagram

		char colorC     ='r';
		char markerC   = ' ';
		char lineStyleC =' ';

        if (operands.length >= 2)
        {
            if (!(operands[1] instanceof DoubleNumberToken)) return null;

			y    	= ((DoubleNumberToken)operands[1]).getReValues(); 
			ySizeX 	= ((DoubleNumberToken)operands[1]).getSizeX();	
			ySizeY 	= ((DoubleNumberToken)operands[1]).getSizeY();

			//ErrorLogger.debugLine("plot frame "+xSizeX);
			if ((xSizeX != ySizeX) || (xSizeY!=1)) return null;


			// Look for style information
			if (operands.length==3)
			{
				if (operands[2] instanceof CharToken)
				{
					String s = ((CharToken)operands[2]).getValue();
					boolean dashSwitch = false;
					for (int n=0; n<s.length(); n++)
					{
						char c = s.charAt(n);
						switch (c)
						{
							case 'y':
							case 'm':
							case 'c':
							case 'r':
							case 'g':
							case 'b':
							case 'w':
							case 'k': colorC = c;
									  break;
							case '-': lineStyleC = c;
									  if (dashSwitch)
									  {
										  // -- (dashed)
									      lineStyleC = 'd';
										  break;
									  }
									  dashSwitch = true;
									  break;
							case ':': lineStyleC = c;
									  break;
							case '.':
									  if (dashSwitch)
									  {
										  // -. (dash-dotted)
									      lineStyleC = '.';
										  break;
									  }
							case 'o':
							case 'x':
							case '+':
							case '*':
							case 's':
							case 'd':
							case 'v':
							case '^':
							case '<':
							case '>':
							case 'p':
							case 'h': markerC = c;
									  break;
							default:
						}
					}
				}
			}

            ErrorLogger.debugLine("plot: types: "+colorC+" "+markerC+" "+lineStyleC);

			getGraphicsManager().getCurrentFigure().getCurrentAxes().addLines(x[0],y,
																			  new Character(colorC).toString(),
																			  new Character(markerC).toString(),
                                                                              new Character(lineStyleC).toString());
			getGraphicsManager().getCurrentFigure().repaint();
        }
        else
        {
            // plot was called with only one argument e.g.: plot(matrix)
            double[][] xx = new double[1][x[0].length];
            for(int i=0; i<x[0].length; i++) xx[0][i]=(double)(i+1);

	  		// changed order
			getGraphicsManager().getCurrentFigure().getCurrentAxes().addLines(xx[0],x,
                                                                             new Character(colorC).toString(),
                                                                             new Character(markerC).toString(),
                                                                             new Character(lineStyleC).toString());
			getGraphicsManager().getCurrentFigure().repaint();
        }


		return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
PLOT(x-values, y-values, graph settings)
@DOC
Plots a graph of the supplied points.
@EXAMPLES
<programlisting>
plot([1,2,3], rand(3), 'b')
</programlisting>
@NOTES
<programlisting>
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
</programlisting>
@SEE
plotfunction, plot3
*/

