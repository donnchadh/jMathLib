package jmathlib.core.tokens;


/**
 * 
 * @author stefan
 *
 */
public class FunctionHandleToken extends DataToken
{            

    
    private String name = "";
    
    public FunctionHandleToken()
    {
        super(5, "function_handle");
    }

    public FunctionHandleToken(String _handleS)
    {
        // empty number token
            super(5, "function_handle");
            name = _handleS;
    }

    public String getName()
    {
        return name;
    }
    
    
    /**Evaluate the token. This causes it to return itself*/
    public OperandToken evaluate(Token[] operands)
    {
        return this;    
    }


    public String toString()
    {
        return "@"+name;
    }


} // end FunctionHandleToken
