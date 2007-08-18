package jmathlib.toolbox.general;

import jmathlib.core.tokens.*;
import jmathlib.core.interpreter.*;
import jmathlib.core.functions.ExternalFunction;
import java.util.*;

/**An external function for getting the stored variables*/
public class whos extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{
        
        Iterator iter = getVariables().getIterator();

        // display header information
        getInterpreter().displayText("\nYour variables are:\n");
        getInterpreter().displayText("\nName: \t size: \t type \n");

        // check if global context is requested
        if (getNArgIn(operands) == 1)
        {
            if ((operands[0] instanceof CharToken))
            {
                String data = ((CharToken)operands[0]).getValue().toLowerCase();
                if (data.equals("global"))
                    iter = getGlobalVariables().getIterator();
            }
        }

		while(iter.hasNext())
		{
		    Map.Entry    next = ((Map.Entry)iter.next());
		    Variable     var  = (Variable)next.getValue();
		    OperandToken op   = (OperandToken)var.getData();
            String       line = "";
            
            line = var.getName()+"      \t";
            
            // check which type of variable
            if (op instanceof DataToken)
            {
                line += getSizeString(op);
                line += "  \t "+ ((DataToken)op).getDataType()+" ";
            }
            else
            {
                line += "  \t unknown";
            }
            
            if (getGlobalVariables().isVariable(var.getName()))
                line += "(global)";
            
		    getInterpreter().displayText(line);
		}

		return null;		
	}
    
    private String getSizeString(OperandToken tok)
    {
        int[] s = ((DataToken)tok).getSize();

        String line ="";
        
        // for tokens with no size return ""
        if (s==null)
            return "";
        
        // build up "size"-string
        for (int i=0; i< s.length; i++)
        {
            line += s[i];
            if (i<s.length-1)
                line +="x";
        }
        
        return line;
    }
    
}

/*
@GROUP
general
@SYNTAX
whos
@DOC
Returns a list of all the variables in the system.
@EXAMPLES
@NOTES
@SEE
who
*/

