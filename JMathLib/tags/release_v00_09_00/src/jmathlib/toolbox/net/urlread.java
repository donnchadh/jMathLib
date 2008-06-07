package jmathlib.toolbox.net;

import jmathlib.core.tokens.*;
import jmathlib.core.functions.ExternalFunction;
import java.net.*;
import java.io.*;

/**An external function for reading files over the network*/
public class urlread extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

		String s        = "";
        String lineFile = "";;
 		    
        if (getNArgIn(operands) != 1)
			throwMathLibException("urlread: number of arguments < 1");
            
		if (!(operands[0] instanceof CharToken))
			throwMathLibException("urlread: argument must be String");
        
        String urlString = ((CharToken)operands[0]).toString();
        
        // open URL
        URL url = null;
        try
        {
            url = new URL( urlString );
        }
        catch (Exception e)
        {
            throwMathLibException("urlread: malformed url");
        }          
        
        // read file over the network
        try 
	    {			
		    BufferedReader inReader = 
                new BufferedReader(new InputStreamReader( url.openStream() ));
 		    
            while ((lineFile = inReader.readLine()) != null)
		    {		    	
		        s += lineFile + "\n";
		    }

		    inReader.close();
	     }
	     catch (Exception e)
	     {
		    throwMathLibException("urlread: error input stream");
	     }		    

	   

		return new CharToken(s);		
	}
}

/*
@GROUP
net
@SYNTAX
urlread( URL)
@DOC
Read a text file from the network. This function supports many
transfer protocols e.g. http, gopher, ftp, file. 
@EXAMPLE
<programlisting>
urlread("http://www.heise.de/newsticker/");
urlread("file:///c:/home/text.txt");
</programlisting>
@NOTES
.
@SEE
csvread, csvwrite
*/
