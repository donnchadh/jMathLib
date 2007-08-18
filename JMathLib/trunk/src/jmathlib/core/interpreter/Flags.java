package jmathlib.core.interpreter;

/**Class containing the system flags controlling the program*/
public class Flags
{
    /**sets whether to write debug lines to console and	log files*/
    private boolean debug = true;
    
    /**sets whether mathlib is strictly running with numerics or
       if set to false also works with symbolic expressions. 
       A true value will give most compatability with octave, matlab
       and scilab. All symbolic values must be crated using SYM(name)*/
    private boolean numericB = true;

    /**default constructor*/    
    public Flags()
    {
        debug = false;
    }
    
    /**@return the setting of the debug flag*/
    public boolean getDebug()
    {
        return debug;
    }
    
    /**sets the debug flag
    @param _debug = should debug information be displayed*/
    public void setDebug(boolean _debug)
    {
        debug= _debug;
    }

    /**@return wether the program is in Numeric mode*/
    public boolean isNumericMode()
    {
    	return numericB;
    }
    
    /**@return wether the program is in Symbolic mode*/
    public boolean isSymbolicMode()
    {
    	return (!numericB);
    }

    /**Sets the program to Numeric or Symbolic mode
       @param _numericB = if true sets the system to Numeric mode*/
    public void setNumericMode(boolean _numericB)
    {
    	numericB = _numericB;
    }  
}
