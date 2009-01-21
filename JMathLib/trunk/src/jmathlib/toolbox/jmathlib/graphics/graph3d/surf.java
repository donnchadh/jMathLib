package jmathlib.toolbox.jmathlib.graphics.graph3d;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.graphics.*;
import jmathlib.core.graphics.axes.*;
import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for 3 dimensional surface plots*/
public class surf extends ExternalFunction
{

	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
    	double[][] x = {{0.0}};
        double[][] y = {{0.0}};
        double[][] z = {{0.0}};
        int   xSizeX;
        int   xSizeY;
        int   ySizeX;
        int   ySizeY;
        int   zSizeX;
        int   zSizeY;

		ErrorLogger.debugLine("surf evaluate. no of args: "+getNArgIn(operands));

        if (getNArgIn(operands) == 1)
		{
			if (!(operands[0] instanceof DoubleNumberToken) )
           	 	throwMathLibException("plot3: argument not numerical");
         	
            // values of z-axis
        	z      = ((DoubleNumberToken)operands[0]).getReValues();
        	zSizeX = ((DoubleNumberToken)operands[0]).getSizeX();
        	zSizeY = ((DoubleNumberToken)operands[0]).getSizeY();

			x = new double[zSizeY][zSizeX];
            for (int i=0; i<zSizeY; i++)
            {
            	for (int j=0; j<zSizeX; j++)
                {
                	x[i][j] = (double)j; 
                
            	}
            }
            
            y = new double[zSizeY][zSizeX];
            for (int i=0; i<zSizeY; i++)
            {
            	for (int j=0; j<zSizeX; j++)
                {
                	y[i][j] = (double)i; 
                
            	}
            }
            
		}
        else if (getNArgIn(operands) == 3)
		{
			if (!(operands[0] instanceof DoubleNumberToken) ||
           	 	!(operands[1] instanceof DoubleNumberToken) ||
           	 	!(operands[2] instanceof DoubleNumberToken)    )
           	 	throwMathLibException("plot3: not numerical");
         
	        // values of x-axis
   	     	x      = ((DoubleNumberToken)operands[0]).getReValues();
   	     	xSizeX = ((DoubleNumberToken)operands[0]).getSizeX();
   	     	xSizeY = ((DoubleNumberToken)operands[0]).getSizeY();

        	// values of y-axis
        	y      = ((DoubleNumberToken)operands[1]).getReValues();
        	ySizeX = ((DoubleNumberToken)operands[1]).getSizeX();
        	ySizeY = ((DoubleNumberToken)operands[1]).getSizeY();

        	// values of z-axis
        	z      = ((DoubleNumberToken)operands[2]).getReValues();
        	zSizeX = ((DoubleNumberToken)operands[2]).getSizeX();
        	zSizeY = ((DoubleNumberToken)operands[2]).getSizeY();

			if ((xSizeX != ySizeX) ||
        		(xSizeX != zSizeX)   ) 
        		throwMathLibException("surf: arguments have different sizes");
	
    	}
        else
        {
			throwMathLibException("surf: number of arguments < 3");
        }
     

		// add surface to current axes
        globals.getGraphicsManager().getCurrentFigure().getCurrentAxes();
        globals.getGraphicsManager().getCurrentFigure().convertCurrentAxesTo3DAxes();
		AxesObject axesOb = globals.getGraphicsManager().getCurrentFigure().getCurrentAxes();
        if (axesOb instanceof Axes3DObject)
        	((Axes3DObject)axesOb).addSurface(x, y, z);
        else
        	ErrorLogger.debugLine("surf: eval: something wrong");
            
        // call repaint to initiate drawing of plot																	  lineStyleC);
        globals.getGraphicsManager().getCurrentFigure().repaint();
        		

		return null;
	}
}

/*
@GROUP
Graph3d
@SYNTAX
SURF(matrix)
@DOC
Plots a 3 dimensional surface
@EXAMPLES
[x,y]=MESHGRID([-2:0.2:2],[-2:0.2:2])
SURF(sin(x).*cos(y))
@NOTES
@SEE
plot, plot3
*/

