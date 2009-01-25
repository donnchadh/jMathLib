package jmathlib.core.tokens;

import jmathlib.core.interpreter.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;


/**Class used to represent non numeric matrices*/
public class MatrixToken extends DataToken
{
    /**The values of the matrix (array)*/
    OperandToken value[][];

    /** type of matrix */
    private int typeOfMatrix;   // 0 = numeric array
                                // 1 = cell    array
    
    /**Constructor taking the numbers value as a double
    @param _value = the values of the elements of the matrix*/
    public MatrixToken(OperandToken[][] _value)
    {
        super(5, "matrix"); 
		sizeY  = _value.length;
		sizeX  = _value[0].length;
        value = _value;
    }

    /**Constructor taking a DoubleNumberToken
    @param _value = the values of the elements of the matrix*/
    public MatrixToken(DoubleNumberToken _value)
    {
        super(5, "matrix"); 
		sizeY  = _value.getSizeY();
		sizeX  = _value.getSizeX();
        value = new OperandToken[sizeY][sizeX];
        
        for(int yy = 0; yy < sizeY; yy++)
        {
            for(int xx = 0; xx < sizeX; xx++)
            {
                value[yy][xx] = _value.getElement(yy, xx);
            }
        }
    }

    /**evaluate function - just returns the object itself
    @param operands = the matrices operands (not used)
    @return the matrix token itself*/
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {
		ErrorLogger.debugLine("Matrix evaluate size: "+sizeY+" "+sizeX);
		
        int sizeOfRowY  = 0;    // number or rows of each element in a row must be equal
        int sizeOfRowX  = 0;    // columns of each row
        
        int tmpSizeY    = 0;    // no. of rows    of new overall matrix
        int tmpSizeX    = 0;    // no. or columns of new overall matrix
        
        boolean numberB = true; // stays true if array is purely numeric
        boolean stringB = true; // stays true if array is string  and/or numeric
        
        /*****    Evaluate every single element of the matrix              *****/
		/* Sub-matrices are possible, so row and column size may vary at first */
        /*    e.g. b=[1,2,a;                                                   */
        /*            1,2,3,4,5]											   */
        /*         with a=[3,4,5] will result in b=[1,2,3,4,5;                 */
        /*                                          1,2,3,4,5]                 */
        for (int yy=0; yy<value.length; yy++)
		{
		    for (int xx=0; xx<value[yy].length; xx++)
			{
				//if (value[yy][xx] != null)  // maybe not necessary
				//{
					value[yy][xx] = value[yy][xx].evaluate(null, globals);

					// Check if value[yy][xx] is array of number tokens
                    //  e.g.: [1,2,[3,4],5]
                    if (value[yy][xx] instanceof DoubleNumberToken)
                    {
                    	int valueSizeY = ((DoubleNumberToken)value[yy][xx]).getSizeY();
                        int valueSizeX = ((DoubleNumberToken)value[yy][xx]).getSizeX();
                        //ErrorLogger.debugLine("Matrix: eval: "+valueSizeY+" "+valueSizeX);
                        
                        // The number of rows of EACH element in a row must be equal
                	    // e.g. a=[1,b,3] must be a=[1, 2, 3, 4, 5, 3] 
                    	//      and not           a=[1, [2,3]', 3]
                        if (xx==0)
                    	{
                    		sizeOfRowY   = valueSizeY; // rows of each element in THIS row
                            tmpSizeY    += valueSizeY; // compute total no. of rows
                    		sizeOfRowX   = 0;          // columns of THIS row
                        }
                        
                        // Check if all elements in THIS row have the same number of rows
                        if (valueSizeY != sizeOfRowY)
                        	Errors.throwMathLibException("Matrix: number of rows of each element must be equal");
                        
                        // compute number of columns in THIS row
                        sizeOfRowX  += valueSizeX;
                    }
                    else if (value[yy][xx] instanceof CharToken)
                    {
                        if (xx==0)
                        {
                            sizeOfRowX   = 0;
                        }
                        sizeOfRowX  += 1;
                        sizeOfRowY   = 1;

                        // at least one element is not a number
                        numberB = false; 
                    }
                    else
                    {
                        // at least one element is not a number
                    	numberB = false; 

                        // neither string nor number
                    	stringB = false;
                    }
                                       
				//}
			} // end for xx
            
            // Store length of first row (as size for all rows)
			if (yy==0)
        	{
            	tmpSizeX  = sizeOfRowX;  // size of new overall matrix
            }
             
            // Check if all rows have the same number of columns
            if (sizeOfRowX != tmpSizeX)
            	Errors.throwMathLibException("Matrix: number of columns of all rows must be equal");
            	      
		} // end for yy
        
        // if isSymbolMode() and numberB=false
        // then return a MatrixToken with unresolved entries
        if ((!numberB) && (!stringB))
       		Errors.throwMathLibException("Matrix: is not numeric or string");  

        // e.g. ['asdf' 'asdf'] or
        // e.g. ['asdf' 55 99]
        if (stringB && !numberB)
        {
            ErrorLogger.debugLine("Matrix: found String");
            
            if (tmpSizeY>1) 
                Errors.throwMathLibException("Matrix: String with more than one line not implemented");

            String retString = "";
            
            // convert operands to a single string
            for (int x=0; x<tmpSizeX; x++)
            {
                if (value[0][x] instanceof CharToken)
                {
                    retString += ((DataToken)value[0][x]).toString(); 
                }
                else if (value[0][x] instanceof DoubleNumberToken)
                {
                    // e.g. ['asdf' 65] -> 'asdfA'
                    byte[] b = { new Double( ((DoubleNumberToken)value[0][x]).getValueRe() ).byteValue() };
                    try{
                        retString += new String(b, "UTF8");
                    }
                    catch (Exception e)
                    {
                        Errors.throwMathLibException("Matrix: exception");
                    }
                }
                else
                    Errors.throwMathLibException("Matrix: converting to string");
            }

            return new CharToken(retString);
        }
        
        
        if (!numberB)
        	return new MatrixToken(value);
          
        
        /******************** the matrix is purely NUMERIC *******************/ 
        
       	// create new array to store numeric data
        double valuesRe[][] = new double[tmpSizeY][tmpSizeX];
        double valuesIm[][] = new double[tmpSizeY][tmpSizeX];
	    int valSizeY = 0;
        int valSizeX = 0;
        ErrorLogger.debugLine("Matrix: new bigger array "+tmpSizeY+" "+tmpSizeX);
                    
        // fill new bigger array and expand sub matrices
	    int yBig = 0;
        for (int yy=0; yy<value.length; yy++)
		{
			// number of rows of first element in row gives height of this line
            // e.g. a=[1,b,3]  -> b must be 1*n matrix
            // e.g. a=[c,d]    -> c and d have the same number of rows
                
            int xBig=0;
            for (int xx=0; xx<value[yy].length; xx++)
			{
        	    // get matrix of each element
                valSizeY         = ((DoubleNumberToken)value[yy][xx]).getSizeY();
                valSizeX         = ((DoubleNumberToken)value[yy][xx]).getSizeX();
                double[][] valRe = ((DoubleNumberToken)value[yy][xx]).getValuesRe();
                double[][] valIm = ((DoubleNumberToken)value[yy][xx]).getValuesIm();
                        
                // copy small matrix of each element into global matrix
                for (int y=0; y<valSizeY; y++)
                {
                   	for (int x=0; x<valSizeX; x++)
                    {
                   		valuesRe[yBig+y][xBig+x] = valRe[y][x];
                        valuesIm[yBig+y][xBig+x] = valIm[y][x];
                    }
                }
                xBig += valSizeX;
            }
            yBig += valSizeY;
        }
            
       return new DoubleNumberToken(valuesRe, valuesIm);
   } 

