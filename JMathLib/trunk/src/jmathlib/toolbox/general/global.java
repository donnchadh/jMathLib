package jmathlib.toolbox.general;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.VariableToken;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.interpreter.Variable;
import jmathlib.core.functions.ExternalFunction;

/**An external function which checks if the argument is numeric*/
public class global extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{

        if (getNArgIn(operands) != 1)
			throwMathLibException("global: number of arguments != 1");
            
        if (!(operands[0] instanceof VariableToken))
            throwMathLibException("global: argument must be variable");
            
        VariableToken var  = (VariableToken)operands[0];
        String        name = var.getName();
        
        debugLine("global "+operands[0].toString());
        
        debugLine("global: local  variable:"+getVariables().isVariable(var.getName()));
        debugLine("global: global variable:"+getGlobalVariables().isVariable(var.getName()));

        // check if variable is already created in global context
        if (getGlobalVariables().isVariable(name))
        {
            // variable is already created in global context

            // check if current context already contains variable
            if (getVariables().isVariable(name))
            {
                // remove variable from current workspace
                getVariables().remove(name);
            }
         }
        else
        {
            // variable not yet created in global context
            
            // create variable in global context (data only in global context)
            getGlobalVariables().createVariable(name);
            getGlobalVariables().getVariable(name).setGlobal(true);                

            // check if current context already contains variable
            if (getVariables().isVariable(name))
            {
                // current context already contains variable
                Variable varCurrent = getVariables().getVariable(name);
                getGlobalVariables().getVariable(name).assign(varCurrent.getData());
                
                // remove variable, create new one and set variable to global
                getVariables().remove(name);
            }

        }

        // create new variable in current context and set variable to global
        getVariables().createVariable(name);
        getVariables().getVariable(name).setGlobal(true);

        debugLine("global:global var:"+name+" global="+getGlobalVariables().getVariable(name).isGlobal());


        debugLine("global:local  var:"+name+" global="+getVariables().getVariable(name).isGlobal());

        
        
        return null;
	}
}

/*
@GROUP
general
@SYNTAX
global aaa
@DOC
Returns 1 if the first operand is a cell array, else it returns 0.
@EXAMPLES
<programlisting>
a=33;
global a

</programlisting>
@NOTES
@SEE
isglobal, isvarname
*/

