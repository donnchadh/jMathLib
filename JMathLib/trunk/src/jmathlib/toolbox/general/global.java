package jmathlib.toolbox.general;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.VariableToken;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.interpreter.GlobalValues;
import jmathlib.core.interpreter.Variable;
import jmathlib.core.functions.ExternalFunction;

/**An external function which checks if the argument is numeric*/
public class global extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

        if (getNArgIn(operands) != 1)
			throwMathLibException("global: number of arguments != 1");
            
        if (!(operands[0] instanceof VariableToken))
            throwMathLibException("global: argument must be variable");
            
        VariableToken var  = (VariableToken)operands[0];
        String        name = var.getName();
        
        debugLine("global "+operands[0].toString());
        
        debugLine("global: local  variable:"+globals.getLocalVariables().isVariable(var.getName()));
        debugLine("global: global variable:"+globals.getGlobalVariables().isVariable(var.getName()));

        // this is the procedure for global variables:
        // - normally all variables a local to each workspace
        // - the "global" workspace is a totally separate workspace
        // - each workspace which defines a variable as global will
        //   use the "global" workspace instead of its own local workspace
        // - in case a variable is defined as "global" in one workspace and
        //   "local" in another workspace, the local workspace will work
        //   with the "local" variable whereas the "global-one" will work
        //   with the "global" variable
        //   -> ALL indidivual workspaces which like to use the "global"
        //      version of a variable need to call "global variable-name"
        
        
        // check if variable is already created in global context
        if (globals.getGlobalVariables().isVariable(name))
        {
            // variable is already created in global context

            // check if local context already contains variable
            if (globals.getLocalVariables().isVariable(name))
            {
                // variable is already created in local context
                
                // remove variable from current workspace (may delete current value)
                // create empty variable and set pointer to "global" property
                globals.getLocalVariables().remove(name);
                globals.getLocalVariables().createVariable(name);
                globals.getLocalVariables().getVariable(name).setGlobal(true);

                globals.getInterpreter().displayText("WARNING global: variable "+name+
                  " already existed in the local workspace. \n"+
                  " It has been overwritten with the value from"+
                  " the global workspace.\n" +
                  " please type: global variable\n"+
                  " before using a variable as global.");
            }
            else
            {
                // variable is not yet created in local context
                
                // create empty variable and set "global" property
                globals.getLocalVariables().createVariable(name);
                globals.getLocalVariables().getVariable(name).setGlobal(true);
                
            }
         }
        else
        {
            // variable not yet created in global context
            
            // create variable in global context (data only in global context)
            globals.getGlobalVariables().createVariable(name);
            globals.getGlobalVariables().getVariable(name).setGlobal(true);                

            // check if current context already contains variable
            if (globals.getLocalVariables().isVariable(name))
            {
                // current context already contains variable
                Variable varCurrent = globals.getLocalVariables().getVariable(name);
                globals.getGlobalVariables().getVariable(name).assign(varCurrent.getData());
                
                // remove variable, create new one and set variable to global
                globals.getLocalVariables().remove(name);
            }

        }

        // create new variable in current context and set variable to global
        globals.getLocalVariables().createVariable(name);
        globals.getLocalVariables().getVariable(name).setGlobal(true);

        debugLine("global:global var:"+name+" global="+globals.getGlobalVariables().getVariable(name).isGlobal());
        debugLine("global:local  var:"+name+" global="+globals.getLocalVariables().getVariable(name).isGlobal());
       
        
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
isglobal
*/