    /**Convert the matrix to a string*/
    public String toString()
    {
		String s = "[";
     
		int y = value.length;
		int x = value[0].length;

		//System.out.println("matrix toString "+y+" "+x);

		for (int yy=0; yy<y; yy++)
		{
			x = value[yy].length;
            for (int xx=0; xx<x; xx++)
			{
				if (value[yy][xx] != null)	s = s + value[yy][xx].toString();
				else						s = s +" ---";

                if (xx < (x-1)) { s = s + " , "; } 
			}
    		if (yy < (y-1))  { s = s + "],["; }
		}
		s = s + " ]";
        return s;
    }

    /**Return the value of the number
    @return the value as an 2D array of double*/
    public OperandToken[][] getValue()
    {
		ErrorLogger.debugLine("matrix getValue");
        return value;
    }

    /**multiply arg by this object for a number token
    @param arg = the amount to multiply the matrix by
    @return the result as an OperandToken*/
   /* public OperandToken multiply(OperandToken arg)
    {
			
 		int y = value.length;
		int x = value[0].length;
		ErrorLogger.debugLine("matrix multiply dimension "+y+" "+x);


		// Multiply every single element of the matrix 
		for (int yy=0; yy<y; yy++)
		{
			for (int xx=0; xx<x; xx++)
			{
				if (value[yy][xx] != null)
				{
					if (arg instanceof DoubleNumberToken) 
					{
						System.out.println("Matrix multiply num");
						value[yy][xx]=value[yy][xx].multiply(arg);
					}
					else if (arg instanceof VariableToken)
					{
						VariableToken argument = ((VariableToken)arg.clone());
						ErrorLogger.debugLine("Matrix multiply variable "+value[yy][xx].toString()+" "+argument.toString());
						value[yy][xx] = new Expression(new MulDivOperatorToken('*'), value[yy][xx], argument);
						ErrorLogger.debugLine("mt1");
				//stefan		value[yy][xx] = value[yy][xx].evaluate(null, globals);
						ErrorLogger.debugLine("mt2");
						//value[yy][xx] = value[yy][xx].multiply(arg);
						ErrorLogger.debugLine("mt3");
						//value[yy][xx] = arg.multiply(value[yy][xx]);
						ErrorLogger.debugLine("mmmmm1 "+value[yy][xx]+" "+argument);
					}
					else if (arg instanceof MatrixToken)
					{
						// Check dimensions (n,m) * (m,o) = (n,o)
						System.out.println("Matrix multiply not implemented");
					}
				}
			}
		}

		MatrixToken retvalue = new MatrixToken(value);
		//retvalue = retvalue.evaluate(null);

		return retvalue;  //stefan retvalue.evaluate(null, globals);
    }
*/

