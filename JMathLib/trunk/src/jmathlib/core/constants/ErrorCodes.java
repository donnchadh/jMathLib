/* 
 * This file is part or JMathLib 
 * 
 * Check it out at http://www.jmathlib.de
 *
 * Author:   
 * (c) 2005-2009   
 */
package jmathlib.core.constants;

public interface ErrorCodes
{
    public static final int OK = 0;
    public static final int OK_FUNCTION_PROCESS = 1;

    //syntax errors
    public static final int ERR_OPNOTSUPPORTED           = 1000;

    public static final int ERR_BRACKET_ORDER = 2000;
    public static final int ERR_BRACKET_OPEN  = 2001;

    //variable errors
    public static final int ERR_VARIABLE_NOTDEFINED = 3000;
    public static final int ERR_LVALUE_REQUIRED     = 3100;
    
	//General function errors
	public static final int ERR_INVALID_PARAMETER = 10000;
	public static final int ERR_INSUFFICIENT_PARAMETERS=10001;	    
	public static final int ERR_TOO_MANY_PARAMETERS=10002;	    
	
	public static final int ERR_FUNCTION_NOT_FOUND       = 10100;
   public static final int ERR_FUNCTION_NOT_IMPLEMENTED = 10101;

    //matrix errors
    public static final int ERR_NOT_SQUARE_MATRIX = 12000;
    public static final int ERR_MATRIX_SINGULAR   = 12001;

	//misc errors
	public static final int ERR_USER_ERROR = 20000;
} 