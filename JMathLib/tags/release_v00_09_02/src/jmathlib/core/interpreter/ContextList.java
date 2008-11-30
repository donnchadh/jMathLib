package jmathlib.core.interpreter;


/**Stores a list of contexts. A context is a set of variables pluss some code.
   When a function is called a new context is added to the list. When the function is removed, the context is destroyed. */
public class ContextList
{
    // reference to the currently executing context
    private Context currentContext;
    
    // reference to the global context
    private Context globalContext;
    
    /**Create an empty context to act as the global context
     * This constructor should only be called ONCE, because it also creates the
     * GLOBAL context
     */
    public ContextList()
    {
        globalContext  = new Context(); // global context 
        currentContext = new Context(); // local context
    }

    /**
     * 
     * @param context
     */
    public void pushContext(Context context)
    {
        context.setParent(currentContext);
        currentContext = context; 
    }

    /**Used to create a new context and put it on the stack when executing a function
     * @param _variables = the variable list of the function, null if it's a script file
     */
    public Context createContext(VariableList _variables)
    {
        //check if this is a script file
        if(_variables == null)
        {
            //use the calling functions variable list
            _variables = currentContext.getVariables();
        }
                   
        //create a new context and make it the current context
        //currentContext = new Context(_variables, null, currentContext);
        currentContext = new Context(_variables, currentContext);

        return currentContext;
    }
    
    /**
     * return to the calling context of the function
     */
    public Context popContext()
    {       
        Context context = currentContext;
        currentContext = currentContext.getParent();
        return context; 
    }

    /**
     * @return the variable list of the current context
     */
    public VariableList getVariables()
    {
        return currentContext.getVariables();
    }

    /**
     * @return the variable list of the global context
     */
    public VariableList getGlobalVariables()
    {
        return globalContext.getVariables();
    }

    /**
     * 
     * @param name
     * @return
     */
    public Variable getVariable(String name)
    {
        // check if variable is marked as global in current context
        Variable var = currentContext.getVariables().getVariable(name);
        if ((var!=null) && (var.isGlobal()))
        {
            // variable is marked global
            // return data from global context
            return globalContext.getVariables().getVariable(name);
        }
        else
        {
            // variable is local only
            // return data from current context
            return currentContext.getVariables().getVariable(name);
        }
    }

    /**
     * @return true if the current context is the global one
     */
    public boolean isGlobalContext()
    {
        return (currentContext.getParent() == null);
    }

    /**
     * 
     * @return
     */
    public Context getCurrentContext()
    {
        return currentContext;
    }
}
