package jmathlib.core.tokens;

import jmathlib.core.constants.TokenConstants;
import jmathlib.core.interpreter.GlobalValues;

/*Class used to represent delimiter tokens such as ( ) and ,*/
public class DelimiterToken extends OperandToken implements TokenConstants
{
	/**Character representing the delimiter*/
    public char value;
    
    private String wordValue;

	/**param _value = the value of the delimiter as a char*/
    public DelimiterToken(char _value)
    {
    	super(0); //, "Delimiter");
    	value = _value;
    	wordValue = "";
    }

	/**param _value = the value of the delimiter as a string*/
    public DelimiterToken(String _value)
    {
    	super(0); //, "Delimiter");
    	value = '-';
    	wordValue = _value;
    }

	/**Evaluate the delimiter, just returns the object itself
	@param operands = the delimiters operands
	@return the delimter token as an OperandToken*/
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {   		
    	return this;
    }

	/**@return the value of the delimiter as a string*/    
    public String toString()
    {
    	return String.valueOf(value) + wordValue;
    }

    /**Checks if this operand is a numeric value
    @return true if this is a number, false if it's 
    an algebraic expression*/
    public boolean isNumeric()
    {
    	return true;
    }
    
    //accessor functions
    /**@return the value of wordValue*/
    public String getWordValue()
    {
    	return wordValue;
    }   
}
