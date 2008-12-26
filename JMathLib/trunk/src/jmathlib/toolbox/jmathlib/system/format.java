package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.tokens.OperandToken;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class format extends ExternalFunction
{
	/**Returns an enviroment variable
	@param operand[0] = the name of the variable
	@param operand[1] = a default value (optional)
	@return the enviroment value*/
	public OperandToken evaluate(Token[] operands)
	{
		OperandToken result = null;
		
        if (getNArgIn(operands)>1)
            throwMathLibException("format: number of arguments > 1");
        

        if ( (getNArgIn(operands)==1) &&
             (!(operands[0] instanceof CharToken)) )
            throwMathLibException("format: argument must be a string");

		String type = "";
		
		if (getNArgIn(operands)==1)
		    type = operands[0].toString();
		
        //setNumberFormat(DecimalFormat.getInstance(Locale.ENGLISH));
		
		if (type.equals("short"))
		    setNumberFormat(new DecimalFormat("0.0000", new DecimalFormatSymbols(Locale.ENGLISH)));
		else if (type.equals("long"))
            setNumberFormat(new DecimalFormat("0.000000000000000", new DecimalFormatSymbols(Locale.ENGLISH)));
		else if (type.equals("short e"))
            setNumberFormat(new DecimalFormat("0.0000E000", new DecimalFormatSymbols(Locale.ENGLISH)));
        else if (type.equals("long e"))
            setNumberFormat(new DecimalFormat("0.000000000000000E000", new DecimalFormatSymbols(Locale.ENGLISH)));
        else if (type.equals("short g"))
            setNumberFormat(new DecimalFormat("0.0000E000", new DecimalFormatSymbols(Locale.ENGLISH)));
        else if (type.equals("long g"))
            setNumberFormat(new DecimalFormat("0.000000000000000E000", new DecimalFormatSymbols(Locale.ENGLISH)));
        else if (type.equals("short eng"))
            setNumberFormat(new DecimalFormat("0.0000E000", new DecimalFormatSymbols(Locale.ENGLISH)));
        else if (type.equals("long eng"))
            setNumberFormat(new DecimalFormat("0.000000000000000E000", new DecimalFormatSymbols(Locale.ENGLISH)));
		else
            setNumberFormat(new DecimalFormat("0.0000", new DecimalFormatSymbols(Locale.ENGLISH)));
		    
		return result;
	}
}

/*
@GROUP
system
@SYNTAX
format('type')
@DOC
changes the numerical format for numbers.
@NOTES
It only affects the display of numbers, the internal format
of numbers is not affected at all.
@EXAMPLES
format()
format('long')
format('short')
format('long e')
format('short e')
format('long g')
format('short g')
format('long eng')
format('short eng')
@SEE
disp
*/

