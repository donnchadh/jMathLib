package jmathlib.core.tokens;

import jmathlib.core.interpreter.*;
import jmathlib.core.constants.*;

/**This is the base class for all the types of token supported by MathLib*/
abstract public class Token extends RootObject implements TokenConstants
{
    /**The priority of the token*/
    protected int priority;
    
    /**Indicator if a result of an operation is displayed at the prompt or not
       (e.g. a=2+3, then a=5 is shown at the prompt. a=2+3; then nothing is
       displayed at the prompt */
    private boolean displayResultSwitch = false;

    /**Default Constructor - create a token with the type not set
     */
    public Token()
    {
        priority = 0;
    }

    /**Constructor 
     * @param _priority = priority of token
     */
    public Token(int _priority)
    {
        priority = _priority;
    }

    /**evaluate the token
     * @param operands = an array of RootObject containing the tokens operands
     * @param 
     * @return the result of the token evaluation as a RootObject
     * */
    public abstract OperandToken evaluate(Token[] operands, GlobalValues globals);
    
    /**
     * @return a string representation of the token
     */
    abstract public String toString();

    /**
     * 
     * @param globals
     * @return
     */
    public String toString(GlobalValues globals)
    {
        return toString();
    }
    
    /**Converts the token to its MathML representation. At the moment this is unimplemented and just
       converts the token into a string*/
    public String toMathMlString(OperandToken[] operands)
    {
        return toString(operands);
    }
    
    /** set the display flag for a given token */
    public void setDisplayResult(boolean _displayResultSwitch)
    {
    	displayResultSwitch = _displayResultSwitch;
    }
    
    /** return if the display flag for a given token is set 
     * @return
     */
    public boolean isDisplayResult()
    {
    	return displayResultSwitch;
    }
}
