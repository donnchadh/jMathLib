package jmathlib.toolbox.general;

import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function for creating matrices that consist of zeros          *
 * everywhere except in the diagonal. The diagonal consists of ones.         *                        
 * (e.g.: eye(3) will return a 3-by-3 matrix [1,0,0;0,1,0;0,0,1],            *
 *  eye(4,3) will return a 4-by-3 matrix with diagonal set to 1              */
public class cell extends ExternalFunction
{
	/**returns a cell array 
	@param operands[0] = number of rows
	@param operands[1] = number of columns */
	public OperandToken evaluate(Token[] operands)
	{
		// at least one operands (e.g. cell(n) )
		if (getNArgIn(operands) < 1)
			throwMathLibException("cell: number of arguments < 1");
            
		if (!(operands[0] instanceof DoubleNumberToken)) return null;
		
		int rows    = (int)(((DoubleNumberToken)operands[0]).getReValues())[0][0];
		int columns = rows;

		// two operands (e.g. cell(n,m) )
		if (operands.length == 2) 
		{
			if (operands[1] == null)                   
				throwMathLibException("cell: arg2 is null");
			if (!(operands[1] instanceof DoubleNumberToken)) 
				throwMathLibException("cell: arg2 not a number");

			columns = (int)((DoubleNumberToken)operands[1]).getReValues()[0][0];
		}
		
		// only positive indices
		if ((rows <= 0) || (columns <= 0)) return null;

		// create cell array
		OperandToken   values[][] = new OperandToken[rows][columns];
		CellArrayToken cellArray  = new CellArrayToken(values);

		return cellArray;		

	} // end eval
}

/*
@GROUP
general
@SYNTAX
matrix = cell("a",7,"b",9)
@DOC
creates a cell array
@NOTES
@EXAMPLES
<programlisting>
matrix = cell("a",7,"b",9)
d.value = 33; d.name="hello"
</programlisting>
@SEE
struct, iscell, isstruct
*/

