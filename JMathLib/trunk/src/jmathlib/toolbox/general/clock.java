package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

import java.util.Date;
import java.util.Calendar;

/**External function to return the date and time*/
public class clock extends ExternalFunction
{
	/**@return the current date and time as a 6 by 1 vector containing
	[year month day hours minutes seconds]*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		Date now = new Date();
		
		Calendar calendarInst = Calendar.getInstance();
		calendarInst.setTime(now);
		
		double[][] datetime = new double[1][6];
		datetime[0][0]  = calendarInst.get(Calendar.YEAR);
		datetime[0][1]  = calendarInst.get(Calendar.MONTH) + 1;
		datetime[0][2]  = calendarInst.get(Calendar.DATE);
		
		datetime[0][3]  = calendarInst.get(Calendar.HOUR);
		datetime[0][4]  = calendarInst.get(Calendar.MINUTE);
		datetime[0][5]  = calendarInst.get(Calendar.SECOND);
		
		DoubleNumberToken result	= new DoubleNumberToken(datetime);
		return result;
	}
}

/*
@GROUP
general
@SYNTAX
clock()
@DOC
Returns the current date and time as a six element vector.
@EXAMPLES
clock() = [2,002, 6, 6, 5, 23, 34]
@NOTES
The variable should be given as a string containing the variable name.
@SEE
date, tic, toc, time
*/
