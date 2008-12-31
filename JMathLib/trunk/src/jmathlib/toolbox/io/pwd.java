package jmathlib.toolbox.io;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import java.io.*;

public class pwd extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

	    File f = getWorkingDirectory();
	    
		return new CharToken(f.getAbsolutePath());		

	} // end eval
}

/*
@GROUP
IO
@SYNTAX
pwd
@DOC
displays the current working directory
@EXAMPLES
<programlisting>
pwd
</programlisting>
@SEE
cd, createnewfile, dir, exist, mkdir, rmdir, delete, isfile, ishidden, lastmodified
*/

