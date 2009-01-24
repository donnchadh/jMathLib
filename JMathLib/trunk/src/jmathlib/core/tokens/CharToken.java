package jmathlib.core.tokens;

import jmathlib.core.interpreter.*;

/**Class used to represent any strings used in an expression*/
public class CharToken extends DataToken
{
    /**The value of the string*/
    private char[][] values;
    
    /**Creates an empty char array
     */
     public CharToken()
     {
         super(99, "char"); 
         sizeY     = 0;
         sizeX     = 0;
         sizeA     = new int[]{0, 0};
         noElem    = 0;
         values    = null;
     }

    /**Creates a string with a value of _value
     * @param _value = the value of the string
     */
    public CharToken(String _value)
    {
        super(99, "char"); 
        values    = new char[1][1];
        values[0] = _value.toCharArray();
        sizeY     = 1;
        sizeX     = values[0].length;
        sizeA     = new int[]{sizeY,sizeX};
        noElem    = sizeY * sizeX;
    }
    
    /**Creates a string with a value of _value
     * @param _value = the value of the string
     */
    public CharToken(String[] _values)
    {
        super(99, "char"); 
        sizeY     = _values.length;
        sizeX     = _values[0].length();
        sizeA     = new int[]{sizeY,sizeX};
        noElem    = sizeY * sizeX;
        
        values    = new char[sizeY][1];
        for (int i=0;i<sizeY;i++) {
          if (_values[i].length() != sizeX)
            Errors.throwMathLibException("CharToken: string sizes must be identical");
          values[i] = _values[i].toCharArray();
        }
    }
    

    /**Creates a string with a value of _value
     * @param _value = the value of the string
     */
     public CharToken(char[][] _values)
     {
         super(99, "char"); 
         values = _values;
         sizeY  = values.length;
         sizeX  = values[0].length;
         sizeA  = new int[]{sizeY,sizeX};
         noElem = sizeY * sizeX;
     }

     /**Creates a string with a value of _value
      * @param _value = the value of the string
      */
      public CharToken(char _value)
      {
          super(99, "char"); 
          values       = new char[1][1];
          values[0][0] = _value;
          sizeY        = 1;
          sizeX        = 1;
          sizeA        = new int[]{sizeY,sizeX};
          noElem       = sizeY * sizeX;
      }

    /**Evaluates the token, just returns the token itself
     * @param operands = the tokens operands (not used)
     * @param
     * @return the token itself as an OperandToken
     */
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {
        return this;
    }

    /**
     * @return the string value
     */    
    public String toString()
    {
        String ret = new String();
        for (int yi=0; yi<sizeY; yi++)
        {
            ret += new String(values[yi]);
        }
    	return ret;
    }

    /**
     * @return the value of the string
     */
    public String getValue()
    {
        return new String(values[0]);
    }

    /**
     * @return the value of the string
     */
    public char getCharValue()
    {
        return values[0][0];
    }

    /**
     * @param
     * @param
     */
    public OperandToken getElement(int y, int x)
    {
        return new CharToken(values[y][x]);
    }

    /**
     * @param
     * @param
     * @param
     */
    public void setElement(int y, int x, OperandToken op)
    {
        char c = ((CharToken)op).getCharValue();
        
        ErrorLogger.debugLine("CharToken("+y+","+x+")"+ c);
        values[y][x] = c;
    }
    
    /**
     * @param
     * @param
     * @return
     */
    public DataToken getElementSized(int y, int x)
    {
        return new CharToken(new char[y][x]); 
    }

    /**add arg to this object to create a new string
     * @param arg = the value to add to the string
     * @return
     */
    public OperandToken add(OperandToken arg)
    {
    	if (sizeY!=1)
            Errors.throwMathLibException("CharToken: add not supported");
        
        String answer = new String(values[0]) + arg.toString();
        values[0] = answer.toCharArray();
        sizeX     = values[0].length;
        return new CharToken(answer);
    }
    
  }