    /**add arg to this object for a number token
    @param arg = the amount to add to the matrix
    @return the result as an OperandToken*/
 /*   public OperandToken add(OperandToken arg)
    {
  		ErrorLogger.debugLine("matrix add");
		//if(arg instanceof VariableToken)
		//return arg;

		// left argument 
		int y1 = value.length;
		int x1 = value[0].length;


		// right argument 
        OperandToken value2[][] = ((MatrixToken)arg).getValue();
		int y2 = value2.length;
		int x2 = value2[0].length;

		if ( (y1 != y2) || (x1 != x2) ) return null;

		ErrorLogger.debugLine("matrix add ("+y1+","+x1+")-("+y2+","+x2+")"  );

		for (int yy=0; yy<y1; yy++)
		{
			for (int xx=0; xx<x1; xx++)
			{
				OperandToken left  = value[yy][xx];
				OperandToken right = value2[yy][xx]; 
				if (   (left  instanceof DoubleNumberToken) 
					&& (right instanceof DoubleNumberToken) ) 
				{
					value[yy][xx]=left.add(right);
					ErrorLogger.debugLine("Matrix add Number Number");
				}
				else
				{
					// build expression and evaluate expression

					ErrorLogger.debugLine("Matrix add Expressions");

					value[yy][xx] = new Expression(new AddSubOperatorToken('+'), left, right);
			//stefam		value[yy][xx] = value[yy][xx].evaluate(null, globals);
				}

				ErrorLogger.debugLine("Matrix add to String "+value[yy][xx].toString());
			}
		}

        return new MatrixToken(value);
    }
*/

	public OperandToken elementAt(int y, int x)
	{
		return value[y][x];
	}
	
} // end MatrixToken
