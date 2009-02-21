package jmathlib.core.tokens;

import jmathlib.core.interpreter.GlobalValues;


/**The base class for all binary operators*/
public class BinaryOperatorToken extends OperatorToken
{

    /**Default COnstructor - creates an operator with the value set to ' '*/
    public BinaryOperatorToken()
    {
        super(0); 
        value = ' ';
    }

    /**Constructor taking the operator and priority
    @param _operator = the type of operator
    @param _priority = the priority of the operator*/
    public BinaryOperatorToken(char _operator, int _priority)
    {
    	/**call the super constructor, type defaults to ttoperator and operands to 2*/
        super(_priority); 

        value = _operator;
    }

    /**evaluate the operator
    @param operands = the operators operands
    @return the result as and OperandToken*/
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {
        return null;
    }

    /**Convert the operator to a string*/
    public String toString()
    {
        return String.valueOf(value);
    }

    /**Checks if an object is equal to this operator
    if object is a binary operator then it checks whether they have the
    same value otherwise it calls the super classes version
    @param object = the object being tested against
    @return true if they are equal*/
    public boolean equals(Object object)
    {
    	boolean equal = false;
    	if(object instanceof BinaryOperatorToken)
    		equal = (value == ((BinaryOperatorToken)object).value);
    	else
    		equal = super.equals(object);

    	return equal;
    }
}
