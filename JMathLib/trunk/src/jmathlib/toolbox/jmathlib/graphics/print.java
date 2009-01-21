package jmathlib.toolbox.jmathlib.graphics;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

import java.awt.print.*;

/**An external function for printing plots*/
public class print extends ExternalFunction 
{

    
	public OperandToken evaluate(Token[] operands, GlobalValues globals) 
	{

		debugLine("print evaluate");

        /*if (operands==null) 
		{
			// figure called with no arguments
			//getGraphicsManager().createNewFigure();

		}
        else
        {
	        if (getNArgIn(operands) != 1)
				throwMathLibException("print: number of arguments != 1");
            
	    	if (!(operands[0] instanceof NumberToken)) 
        		throwMathLibException("print: argument must be a number");

			int figureNumber  = (int)(((NumberToken)operands[0]).getValues()[0][0]); 

			getGraphicsManager().createNewFigure(figureNumber);
        }
		*/
        
        
        PrinterJob pjob = PrinterJob.getPrinterJob();
        PageFormat defaultPF = pjob.defaultPage();
        PageFormat pageformat = pjob.pageDialog(defaultPF);
        pjob.setPrintable(globals.getGraphicsManager().getCurrentFigure(), pageformat);
        
        // somebody hit cancel
        if (pageformat != defaultPF)
        {
            debugLine("print: pageformat changed");
            //return null;
        }
        
        boolean status = pjob.printDialog();
        
        if (!status)
        {
            debugLine("print: cancelled");
            return null;
        }
        
        try {
            pjob.print(); 
        }
        catch (PrinterException e)
        {
            debugLine("print: Printer Exception");
            globals.getInterpreter().displayText("print: Printer Exception");
        }
        
		//getGraphicsManager().getCurrentFigure().print();
		

		return null;
	}
}

/*
@GROUP
graphics
@SYNTAX
print
@DOC
Prints the current figure to a printer
@EXAMPLES
print
@SEE
plot, plot3, surf, xlabel, ylabel, zlabel, rotate, meshgrid, subplot, title, figure
*/

