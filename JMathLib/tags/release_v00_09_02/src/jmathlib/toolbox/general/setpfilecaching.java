package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.tokens.CharToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for enabling/disabling of caching of p-files  */
public class setpfilecaching extends ExternalFunction
{
	/**enable or disable caching of p-files 
	* @param operands[0] 1, 0 , 'on', 'off' 
    */
	public OperandToken evaluate(Token[] operands)
	{
		// one operand (e.g. setPFileCaching(1)
		// one operand (e.g. setPFileCaching('on')
		
		if (getNArgIn(operands)!=1)
			throwMathLibException("setPFileCaching: number of input arguments != 1");

		if (operands[0] instanceof DoubleNumberToken)
		{
			if ( ((DoubleNumberToken)operands[0]).getValueRe()==0)
				getFunctionManager().setPFileCaching(false);
			else
				getFunctionManager().setPFileCaching(true);  	
		}
		else if (operands[0] instanceof CharToken)
		{
			if ( ((CharToken)operands[0]).getValue().equals("on"))
				getFunctionManager().setPFileCaching(true);
			else
				getFunctionManager().setPFileCaching(false);  	
		}
       
		return null;

	} // end eval
}


/*
@GROUP
General
@SYNTAX
setPFileCaching(value)
@DOC
enables or disables caching of p-files
@EXAMPLES
<programlisting>
setPFileCaching(1)      enable caching 

setPFileCaching('on')   enable caching 

setPFileCaching(0)      disable caching 

setPFileCaching('off')  disable caching 
</programlisting>
@NOTES
@SEE
getpfilecaching
*/
