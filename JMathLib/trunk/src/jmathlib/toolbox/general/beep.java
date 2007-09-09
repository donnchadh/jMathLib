package jmathlib.toolbox.general;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import java.awt.Toolkit;

/**An external function for emitting a beep sound*/
public class beep extends ExternalFunction
{
	/**Emits a beeping sound*/
	public OperandToken evaluate(Token[] operands)
	{
		Toolkit.getDefaultToolkit().beep();
		
		return null;
	}
}

/*
@GROUP
general
@SYNTAX
beep();
@DOC
this sounds an audible beep
@NOTES
@EXAMPLES
<programlisting>
beep();
</programlisting>
@SEE
*/
