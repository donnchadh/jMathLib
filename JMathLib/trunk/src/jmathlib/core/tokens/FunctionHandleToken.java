package jmathlib.core.tokens;


/**
 * 
 * @author stefan
 *
 */
public class FunctionHandleToken extends DataToken
{            

    
    String handleS = "";
    
    public FunctionHandleToken()
    {
        super(5, "function_handle");
    }

    public FunctionHandleToken(String _handleS)
    {
        // empty number token
            super(5, "function_handle");
            handleS = _handleS;
    }

    
    
    
    /**Evaluate the token. This causes it to return itself*/
    public OperandToken evaluate(Token[] operands)
    {
        return this;    
    }


    public String toString()
    {
        return "@"+handleS;
    }


} // end FunctionHandleToken
