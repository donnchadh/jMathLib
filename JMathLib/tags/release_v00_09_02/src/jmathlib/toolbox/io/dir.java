package jmathlib.toolbox.io;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import java.io.*;

/**An external function for getting a directory listing         */
public class dir extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 0)
            throwMathLibException("dir: number of arguments != 0");

		String path=".";

		File dir = new File(getWorkingDirectory(), path);
		String[] files = dir.list();

		
		for (int i=0; i<files.length ; i++)
		{

			String name = files[i]; 
			File   f    = new File(name);
			if (f.isDirectory())
				getInterpreter().displayText(files[i]+"/");
			else
				getInterpreter().displayText(files[i]);

		}


		return null;		

	} // end eval
}

/*
@GROUP
IO
@SYNTAX
dir()
dir(directory)
@DOC
Displays the file list for the current directory.
@EXAMPLES
<programlisting>
dir()
MathLib.MathLib.html
MathLib.log
MathLib.bat
object.txt
Demos.class
MathLib/
CVS/
Demos$1.class
1.0
</programlisting>
@SEE
cd, createnewfile, exist, mkdir, rmdir, delete, isfile, isdirectory, ishidden, lastmodified
*/

