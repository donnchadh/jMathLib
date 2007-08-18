package jmathlib.core.interpreter;

/**MathLib specific exception */
public class MathLibException extends ArithmeticException
{
    /**Create a new exception object*/
    public MathLibException()
    {
    
    }
    
    /**Set the message text
     @param text = the text to display*/
    public MathLibException(String text)
    {
    	super(text);
    }

	
}
