package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

/**An external function for determining the determinant of a matrix*/
public class determinant extends ExternalFunction
{
	/**Check that the parameter is a square matrix then claculate
	it's determinant*/
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{
		OperandToken result = null;
        
        if (getNArgIn(operands) != 1)
			throwMathLibException("Determinant: number of arguments != 1");
            
		Token operand = operands[0];
		
		if(operand instanceof DoubleNumberToken)
		{
			DoubleNumberToken matrix = ((DoubleNumberToken)operand);
			
			if(matrix.getSizeX() == matrix.getSizeY())
			{
				int size = matrix.getSizeX();
				
				result = new DoubleNumberToken(calcDeterminant(matrix.getReValues(), size));
			}
	        else
	        {
	            jmathlib.core.interpreter.Errors.throwMathLibException(ERR_NOT_SQUARE_MATRIX);
	        }			
		}
		
		return result;
	}
	
	/**Function to actually calculate the determinant
	values 	= array of values
	size 	= the size of the matrix
	result 	= the determinant */
	private double calcDeterminant(double[][] values, int size)
	{
		jmathlib.core.interpreter.ErrorLogger.debugLine("calculating determinant - size = " + size);
		double result = 0;
		if(size == 1)		//special case 1, a scalar value
		{
			result = values[0][0];
		}
		else if(size == 2)	//special case 2, a 2*2 matrix
		{
			result = values[0][0] * values[1][1] - values[1][0] * values[0][1];
		}
		else				//calculate the determinant of an larger matrix
		{					//using recursion
			for(int colNumber = 0; colNumber < size; colNumber++)
			{
				//construct the sub matrix
				double[][] newMatrix = constructMatrix(values, size, colNumber);
	
				int modifier = -1;
				if(colNumber % 2 == 0)
					modifier = 1;
								
				result += modifier * values[0][colNumber] * calcDeterminant(newMatrix, size - 1);
			}
		}
		
		return result;
	}
	
	/**constructs a sub matrix
	values = the array of values
	size   = the size of the origional matrix
	column = the column to remove
	result = the [size-1][size-1] array of values after reomoving
			  row 1 and the specified column*/
	private double[][] constructMatrix(double[][]values, int size, int column)
	{
		jmathlib.core.interpreter.ErrorLogger.debugLine("Creating new matrix - size = " + size);
		double newMatrix[][] = new double[size-1][size-1];
		
		for(int rowNumber = 1; rowNumber < size; rowNumber++)
		{
			for(int colNumber = 0; colNumber < size; colNumber++)
			{
				if(colNumber < column)
					newMatrix[rowNumber-1][colNumber] = values[rowNumber][colNumber];
				else if(colNumber > column)
					newMatrix[rowNumber-1][colNumber-1] = values[rowNumber][colNumber];
					
			}
		}
		return newMatrix;
	}
}

/*
@GROUP
matrix
@SYNTAX
answer = DETERMINANT(square matrix)
@DOC
Returns the determinant for the first operand which must be a square matrix.
@NOTES
@EXAMPLES
DETERMINANT([1,0;0,1]) = 1
DETERMINANT([1,2;3,4]) = -2
@SEE
*/

