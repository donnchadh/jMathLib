/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   
 * (c) 2002-2009   
 */


package jmathlib.core.interpreter;

/**MathLib specific exception */
public class JMathLibException extends ArithmeticException
{
    /**Create a new exception object*/
    public JMathLibException()
    {
    
    }
    
    /**Set the message text
     * @param text = the text to display
     */
    public JMathLibException(String text)
    {
    	super(text);
    }

	
}
