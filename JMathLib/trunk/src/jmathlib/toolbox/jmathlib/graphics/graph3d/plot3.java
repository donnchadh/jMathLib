package jmathlib.toolbox.jmathlib.graphics.graph3d;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.graphics.*;
import jmathlib.core.interpreter.ErrorLogger;

/**An external function for 3 dimensional plots*/
public class plot3 extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands)
	{

		ErrorLogger.debugLine("plot3 evaluate. no of args: "+getNArgIn(operands));

        if (getNArgIn(operands) < 3)
			throwMathLibException("plot3: number of arguments < 3");

		if (!(operands[0] instanceof DoubleNumberToken) ||
            !(operands[1] instanceof DoubleNumberToken) ||
            !(operands[2] instanceof DoubleNumberToken)    )
            throwMathLibException("plot3: not numerical");
         
        // values of x-axis
        double[][] x = ((DoubleNumberToken)operands[0]).getReValues();
        int   xSizeX = ((DoubleNumberToken)operands[0]).getSizeX();
        int   xSizeY = ((DoubleNumberToken)operands[0]).getSizeY();

        // values of y-axis
        double[][] y = ((DoubleNumberToken)operands[1]).getReValues();
        int   ySizeX = ((DoubleNumberToken)operands[1]).getSizeX();
        int   ySizeY = ((DoubleNumberToken)operands[1]).getSizeY();

        // values of z-axis
        double[][] z = ((DoubleNumberToken)operands[2]).getReValues();
        int   zSizeX = ((DoubleNumberToken)operands[2]).getSizeX();
        int   zSizeY = ((DoubleNumberToken)operands[2]).getSizeY();


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

		char colorC     =' ';
		char markerC    =' ';
		char lineStyleC =' ';

		//ErrorLogger.debugLine("plot frame "+xSizeX);
		if ((xSizeX != ySizeX) ||
        	(xSizeX != zSizeX)   ) 
        	throwMathLibException("plot3: arguments have different sizes");


		// Look for style information
		if (getNArgIn(operands)==4)
		{
			if (operands[3] instanceof CharToken)
			{
				String s = ((CharToken)operands[3]).getValue();
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
				ErrorLogger.debugLine("plot: types: "+colorC+" "+markerC+" "+lineStyleC);
			}
		}

		// add Lines to current axes
        getGraphicsManager().getCurrentFigure().getCurrentAxes();
        getGraphicsManager().getCurrentFigure().convertCurrentAxesTo3DAxes();
		AxesObject axesOb = getGraphicsManager().getCurrentFigure().getCurrentAxes();
        if (axesOb instanceof Axes3DObject)
        	((Axes3DObject)axesOb).add3DLines(x, 
        									  y,
                                              z,
											  new Character(colorC).toString(),
                                              new Character(markerC).toString(),
                                              new Character(lineStyleC).toString());
        else
        	ErrorLogger.debugLine("plot3: eval: something wrong");
            
        // call repaint to initiate drawing of plot																	  lineStyleC);
		getGraphicsManager().getCurrentFigure().repaint();
        		

		return new DoubleNumberToken(1);
	}
}

/*
@GROUP
graphics
@SYNTAX
plot3(x-values, y-values, z-values, [graph settings])
@DOC
Plots a 3 dimensional graph of the supplied points.
@EXAMPLES
t = 0:0.5:100; 
plot3(SIN(t), COS(t), t/100);
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
plot, plotfunction
*/


