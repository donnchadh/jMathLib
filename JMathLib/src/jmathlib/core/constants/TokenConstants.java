package jmathlib.core.constants;

/** a set of constants used by the Token classes*/
public interface TokenConstants
{
    /**Token types*/
    static final int ttNotSet   	= 0;
    static final int ttOperator 	= 1;
    static final int ttOperand   	= 2;
    static final int ttDelimiter  	= 3;

	/**Token priority*/    
    static final int ASSIGN_PRIORITY   = 200;  // =
    static final int BRACKET_PRIORITY  = 150;  // ( ) [ ]
    static final int POWER_PRIORITY    = 110;  // ^
    static final int MULDIV_PRIORITY   = 100;  // * /
    static final int ADDSUB_PRIORITY   =  90;  // + -
    static final int RELATION_PRIORITY =  80;  // < <= > >=
    static final int COMPARE_PRIORITY  =  70;  // == !=
    static final int AND_PRIORITY      =  65;  // &
    static final int OR_PRIORITY       =  55;  // |
    static final int ANDAND_PRIORITY   =  50;  // &&
    static final int OROR_PRIORITY     =  45;  // ||
    
    /**parsing strategy*/
    static final int SINGLE         = 0;
    static final int CONCAT         = 1;
    static final int MATRIX         = 3;
    static final int PARAMETER      = 4;
    
} 