package jmathlib.core.tokens;

//import MathLib.Interpreter.RootObject;
//import MathLib.Casts.CastClass;

/**The base class of all operators used in an expression*/
abstract public class OperatorToken extends Token
{

    /**Char representing the operator*/
    protected char value;

    /**default constructor*/
    public OperatorToken()
    {
    	super();
    }

    /**constructor,
    @param _priority, the operators priority */
    public OperatorToken(int _priority)
    {
        super(_priority);
    }
    
    /**@return the value of the operator*/
    public char getValue()
    {
        return value;
    }
    
    /**
     * 
     * @return priority of given operator
     */
    public int getPriority()
    {
        return priority;
    }

}
